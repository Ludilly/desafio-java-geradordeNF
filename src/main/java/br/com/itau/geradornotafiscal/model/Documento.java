    package br.com.itau.geradornotafiscal.model;

    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import javax.validation.constraints.NotBlank;
    import javax.validation.constraints.NotNull;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Documento {

        @NotBlank
        private String numero;

        @NotNull
        private TipoDocumento tipo;

    }
