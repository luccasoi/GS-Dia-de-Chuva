package br.fiap.luccas563794.service;

import br.fiap.luccas563794.domain.Bairro;
import br.fiap.luccas563794.dto.BairroDTO;
import br.fiap.luccas563794.mapper.BairroMapper;
import br.fiap.luccas563794.repository.BairroRepository;
import br.fiap.luccas563794.repository.TrechoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BairroService {
    private final BairroRepository repo;
    private final TrechoRepository trechoRepo;
    private final BairroMapper mapper;

    public List<BairroDTO> listar() {
        return repo.findAllByOrderByNomeAsc()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public BairroDTO buscarPorId(Long id) {
        Bairro bairro = buscarEntidade(id);
        return mapper.toDTO(bairro);
    }

    public Bairro buscarEntidade(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Bairro " + id + " não existe."));
    }

    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        if (dto.getId() != null) {
            buscarEntidade(dto.getId());
        }
        Bairro bairro = mapper.toEntity(dto);
        Bairro bairroSalvo = repo.save(bairro);
        return mapper.toDTO(bairroSalvo);
    }

    @Transactional
    public void excluir(Long id) {
        Bairro bairro = buscarEntidade(id);
        // não deixa excluir bairro que ainda tem trecho
        if (trechoRepo.existsByBairroId(id)) {
            throw new RegraNegocioException("O bairro " + bairro.getNome()
                    + " não pode ser excluído porque possui trechos cadastrados.");
        }
        repo.delete(bairro);
    }
}
