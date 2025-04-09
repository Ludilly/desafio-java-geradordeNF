package br.com.itau.geradornotafiscal.service.strategy;

import br.com.itau.geradornotafiscal.model.Regiao;

public interface FreteStrategy {
    double calcularFrete(double valorFrete, Regiao regiao);
}
