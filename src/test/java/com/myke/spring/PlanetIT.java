package com.myke.spring;

import com.myke.spring.domain.model.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.myke.spring.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(scripts = "/insert_planets.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/clear_planets.sql", executionPhase = AFTER_TEST_METHOD)
public class PlanetIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void createPlanet_ReturnsCreated() {
        ResponseEntity<Planet> sut = restTemplate.postForEntity("/planets", PLANET, Planet.class);

        Planet planetResponse = sut.getBody();

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(planetResponse.getId()).isNotNull();
        assertThat(planetResponse.getName()).isEqualTo(PLANET.getName());
        assertThat(planetResponse.getTerrain()).isEqualTo(PLANET.getTerrain());
        assertThat(planetResponse.getClimate()).isEqualTo(PLANET.getClimate());
    }

    @Test
    public void getPlanet_ReturnsPlanet() {
        ResponseEntity<Planet> sut = restTemplate.getForEntity("/planets/1", Planet.class);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(TATOOINE);
    }

    @Test
    public void getPlanetByName_ReturnsPlanet() {
        Planet example = TATOOINE;

        ResponseEntity<Planet> sut = restTemplate
                .getForEntity("/planets/name/" + TATOOINE.getName(), Planet.class);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(TATOOINE);
    }

    @Test
    public void listPlanets_ReturnsAllPlanets() {
        ResponseEntity<Planet[]> sut = restTemplate.getForEntity("/planets", Planet[].class);
        Planet[] response = sut.getBody();

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response).isNotNull();
        assertThat(response.length).isEqualTo(3);
        assertThat(response[0]).isEqualTo(TATOOINE);
        assertThat(response[1]).isEqualTo(ALDERAAN);
        assertThat(response[2]).isEqualTo(YAVINIV);
    }

    @Test
    public void listPlanets_byClimate_ReturnsPlanets() {
        String aridClimate = TATOOINE.getClimate();

        ResponseEntity<Planet[]> sut = restTemplate
                .getForEntity(String.format("/planets?climate=%s", aridClimate), Planet[].class);
        Planet[] response = sut.getBody();

        assertThat(response).isNotNull();
        assertThat(response.length).isEqualTo(1);
        assertThat(response[0]).isEqualTo(TATOOINE);
    }

    @Test
    public void listPlanets_byTerrain_ReturnsPlanets() {
        String desertClimate = TATOOINE.getTerrain();

        ResponseEntity<Planet[]> sut = restTemplate
                .getForEntity(String.format("/planets?terrain=%s", desertClimate), Planet[].class);
        Planet[] response = sut.getBody();

        assertThat(response).isNotNull();
        assertThat(response.length).isEqualTo(1);
        assertThat(response[0]).isEqualTo(TATOOINE);
    }

    @Test
    public void deletePlanet_ReturnsNoContent() {
        ResponseEntity<Void> sut = restTemplate.exchange("/planets/" + TATOOINE.getId(), HttpMethod.DELETE, null, Void.class);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(sut.getBody()).isNull();
    }

}
