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
                .build();
    }

    public static SandwichResponseDTO toResponseDto(Sandwich sandwichGuardado) {
        return SandwichResponseDTO.builder()
                .id(sandwichGuardado.getId())
                .nombre(sandwichGuardado.getNombre())
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
                .build();
    }

    public static SandwichResumenDTO toResumenDto(Sandwich sandwich) {
        return SandwichResumenDTO.builder()
                .id(sandwich.getId())
                .nombre(sandwich.getNombre())
                .origen(sandwich.getOrigen().getNombre())
                .tipo(sandwich.getTipo().getNombre())
                .build();
    }

}
