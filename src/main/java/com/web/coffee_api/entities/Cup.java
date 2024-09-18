package com.web.coffee_api.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cup_tb")
public class Cup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cup_size",
            joinColumns = @JoinColumn(name = "cup_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private final Set<CupSize> sizes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cup")
    private final Set<CoffeeCup> coffeeCups = new HashSet<>();

    public Cup() {
    }

    public Cup(Long id, String name) {
        this.id = id;
        this.name = name;
        this.price = 0.0;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<CupSize> getSizes() {
        return sizes;
    }

    public Set<CoffeeCup> getCoffeeCups() {
        return coffeeCups;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cup cup)) return false;

        return Objects.equals(id, cup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Double getPrice() {
        return price;
    }
}
