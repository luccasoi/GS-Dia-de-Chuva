package br.fiap.luccas563794.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BairroDTO {
    private Long id;

    @NotBlank(message = "Informe o nome do bairro.")
    @Size(max = 80, message = "O nome deve ter no máximo 80 caracteres.")
    private String nome;

    @NotBlank(message = "Selecione a zona.")
    private String zona;

    private Boolean possuiPiscinao;
}
