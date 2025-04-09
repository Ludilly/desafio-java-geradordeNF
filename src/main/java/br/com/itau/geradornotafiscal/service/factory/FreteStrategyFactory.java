package br.com.itau.geradornotafiscal.service.factory;

import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.service.strategy.FreteComPercentualStrategy;
import br.com.itau.geradornotafiscal.service.strategy.FretePorRegiaoStrategy;
import br.com.itau.geradornotafiscal.service.strategy.FreteStrategy;

public class FreteStrategyFactory {

    public static FreteStrategy getStrategy(Regiao regiao) {
        if (regiao == Regiao.SUL || regiao == Regiao.SUDESTE) {
            return new FreteComPercentualStrategy();
        } else {
            return new FretePorRegiaoStrategy();
        }
    }
}
