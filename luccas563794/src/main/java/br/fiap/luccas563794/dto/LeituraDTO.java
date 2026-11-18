package br.fiap.luccas563794.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraDTO {
    private Long id;

    @NotNull(message = "Selecione o trecho.")
    private Long trechoId;

    private String trechoDescricao;

    @NotNull(message = "Informe a data e a hora.")
    @PastOrPresent(message = "A data e a hora não podem estar no futuro.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataHora;

    @NotNull(message = "Informe a lâmina.")
    @Min(value = 0, message = "A lâmina deve estar entre 0 e 300 cm.")
    @Max(value = 300, message = "A lâmina deve estar entre 0 e 300 cm.")
    private Integer laminaCm;

    private String status;
}
