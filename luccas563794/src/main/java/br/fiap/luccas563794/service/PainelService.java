package br.fiap.luccas563794.service;

import br.fiap.luccas563794.domain.Bairro;
import br.fiap.luccas563794.domain.Leitura;
import br.fiap.luccas563794.domain.Trecho;
import br.fiap.luccas563794.dto.PainelDTO;
import br.fiap.luccas563794.dto.PainelItemDTO;
import br.fiap.luccas563794.repository.LeituraRepository;
import br.fiap.luccas563794.repository.TrechoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PainelService {
    private final BairroService bairroService;
    private final TrechoRepository trechoRepo;
    private final LeituraRepository leituraRepo;

    public List<PainelItemDTO> montarPainel(Long bairroId) {
        bairroService.buscarEntidade(bairroId);
        List<Trecho> trechos = trechoRepo.findByBairroIdOrderByLogradouroAsc(bairroId);
        List<PainelItemDTO> itens = new ArrayList<>();
        for (Trecho trecho : trechos) {
            // pega só a leitura mais recente do trecho
            Leitura ultima = leituraRepo.findFirstByTrechoIdOrderByDataHoraDesc(trecho.getId()).orElse(null);
            Integer ultimaLamina = null;
            String ultimoStatus = null;
            if (ultima != null) {
                ultimaLamina = ultima.getLaminaCm();
                ultimoStatus = ultima.getStatus();
            }
            itens.add(new PainelItemDTO(
                    trecho.getLogradouro(),
                    trecho.getTipoCalcada(),
                    trecho.getAltitude(),
                    ultimaLamina,
                    ultimoStatus
            ));
        }
        return itens;
    }

    public PainelDTO montarPainelCompleto(Long bairroId) {
        Bairro bairro = bairroService.buscarEntidade(bairroId);
        List<PainelItemDTO> itens = montarPainel(bairroId);
        return new PainelDTO(
                bairro.getId(),
                bairro.getNome(),
                contar(itens, Leitura.NORMAL),
                contar(itens, Leitura.ATENCAO),
                contar(itens, Leitura.ALERTA),
                itens
        );
    }

    private long contar(List<PainelItemDTO> itens, String status) {
        return itens.stream()
                .filter(i -> status.equals(i.getUltimoStatus()))
                .count();
    }
}
