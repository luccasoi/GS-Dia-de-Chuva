package br.fiap.luccas563794.service;

import br.fiap.luccas563794.domain.Leitura;
import br.fiap.luccas563794.domain.Trecho;
import br.fiap.luccas563794.dto.LeituraDTO;
import br.fiap.luccas563794.mapper.LeituraMapper;
import br.fiap.luccas563794.repository.LeituraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeituraService {
    public static final int LIMIAR = 14;

    private final LeituraRepository repo;
    private final TrechoService trechoService;
    private final LeituraMapper mapper;

    public List<LeituraDTO> listar() {
        return repo.findAllByOrderByDataHoraDesc()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public LeituraDTO buscarPorId(Long id) {
        Leitura leitura = buscarEntidade(id);
        return mapper.toDTO(leitura);
    }

    public Leitura buscarEntidade(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Leitura " + id + " não existe."));
    }

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        if (dto.getId() != null) {
            buscarEntidade(dto.getId());
        }
        Trecho trecho = trechoService.buscarEntidade(dto.getTrechoId());
        String status = calcularStatus(dto.getLaminaCm());
        Leitura leitura = mapper.toEntity(dto, trecho, status);
        Leitura leituraSalva = repo.save(leitura);
        return mapper.toDTO(leituraSalva);
    }

    @Transactional
    public void excluir(Long id) {
        Leitura leitura = buscarEntidade(id);
        repo.delete(leitura);
    }

    public String calcularStatus(int laminaCm) {
        if (laminaCm >= LIMIAR) {
            return Leitura.ALERTA;
        }
        if (laminaCm >= LIMIAR / 2) {
            return Leitura.ATENCAO;
        }
        return Leitura.NORMAL;
    }
}
