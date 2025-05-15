package cl.josbla.sandwichplanet.client.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.josbla.sandwichplanet.client.dto.SandwichRequestDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichResponseDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichResumenDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichesMetadataDTO;
import cl.josbla.sandwichplanet.client.services.SandwichService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sandwiches")
@RequiredArgsConstructor
public class SandwichController {

    private final SandwichService sandwichService;

    @PostMapping
    public ResponseEntity<SandwichResponseDTO> crearSandwich(@RequestBody SandwichRequestDTO dto) {
        SandwichResponseDTO creado = sandwichService.crearSandwich(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

     @GetMapping
    public ResponseEntity<SandwichesMetadataDTO> listarSandwiches() {
        SandwichesMetadataDTO response = sandwichService.obtenerTodosLosSandwiches();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<SandwichResumenDTO>> listarResumen() {
        List<SandwichResumenDTO> resumen = sandwichService.listarResumen();
        return ResponseEntity.ok(resumen);
    }
}
