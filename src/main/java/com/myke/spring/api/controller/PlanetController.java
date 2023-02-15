package com.myke.spring.api.controller;

import com.myke.spring.domain.model.Planet;
import com.myke.spring.domain.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
@AllArgsConstructor
public class PlanetController {

    private PlanetService planetService;

    @GetMapping
    public List<Planet> findAll() {
        return planetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> get(@PathVariable Long id) {
        return planetService.get(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> getByName(@PathVariable String name) {
        return planetService.getByName(name).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet create(@RequestBody Planet planet) {
        return planetService.create(planet);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        planetService.deleteById(id);
    }

}
