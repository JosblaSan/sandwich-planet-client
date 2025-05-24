package cl.josbla.sandwichplanet.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String title;
    private Integer quantity;
    private String currency_id;
    private Double unit_price;
}
