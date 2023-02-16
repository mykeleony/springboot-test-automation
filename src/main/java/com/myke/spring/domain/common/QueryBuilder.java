package com.myke.spring.domain.common;

import com.myke.spring.domain.model.Planet;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder {

    public static Example<Planet> createQuery(Planet planet) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();

        return Example.of(planet, exampleMatcher);
    }

}
