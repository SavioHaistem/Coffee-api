package com.web.coffee_api.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "size_tb")
public class CupSize implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Size size;

    @ManyToMany(mappedBy = "sizes")
    private Set<Cup> cups = new HashSet<>();

    public CupSize() {
    }

    public CupSize(Long id, Size size) {
        this.id = id;
        this.size = size;
    }

    public void addCup(Cup cup) {
        this.cups.add(cup);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Set<Cup> getCups() {
        return cups;
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
