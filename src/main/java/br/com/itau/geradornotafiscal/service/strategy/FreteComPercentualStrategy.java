package br.com.itau.geradornotafiscal.service.strategy;

import br.com.itau.geradornotafiscal.model.Regiao;

public class FreteComPercentualStrategy implements FreteStrategy {

    private final CalculadoraFreteComPercentual calculadora;

    public FreteComPercentualStrategy() {
        this.calculadora = new CalculadoraFreteComPercentual();
    }

    @Override
    public double calcularFrete(double valorFrete, Regiao regiao) {
        return calculadora.calcular(valorFrete, regiao);
    }

    private static class CalculadoraFreteComPercentual {
        public double calcular(double valorFrete, Regiao regiao) {
            switch (regiao) {
                case NORTE: return valorFrete * 1.08;
                case NORDESTE: return valorFrete * 1.085;
                case CENTRO_OESTE: return valorFrete * 1.07;
                case SUDESTE: return valorFrete * 1.048;
                case SUL: return valorFrete * 1.06;
                default: return valorFrete;
            }
        }
    }
}
