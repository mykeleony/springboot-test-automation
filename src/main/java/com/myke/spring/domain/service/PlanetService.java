package com.myke.spring.domain.service;

import com.myke.spring.domain.model.Planet;
import com.myke.spring.domain.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    public Planet create(Planet planet) {
        return planetRepository.save(planet);
    }

}
