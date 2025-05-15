package cl.josbla.sandwichplanet.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.josbla.sandwichplanet.client.models.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    Optional<Ingrediente> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
