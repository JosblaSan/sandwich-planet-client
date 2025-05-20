package cl.josbla.sandwichplanet.client.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.josbla.sandwichplanet.client.dto.IngredienteDTo;
import cl.josbla.sandwichplanet.client.mapper.IngredienteMapper;
import cl.josbla.sandwichplanet.client.models.Ingrediente;
import cl.josbla.sandwichplanet.client.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    public List<IngredienteDTo> findAll() {
        return ingredienteRepository.findAll().stream()
                .map(IngredienteMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<IngredienteDTo> findById(Long id) {
        return ingredienteRepository.findById(id).map(IngredienteMapper::toDto);
    }

    public IngredienteDTo save(IngredienteDTo dto) {
        Ingrediente ingrediente = IngredienteMapper.toEntity(dto);
        return IngredienteMapper.toDto(ingredienteRepository.save(ingrediente));
    }

    public Optional<IngredienteDTo> update(Long id, IngredienteDTo dto) {
        return ingredienteRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            existing.setEsVegetariano(dto.isEsVegetariano());
            return IngredienteMapper.toDto(ingredienteRepository.save(existing));
        });
    }

    public void delete(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
