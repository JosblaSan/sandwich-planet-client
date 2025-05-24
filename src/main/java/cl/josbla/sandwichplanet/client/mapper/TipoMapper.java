package cl.josbla.sandwichplanet.client.mapper;

import cl.josbla.sandwichplanet.client.dto.TipoDto;
import cl.josbla.sandwichplanet.client.models.Tipo;

public class TipoMapper {
    public static TipoDto toDto(Tipo tipo) {
        TipoDto dto = new TipoDto();
        dto.setId(tipo.getId());
        dto.setNombre(tipo.getNombre());
        dto.setDescripcion(tipo.getDescripcion());
        dto.setFechaCreacion(tipo.getFechaCreacion());
        dto.setFechaModificacion(tipo.getFechaModificacion());
        return dto;
    }

    public static Tipo toEntity(TipoDto dto) {
        Tipo tipo = new Tipo();
        tipo.setId(dto.getId());
        tipo.setNombre(dto.getNombre());
        tipo.setDescripcion(dto.getDescripcion());
        return tipo;
    }
}