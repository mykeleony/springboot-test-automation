package com.myke.spring.common;

import com.myke.spring.domain.model.Planet;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("Planet", "Arid", "Desert");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
}
