package cl.josbla.sandwichplanet.client.mapper;

import cl.josbla.sandwichplanet.client.dto.IngredienteDTo;
import cl.josbla.sandwichplanet.client.models.Ingrediente;

public class IngredienteMapper {
    public static IngredienteDTo toDto(Ingrediente ingrediente) {
        return IngredienteDTo.builder()
                .id(ingrediente.getId())
                .nombre(ingrediente.getNombre())
                .esVegetariano(ingrediente.isEsVegetariano())
                .build();
    }

    public static Ingrediente toEntity(IngredienteDTo dto) {
        return Ingrediente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .esVegetariano(dto.isEsVegetariano())
                .build();
    }
}
