package br.com.itau.geradornotafiscal.service.factory;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.strategy.*;

public class AliquotaStrategyFactory {

    public static AliquotaStrategy getStrategy(Pedido pedido) {
        Destinatario destinatario = pedido.getDestinatario();
        TipoPessoa tipoPessoa = destinatario.getTipoPessoa();

        if (tipoPessoa == TipoPessoa.FISICA) {
            return new AliquotaPessoaFisicaStrategy();
        }

        RegimeTributacaoPJ regime = destinatario.getRegimeTributacao();

        switch (regime) {
            case SIMPLES_NACIONAL:
                return new AliquotaPessoaJuridicaStrategy();
            case LUCRO_REAL:
                return new AliquotaLucroRealStrategy();
            case LUCRO_PRESUMIDO:
                return new AliquotaLucroPresumidoStrategy();
            default:
                throw new IllegalArgumentException("Regime de tributação desconhecido");
        }
    }
}
