package br.fiap.luccas563794.mapper;

import br.fiap.luccas563794.domain.Bairro;
import br.fiap.luccas563794.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {
    public Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();
        bairro.setId(dto.getId());
        bairro.setNome(dto.getNome());
        bairro.setZona(dto.getZona());
        bairro.setPossuiPiscinao(dto.getPossuiPiscinao());
        return bairro;
    }

    public BairroDTO toDTO(Bairro bairro) {
        return new BairroDTO(
                bairro.getId(),
                bairro.getNome(),
                bairro.getZona(),
                bairro.getPossuiPiscinao()
        );
    }
}
