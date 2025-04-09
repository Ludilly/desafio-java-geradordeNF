package br.com.itau.geradornotafiscal.service.strategy;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProduto;

import java.util.List;

public class AliquotaLucroPresumidoStrategy implements AliquotaStrategy {

    private final CalculadoraAliquotaProduto calculadoraAliquotaProduto;

    public AliquotaLucroPresumidoStrategy() {
        this.calculadoraAliquotaProduto = new CalculadoraAliquotaProduto();
    }

    @Override
    public List<ItemNotaFiscal> calcularAliquota(Pedido pedido) {
        double valorTotalItens = pedido.getValorTotalItens();
        double aliquota;

        if (valorTotalItens < 1000) {
            aliquota = 0.03;
        } else if (valorTotalItens <= 2000) {
            aliquota = 0.09;
        } else if (valorTotalItens <= 5000) {
            aliquota = 0.16;
        } else {
            aliquota = 0.20;
        }

        return calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);
    }
}
