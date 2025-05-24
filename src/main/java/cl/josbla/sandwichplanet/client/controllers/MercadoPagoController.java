package cl.josbla.sandwichplanet.client.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.josbla.sandwichplanet.client.dto.PreferenceRequestDTo;
import cl.josbla.sandwichplanet.client.dto.PreferenceResponseDTo;
import cl.josbla.sandwichplanet.client.services.MercadoPagoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class MercadoPagoController {

    private final MercadoPagoService mercadoPagoService;

    @PostMapping("/crear-preferencia")
    public ResponseEntity<PreferenceResponseDTo> crearPreferencia(@RequestBody PreferenceRequestDTo request) {
        try {
            // El requestBody debe contener un JSON con la información de la preferencia.
            // Puedes construirlo a partir de tus modelos usando ObjectMapper o recibirlo como String.
            PreferenceResponseDTo response = mercadoPagoService.crearPreferencia(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new PreferenceResponseDTo(e.getMessage()));
        }
        
    }
}
