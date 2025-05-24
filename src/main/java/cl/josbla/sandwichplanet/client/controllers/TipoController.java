package cl.josbla.sandwichplanet.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.josbla.sandwichplanet.client.dto.TipoDto;
import cl.josbla.sandwichplanet.client.services.TipoService;

@RestController
@RequestMapping("/api/tipos")
public class TipoController {
     @Autowired
    private TipoService tipoService;

    @GetMapping
    public List<TipoDto> getAll() {
        return tipoService.findAll();
    }

    @GetMapping("/{id}")
    public TipoDto getById(@PathVariable Long id) {
        return tipoService.findById(id);
    }

    @PostMapping
    public TipoDto create(@RequestBody TipoDto tipoDto) {
        return tipoService.create(tipoDto);
    }

    @PutMapping("/{id}")
    public TipoDto update(@PathVariable Long id, @RequestBody TipoDto tipoDto) {
        return tipoService.update(id, tipoDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tipoService.delete(id);
    }
}
