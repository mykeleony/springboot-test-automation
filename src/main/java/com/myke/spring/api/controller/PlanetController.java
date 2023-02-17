package com.myke.spring.api.controller;

import com.myke.spring.domain.model.Planet;
import com.myke.spring.domain.repository.PlanetRepository;
import com.myke.spring.domain.service.PlanetService;
import jakarta.validation.Valid;
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
    private PlanetRepository planetRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Planet> get(@PathVariable Long id) {
        return planetService.get(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Planet>> list(@RequestParam(required = false) String climate,
                                             @RequestParam(required = false) String terrain) {
        List<Planet> planets = planetService.list(climate, terrain);

        return ResponseEntity.ok(planets);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> getByName(@PathVariable String name) {
        return planetService.getByName(name).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet create(@RequestBody @Valid Planet planet) {
        if (planetRepository.existsByName(planet.getName()))
            throw new RuntimeException();

        return planetService.create(planet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Planet> remove(@PathVariable Long id) {
        if(!planetRepository.existsById(id))
            return ResponseEntity.notFound().build();

        planetService.remove(id);

        return ResponseEntity.noContent().build();
    }

}
