package cl.josbla.sandwichplanet.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.josbla.sandwichplanet.client.models.Origen;

@Repository
public interface OrigenRepository extends JpaRepository<Origen, Long> {
    Optional<Origen> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
