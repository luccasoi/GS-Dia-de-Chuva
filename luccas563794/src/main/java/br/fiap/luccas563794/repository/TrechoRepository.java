package br.fiap.luccas563794.repository;

import br.fiap.luccas563794.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrechoRepository extends JpaRepository<Trecho, Long> {
    // ordena pela altitude e, se empatar, pelo logradouro
    List<Trecho> findAllByOrderByAltitudeAscLogradouroAsc();

    List<Trecho> findByBairroIdOrderByLogradouroAsc(Long bairroId);

    boolean existsByBairroId(Long bairroId);
}
