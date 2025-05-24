package cl.josbla.sandwichplanet.client.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.josbla.sandwichplanet.client.dto.TipoDto;
import cl.josbla.sandwichplanet.client.mapper.TipoMapper;
import cl.josbla.sandwichplanet.client.models.Tipo;
import cl.josbla.sandwichplanet.client.repository.TipoRepository;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    
    public List<TipoDto> findAll() {
        return tipoRepository.findAll().stream()
                .map(TipoMapper::toDto)
                .collect(Collectors.toList());
    }

   
    public TipoDto findById(Long id) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        return TipoMapper.toDto(tipo);
    }


    public TipoDto create(TipoDto tipoDto) {
        Tipo tipo = TipoMapper.toEntity(tipoDto);
        return TipoMapper.toDto(tipoRepository.save(tipo));
    }

  
    public TipoDto update(Long id, TipoDto tipoDto) {
        Tipo existing = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        existing.setNombre(tipoDto.getNombre());
        existing.setDescripcion(tipoDto.getDescripcion());

        return TipoMapper.toDto(tipoRepository.save(existing));
    }

   
    public void delete(Long id) {
        tipoRepository.deleteById(id);
    }

}
