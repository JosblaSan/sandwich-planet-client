package cl.josbla.sandwichplanet.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.josbla.sandwichplanet.client.models.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
    Optional<Tipo> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
