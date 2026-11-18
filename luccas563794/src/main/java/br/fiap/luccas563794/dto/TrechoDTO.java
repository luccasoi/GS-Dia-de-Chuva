package br.fiap.luccas563794.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrechoDTO {
    private Long id;

    @NotBlank(message = "Informe o logradouro.")
    @Size(max = 120, message = "O logradouro deve ter no máximo 120 caracteres.")
    private String logradouro;

    @NotBlank(message = "Selecione o tipo de calçada.")
    private String tipoCalcada;

    @NotNull(message = "Informe a altitude.")
    @Min(value = 0, message = "A altitude deve estar entre 0 e 2000 m.")
    @Max(value = 2000, message = "A altitude deve estar entre 0 e 2000 m.")
    private Integer altitude;

    @NotNull(message = "Selecione o bairro.")
    private Long bairroId;

    private String bairroNome;
}
