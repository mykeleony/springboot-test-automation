package com.myke.spring.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity(name = "planets")
@NoArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String climate;
    private String terrain;

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public Planet(String climate, String terrain) {
        this.climate = climate;
        this.terrain = terrain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet planet)) return false;
        return getId().equals(planet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
