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
public class SandwichResponseDTO {
    private Long id;
    private String nombre;
    private String origen; // nombre del país
    private String tipo;   // nombre del tipo
    private List<String> ingredientes;
    private boolean vegetariano;
    private int caloriasAproximadas;
}
