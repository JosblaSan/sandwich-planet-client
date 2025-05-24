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

import cl.josbla.sandwichplanet.client.dto.IngredienteDTo;
import cl.josbla.sandwichplanet.client.services.IngredienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {
    private final IngredienteService ingredienteService;

    @GetMapping
    public List<IngredienteDTo> getAll() {
        return ingredienteService.findAll();
    }

     @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTo> getById(@PathVariable Long id) {
        return ingredienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IngredienteDTo> create(@RequestBody IngredienteDTo dto) {
        return ResponseEntity.ok(ingredienteService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTo> update(@PathVariable Long id, @RequestBody IngredienteDTo dto) {
        return ingredienteService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
