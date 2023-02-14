package com.myke.spring.domain;

import com.myke.spring.domain.model.Planet;
import com.myke.spring.domain.service.PlanetService;
import lombok.AllArgsConstructor;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static com.myke.spring.common.PlanetConstants.PLANET;

@SpringBootTest(classes = PlanetService.class)
@AllArgsConstructor
public class PlanetServiceTest {

    private PlanetService planetService;

    // Naming schema: operation_state_expectedOutput
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        // System Under Test
        Planet sut = planetService.create(PLANET);

        assertThat(sut).isEqualTo(PLANET);
    }

}
