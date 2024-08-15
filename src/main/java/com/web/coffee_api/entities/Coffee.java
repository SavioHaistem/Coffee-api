package com.web.coffee_api.entities;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coffees_tb")
public class Coffee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "coffees")
    private Set<Cup> cups = new HashSet<>();

    private Double price;

    public Coffee() {
    }

    public Coffee(Long id, String name, String description, Set<Cup> cups, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cups = cups;
        this.price = price;
    }

    public Coffee(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Cup> getCups() {
        return cups;
    }

    public void setCups(Set<Cup> cups) {
        this.cups = cups;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
