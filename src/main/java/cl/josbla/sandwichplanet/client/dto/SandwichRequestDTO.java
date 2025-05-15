package cl.josbla.sandwichplanet.client.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SandwichRequestDTO {
    private String nombre;
    private Long tipoId;
    private Long paisId;
    private List<Long> ingredientesIds;
    private boolean vegetariano;
    private int caloriasAproximadas;
}
