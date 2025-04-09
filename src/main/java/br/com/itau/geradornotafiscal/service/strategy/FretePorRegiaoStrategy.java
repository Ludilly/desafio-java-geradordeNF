package br.com.itau.geradornotafiscal.service.strategy;

import br.com.itau.geradornotafiscal.model.Regiao;

public class FretePorRegiaoStrategy implements FreteStrategy {

    private final CalculadoraFretePorRegiao calculadora;

    public FretePorRegiaoStrategy() {
        this.calculadora = new CalculadoraFretePorRegiao();
    }

    @Override
    public double calcularFrete(double valorFrete, Regiao regiao) {
        return calculadora.calcular(valorFrete, regiao);
    }

    private static class CalculadoraFretePorRegiao {
        public double calcular(double valorFrete, Regiao regiao) {
            double percentual;

            switch (regiao) {
                case SUL: percentual = 0.10; break;
                case SUDESTE: percentual = 0.15; break;
                case NORDESTE: percentual = 0.20; break;
                case NORTE: percentual = 0.25; break;
                case CENTRO_OESTE: percentual = 0.18; break;
                default: percentual = 0.15;
            }

            return valorFrete * (1 + percentual);
        }
    }
}
