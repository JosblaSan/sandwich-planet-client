package cl.josbla.sandwichplanet.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredienteDTo {
    private Long id;
    private String nombre;
    private boolean esVegetariano;
}
