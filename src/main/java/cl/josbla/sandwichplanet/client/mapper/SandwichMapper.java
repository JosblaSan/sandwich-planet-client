package cl.josbla.sandwichplanet.client.mapper;

import java.util.List;
import java.util.stream.Collectors;

import cl.josbla.sandwichplanet.client.dto.SandwichRequestDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichResponseDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichResumenDTO;
import cl.josbla.sandwichplanet.client.models.Ingrediente;
import cl.josbla.sandwichplanet.client.models.Origen;
import cl.josbla.sandwichplanet.client.models.Sandwich;
import cl.josbla.sandwichplanet.client.models.Tipo;

public class SandwichMapper {

    public static SandwichResponseDTO toDto(Sandwich sandwich) {
        return SandwichResponseDTO.builder()
                .id(sandwich.getId())
                .nombre(sandwich.getNombre())
                .descripcion(sandwich.getDescripcion())
                .origen(sandwich.getOrigen().getNombre())
                .tipo(sandwich.getTipo().getNombre())
                .ingredientes(
                    sandwich.getIngredientes()
                            .stream()
                            .map(i -> i.getNombre())
                            .collect(Collectors.toList())
                )
                .vegetariano(sandwich.isVegetariano())
                .caloriasAproximadas(sandwich.getCaloriasAproximadas())
                .imagenUrl(sandwich.getImagenUrl())
                .precio(sandwich.getPrecio())
                .disponible(sandwich.isDisponible())
                .build();
    }

    public static List<SandwichResponseDTO> toDtoList(List<Sandwich> sandwiches) {
        return sandwiches.stream()
                .map(SandwichMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Sandwich fromDto(
            SandwichRequestDTO dto,
            Tipo tipo,
            Origen pais,
            List<Ingrediente> ingredientes
    ) {
        return Sandwich.builder()
                .nombre(dto.getNombre())
                .tipo(tipo)
                .origen(pais)
                .ingredientes(ingredientes.stream().collect(Collectors.toSet()))
                .vegetariano(dto.isVegetariano())
                .caloriasAproximadas(dto.getCaloriasAproximadas())
                .precio(dto.getPrecio())
                .disponible(dto.isDisponible())
                .descripcion(dto.getDescripcion())
                .imagenUrl(dto.getImagenUrl())
                .build();
    }

    public static SandwichResponseDTO toResponseDto(Sandwich sandwichGuardado) {
        return SandwichResponseDTO.builder()
                .id(sandwichGuardado.getId())
                .nombre(sandwichGuardado.getNombre())
                .descripcion(sandwichGuardado.getDescripcion())
                .origen(sandwichGuardado.getOrigen().getNombre())
                .tipo(sandwichGuardado.getTipo().getNombre())
                .ingredientes(
                    sandwichGuardado.getIngredientes()
                            .stream()
                            .map(i -> i.getNombre())
                            .collect(Collectors.toList())
                )
                .vegetariano(sandwichGuardado.isVegetariano())
                .caloriasAproximadas(sandwichGuardado.getCaloriasAproximadas())
                .imagenUrl(sandwichGuardado.getImagenUrl())
                .precio(sandwichGuardado.getPrecio())
                .disponible(sandwichGuardado.isDisponible())
                .build();
    }

    public static SandwichResumenDTO toResumenDto(Sandwich sandwich) {
        return SandwichResumenDTO.builder()
                .id(sandwich.getId())
                .nombre(sandwich.getNombre())
                .descripcion(sandwich.getDescripcion())
                .origen(sandwich.getOrigen().getNombre())
                .tipo(sandwich.getTipo().getNombre())
                .imagenUrl(sandwich.getImagenUrl())
                .precio(sandwich.getPrecio())
                .disponible(sandwich.isDisponible())
                .build();
    }

}
