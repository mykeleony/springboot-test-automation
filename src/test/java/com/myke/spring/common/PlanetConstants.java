package com.myke.spring.common;

import com.myke.spring.domain.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("Planet", "Arid", "Desert");
    public static final Planet INVALID_PLANET = new Planet("", "", "");

    public static final Planet TATOOINE = new Planet(1L, "Tatooine", "Arid", "Desert");
    public static final Planet ALDERAAN = new Planet(2L, "Alderaan", "Temperate", "Grasslands, mountains");
    public static final Planet YAVINIV = new Planet(3L, "Yavin IV", "Temperate, tropical", "Jungle, rainforest");

    public static final List<Planet> PLANETS = new ArrayList<>() {
        {
            add(TATOOINE);
            add(ALDERAAN);
            add(YAVINIV);
        }
    };
}
