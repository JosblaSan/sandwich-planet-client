package cl.josbla.sandwichplanet.client.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import cl.josbla.sandwichplanet.client.dto.PreferenceRequestDTo;
import cl.josbla.sandwichplanet.client.dto.PreferenceResponseDTo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MercadoPagoService {
    /**
     * Crea una preferencia en Mercado Pago y retorna su ID.
     * 
     * @param requestBody El JSON con la preferencia, por ejemplo generado a partir de tu modelo PreferenciaRequest.
     * @return El preference_id de Mercado Pago.
     * @throws Exception en caso de error.
     */
    public PreferenceResponseDTo crearPreferencia(PreferenceRequestDTo requestBody) throws Exception {
        List<PreferenceItemRequest> items = new ArrayList<>();

        requestBody.getItems().forEach(item -> {
            PreferenceItemRequest itemRequest =
            PreferenceItemRequest.builder()
                .title(item.getTitle())
                .quantity(item.getQuantity())
                .currencyId("CLP")
                .unitPrice(new BigDecimal(item.getUnit_price().toString()))
                .build();
            items.add(itemRequest);
        });

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
            .success("https://localhost:4200/success")
            .pending("https://localhost:4200/pending")
            .failure("https://localhost:4200/failure")
            .build();
        
        PreferenceRequest preferenceRequest = PreferenceRequest
            .builder()
            .items(items)
            .backUrls(backUrls)
            .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        PreferenceResponseDTo responseDto = new PreferenceResponseDTo();
        responseDto.setPreference_id(preference.getId());
        return responseDto;
    }
}
