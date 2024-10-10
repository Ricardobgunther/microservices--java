package br.edu.atitus.paradigma.cambioservice.repository;

import br.edu.atitus.paradigma.cambioservice.entity.CambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CambioRepository extends JpaRepository<CambioEntity, Integer> {
    Optional<CambioEntity> findByOrigemAndDestino(String origem, String destino);
}
