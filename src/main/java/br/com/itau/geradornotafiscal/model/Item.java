package br.com.itau.geradornotafiscal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {

	@NotBlank
	@JsonProperty("id_item")
	private String idItem;

	@NotBlank
	@JsonProperty("descricao")
	private String descricao;

	@Min(0)
	@JsonProperty("valor_unitario")
	private double valorUnitario;

	@Min(1)
	@JsonProperty("quantidade")
	private int quantidade;
}

