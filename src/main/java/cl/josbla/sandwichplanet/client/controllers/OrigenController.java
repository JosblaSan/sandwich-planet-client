package cl.josbla.sandwichplanet.client.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.josbla.sandwichplanet.client.dto.OrigenDTO;
import cl.josbla.sandwichplanet.client.services.OrigenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/origenes")
@RequiredArgsConstructor
public class OrigenController {

    private final OrigenService origenService;

    @GetMapping
    public List<OrigenDTO> getAll() {
        return origenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrigenDTO> getById(@PathVariable Long id) {
        return origenService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrigenDTO> create(@RequestBody OrigenDTO dto) {
        return ResponseEntity.ok(origenService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrigenDTO> update(@PathVariable Long id, @RequestBody OrigenDTO dto) {
        return origenService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        origenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
