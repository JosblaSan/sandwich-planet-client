package cl.josbla.sandwichplanet.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SandwichResumenDTO {
    private Long id;
    private String nombre;
    private String origen;
    private String tipo;
}

