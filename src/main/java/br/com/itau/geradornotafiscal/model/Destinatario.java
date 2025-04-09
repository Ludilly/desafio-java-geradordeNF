package br.com.itau.geradornotafiscal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.Valid;

import javax.validation.constraints.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Destinatario {

	@NotBlank
	@JsonProperty("nome")
	private String nome;

	@NotNull
	@JsonProperty("tipo_pessoa")
	private TipoPessoa tipoPessoa;

	@JsonProperty("regime_tributacao")
	private RegimeTributacaoPJ regimeTributacao;

	@NotEmpty
	@Valid
	@JsonProperty("documentos")
	private List<Documento> documentos;

	@NotEmpty
	@Valid
	@JsonProperty("enderecos")
	private List<Endereco> enderecos;
}




