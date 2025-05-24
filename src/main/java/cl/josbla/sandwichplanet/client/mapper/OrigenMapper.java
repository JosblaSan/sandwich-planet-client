package cl.josbla.sandwichplanet.client.mapper;

import cl.josbla.sandwichplanet.client.dto.OrigenDTO;
import cl.josbla.sandwichplanet.client.models.Origen;

public class OrigenMapper {
    
    public static OrigenDTO toDto(Origen origen) {
        return OrigenDTO.builder()
                .id(origen.getId())
                .nombre(origen.getNombre())
                .build();
    }

    public static Origen toEntity(OrigenDTO dto) {
        return Origen.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }
}
