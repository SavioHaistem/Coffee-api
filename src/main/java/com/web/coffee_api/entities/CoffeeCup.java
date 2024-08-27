package com.web.coffee_api.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "coffee_cup")
public class CoffeeCup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "coffee_id")
    private Coffee coffee;

    @ManyToOne
    @Column(name = "cup_id")
    private Cup cup;

    @ManyToOne
    @Column(name = "size_id")
    private Size size;

    public CoffeeCup() {
    }

    public CoffeeCup(Long id, Coffee coffee, Cup cup, Size size) {
        this.id = id;
        this.coffee = coffee;
        this.cup = cup;
        this.size = size;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Long getId() {
        return id;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
