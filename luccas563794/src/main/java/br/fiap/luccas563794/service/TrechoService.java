package br.fiap.luccas563794.service;

import br.fiap.luccas563794.domain.Bairro;
import br.fiap.luccas563794.domain.Trecho;
import br.fiap.luccas563794.dto.TrechoDTO;
import br.fiap.luccas563794.mapper.TrechoMapper;
import br.fiap.luccas563794.repository.LeituraRepository;
import br.fiap.luccas563794.repository.TrechoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrechoService {
    private final TrechoRepository repo;
    private final LeituraRepository leituraRepo;
    private final BairroService bairroService;
    private final TrechoMapper mapper;

    public List<TrechoDTO> listar() {
        return repo.findAllByOrderByAltitudeAscLogradouroAsc()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public TrechoDTO buscarPorId(Long id) {
        Trecho trecho = buscarEntidade(id);
        return mapper.toDTO(trecho);
    }

    public Trecho buscarEntidade(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Trecho " + id + " não existe."));
    }

    @Transactional
    public TrechoDTO salvar(TrechoDTO dto) {
        if (dto.getId() != null) {
            buscarEntidade(dto.getId());
        }
        Bairro bairro = bairroService.buscarEntidade(dto.getBairroId());
        Trecho trecho = mapper.toEntity(dto, bairro);
        Trecho trechoSalvo = repo.save(trecho);
        return mapper.toDTO(trechoSalvo);
    }

    @Transactional
    public void excluir(Long id) {
        Trecho trecho = buscarEntidade(id);
        // apaga as leituras do trecho antes de apagar o trecho
        if (leituraRepo.existsByTrechoId(id)) {
            leituraRepo.deleteByTrechoId(id);
        }
        repo.delete(trecho);
    }
}
