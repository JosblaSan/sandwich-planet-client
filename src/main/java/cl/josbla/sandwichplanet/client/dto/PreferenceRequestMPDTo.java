package cl.josbla.sandwichplanet.client.dto;

import java.util.List;
import java.util.Map;

import cl.josbla.sandwichplanet.client.models.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceRequestMPDTo {
    private List<Item> items;
     private Map<String, String> back_urls;
     private String auto_return;
}
