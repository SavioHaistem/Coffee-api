package com.web.coffee_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "coffee_cup_tb")
public class CoffeeCup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    @ManyToOne
    @JoinColumn(name = "cup_id")
    private Cup cup;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private CupSize size;

    public CoffeeCup() {
    }

    public CoffeeCup(Long id, Coffee coffee, Cup cup, CupSize size) {
        this.id = id;
        this.coffee = coffee;
        this.cup = cup;
        this.size = checkSize(cup,size) ? size : cup.getSizes().stream().findAny().orElseThrow();
    }

    public boolean checkSize(Cup cup, CupSize cupSize) {
        return cup.getSizes().contains(cupSize);
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

    public CupSize getSize() {
        return size;
    }

    public void setSize(CupSize size) {
        if (this.cup.getSizes().contains(size)) {
            this.size = size;
        } else {
            //throw invalid size error
        }
    }
}
