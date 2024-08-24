package com.web.coffee_api.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
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

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cup_coffee_tb",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id", referencedColumnName = "id"))
    private final Set<Coffee> coffees = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cup_size_tb",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "size_id", referencedColumnName = "id"))
    private final Set<CupSize> sizes = new HashSet<>();

    public Cup() {
    }

    public Cup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Coffee> getCoffees() {
        return coffees;
    }

    public Set<CupSize> getSizes() {
        return sizes;
    }
}
