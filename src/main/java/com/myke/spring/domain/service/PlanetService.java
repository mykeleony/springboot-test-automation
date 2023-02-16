package com.myke.spring.domain.service;

import com.myke.spring.domain.common.QueryBuilder;
import com.myke.spring.domain.model.Planet;
import com.myke.spring.domain.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    public Planet create(Planet planet) {
        return planetRepository.save(planet);
    }

    public Optional<Planet> get(Long id) {
        return planetRepository.findById(id);
    }

    public Optional<Planet> getByName(String name) {
         return planetRepository.findByName(name);
    }

    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    @Transactional
    public void remove(Long id) {
        planetRepository.deleteById(id);
    }

    public List<Planet> list(String climate, String terrain) {
        Example<Planet> query = QueryBuilder.createQuery(new Planet(climate, terrain));
        return planetRepository.findAll(query);
    }
}
