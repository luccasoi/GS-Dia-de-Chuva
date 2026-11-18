package br.fiap.luccas563794.mapper;

import br.fiap.luccas563794.domain.Leitura;
import br.fiap.luccas563794.domain.Trecho;
import br.fiap.luccas563794.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {
    public Leitura toEntity(LeituraDTO dto, Trecho trecho, String status) {
        Leitura leitura = new Leitura();
        leitura.setId(dto.getId());
        leitura.setDataHora(dto.getDataHora());
        leitura.setLaminaCm(dto.getLaminaCm());
        leitura.setStatus(status);
        leitura.setTrecho(trecho);
        return leitura;
    }

    public LeituraDTO toDTO(Leitura leitura) {
        Trecho trecho = leitura.getTrecho();
        return new LeituraDTO(
                leitura.getId(),
                trecho.getId(),
                trecho.getLogradouro() + " (" + trecho.getBairro().getNome() + ")",
                leitura.getDataHora(),
                leitura.getLaminaCm(),
                leitura.getStatus()
        );
    }
}
