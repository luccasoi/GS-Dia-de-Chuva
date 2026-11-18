package br.fiap.luccas563794.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PainelDTO {
    private Long bairroId;
    private String bairroNome;
    private long qtdNormal;
    private long qtdAtencao;
    private long qtdAlerta;
    private List<PainelItemDTO> itens;
}
