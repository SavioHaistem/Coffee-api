package com.web.coffee_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "size_tb")
public class CupSize implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Size size;
    @JsonIgnore
    @ManyToMany(mappedBy = "sizes")
    private Set<Cup> cups = new HashSet<>();

    public CupSize() {
    }

    public CupSize(Long id, Size size) {
        this.id = id;
        this.size = size;
    }

    public CupSize(Long id, Size size, Set<Cup> cups) {
        this.id = id;
        this.size = size;
        this.cups = cups;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CupSize cupSize)) return false;

        return Objects.equals(id, cupSize.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
