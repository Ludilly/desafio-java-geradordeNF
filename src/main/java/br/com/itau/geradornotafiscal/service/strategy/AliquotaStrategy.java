package br.com.itau.geradornotafiscal.service.strategy;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;

import java.util.List;

public interface AliquotaStrategy {
    List<ItemNotaFiscal> calcularAliquota(Pedido pedido);
}
