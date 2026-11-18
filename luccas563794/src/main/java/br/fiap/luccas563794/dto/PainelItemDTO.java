package br.fiap.luccas563794.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PainelItemDTO {
    private String logradouro;
    private String tipoCalcada;
    private Integer altitude;
    private Integer ultimaLamina;
    private String ultimoStatus;
}
