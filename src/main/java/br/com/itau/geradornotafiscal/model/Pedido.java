package br.com.itau.geradornotafiscal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;


@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

	@NotNull
	@JsonProperty("id_pedido")
	private Integer idPedido;

	@NotNull
	@JsonProperty("data")
	private LocalDate data;

	@NotNull
	@JsonProperty("valor_total_itens")
	private Double valorTotalItens;

	@NotNull
	@JsonProperty("valor_frete")
	private Double valorFrete;

	@NotEmpty
	@Valid
	@JsonProperty("itens")
	private List<Item> itens;

	@NotNull
	@Valid
	@JsonProperty("destinatario")
	private Destinatario destinatario;
}
