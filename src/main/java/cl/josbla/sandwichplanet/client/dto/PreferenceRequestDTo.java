package cl.josbla.sandwichplanet.client.dto;

import java.util.List;

import cl.josbla.sandwichplanet.client.models.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceRequestDTo {
     private List<Item> items;
}
