package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.GeradorNotaFiscalService;
import br.com.itau.geradornotafiscal.service.factory.AliquotaStrategyFactory;
import br.com.itau.geradornotafiscal.service.factory.FreteStrategyFactory;
import br.com.itau.geradornotafiscal.service.strategy.AliquotaStrategy;
import br.com.itau.geradornotafiscal.service.strategy.FreteStrategy;
import br.com.itau.geradornotafiscal.service.impl.*;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService {

	private final ExecutorService executor = Executors.newFixedThreadPool(4);

	@Override
	public NotaFiscal gerarNotaFiscal(Pedido pedido) {
		Destinatario destinatario = pedido.getDestinatario();
		Regiao regiao = destinatario.getEnderecos().stream()
				.filter(endereco -> endereco.getFinalidade() == Finalidade.ENTREGA || endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA)
				.map(Endereco::getRegiao)
				.findFirst()
				.orElse(null);

		AliquotaStrategy aliquotaStrategy = AliquotaStrategyFactory.getStrategy(pedido);
		List<ItemNotaFiscal> itemNotaFiscalList = aliquotaStrategy.calcularAliquota(pedido);

		FreteStrategy freteStrategy = FreteStrategyFactory.getStrategy(regiao);
		double valorFreteComPercentual = freteStrategy.calcularFrete(pedido.getValorFrete(), regiao);

		String idNotaFiscal = UUID.randomUUID().toString();
		NotaFiscal notaFiscal = NotaFiscal.builder()
				.idNotaFiscal(idNotaFiscal)
				.data(LocalDateTime.now())
				.valorTotalItens(pedido.getValorTotalItens())
				.valorFrete(valorFreteComPercentual)
				.itens(itemNotaFiscalList)
				.destinatario(destinatario)
				.build();

		Callable<Void> estoqueTask = () -> {
			new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal);
			return null;
		};

		Callable<Void> registroTask = () -> {
			new RegistroService().registrarNotaFiscal(notaFiscal);
			return null;
		};

		Callable<Void> entregaTask = () -> {
			new EntregaService().agendarEntrega(notaFiscal);
			return null;
		};

		Callable<Void> financeiroTask = () -> {
			new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal);
			return null;
		};

		List<Callable<Void>> tasks = Arrays.asList(estoqueTask, registroTask, entregaTask, financeiroTask);

		try {
			executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Erro ao processar integrações", e);
		}

		return notaFiscal;
	}
}

