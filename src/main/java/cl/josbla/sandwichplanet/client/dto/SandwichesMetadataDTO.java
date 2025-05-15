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
public class SandwichesMetadataDTO {
    private List<SandwichResponseDTO> sandwiches;
    private Metadata metadata;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata {
        private int totalSandwiches;
        private List<String> paisesRepresentados;
        private List<String> tipos;
    }
}
