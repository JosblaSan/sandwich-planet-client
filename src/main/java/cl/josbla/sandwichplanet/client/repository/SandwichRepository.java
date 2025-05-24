package cl.josbla.sandwichplanet.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.josbla.sandwichplanet.client.models.Sandwich;

@Repository
public interface SandwichRepository extends JpaRepository<Sandwich, Long> {
    Optional<Sandwich> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
