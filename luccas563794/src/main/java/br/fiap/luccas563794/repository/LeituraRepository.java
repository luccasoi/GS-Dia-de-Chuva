package br.fiap.luccas563794.repository;

import br.fiap.luccas563794.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findAllByOrderByDataHoraDesc();

    Optional<Leitura> findFirstByTrechoIdOrderByDataHoraDesc(Long trechoId);

    boolean existsByTrechoId(Long trechoId);

    // usado quando o trecho é excluído
    void deleteByTrechoId(Long trechoId);
}
