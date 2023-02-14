package com.myke.spring.api.controller;

import com.myke.spring.domain.model.Planet;
import com.myke.spring.domain.service.PlanetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planets")
@AllArgsConstructor
public class PlanetController {

    private PlanetService planetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet create(@RequestBody Planet planet) {
        return planetService.create(planet);
    }

}
