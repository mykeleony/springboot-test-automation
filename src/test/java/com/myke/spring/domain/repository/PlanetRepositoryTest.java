package com.myke.spring.domain.repository;

import com.myke.spring.domain.model.Planet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static com.myke.spring.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void resetPlanetId() {
        PLANET.setId(null);
    }

    @Test
    public void createPlanet_withValidData_ReturnsPlanet() {
        Planet planet = planetRepository.save(PLANET);

        Planet sut = testEntityManager.find(Planet.class, planet.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(planet.getName());
        assertThat(sut.getClimate()).isEqualTo(planet.getClimate());
        assertThat(sut.getTerrain()).isEqualTo(planet.getTerrain());
    }

    @Test
    public void createPlanet_withInvalidData_ThrowsException() {
        Planet emptyPlanet = new Planet();
        Planet invalidPlanet = new Planet("", "", "");

        assertThatThrownBy(() -> planetRepository.save(emptyPlanet)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> planetRepository.save(invalidPlanet)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createPlanet_withExistingName_ThrowsException() {
        Planet planet = testEntityManager.persistFlushFind(PLANET);
        testEntityManager.detach(planet);
        planet.setId(null);

        assertThatThrownBy(() -> planetRepository.save(planet)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_withExistingId_ReturnsPlanet() {
        Planet planet = testEntityManager.persistFlushFind(PLANET);

        Optional<Planet> optionalPlanet = planetRepository.findById(planet.getId());

        assertThat(optionalPlanet).isNotEmpty();
        assertThat(optionalPlanet.get()).isEqualTo(planet);
    }

    @Test
    public void getPlanet_withUnexistingId_ReturnsEmpty() {
        Optional<Planet> optionalPlanet = planetRepository.findById(1L);

        assertThat(optionalPlanet).isEmpty();
    }

    @Test
    public void getPlanet_WithExistingName_ReturnsPlanet() {
        Planet planet = testEntityManager.persistFlushFind(PLANET);

        Optional<Planet> planetOptional = planetRepository.findByName(planet.getName());

        assertThat(planetOptional).isNotEmpty();
        assertThat(planetOptional.get()).isEqualTo(planet);
    }

    @Test
    public void getPlanet_WithUnexistingName_ReturnsEmpty() {
        Optional<Planet> planetOptional = planetRepository.findByName("name");

        assertThat(planetOptional).isEmpty();
    }
}
