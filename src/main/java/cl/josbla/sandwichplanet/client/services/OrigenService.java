package cl.josbla.sandwichplanet.client.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.josbla.sandwichplanet.client.dto.OrigenDTO;
import cl.josbla.sandwichplanet.client.mapper.OrigenMapper;
import cl.josbla.sandwichplanet.client.models.Origen;
import cl.josbla.sandwichplanet.client.repository.OrigenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrigenService {

    private final OrigenRepository origenRepository;

    public List<OrigenDTO> findAll() {
        return origenRepository.findAll().stream()
                .map(OrigenMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OrigenDTO> findById(Long id) {
        return origenRepository.findById(id).map(OrigenMapper::toDto);
    }

    public OrigenDTO save(OrigenDTO dto) {
        Origen origen = OrigenMapper.toEntity(dto);
        return OrigenMapper.toDto(origenRepository.save(origen));
    }

    public Optional<OrigenDTO> update(Long id, OrigenDTO dto) {
        return origenRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            return OrigenMapper.toDto(origenRepository.save(existing));
        });
    }

    public void delete(Long id) {
        origenRepository.deleteById(id);
    }
}
