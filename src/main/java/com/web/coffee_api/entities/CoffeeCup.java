package com.web.coffee_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.web.coffee_api.services.exceptions.ArgumentsException;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

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
    @JsonIgnoreProperties(value = {"price"})
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    @ManyToOne
    @JsonIgnoreProperties(value = {"sizes"})
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
        this.size = validSize(cup,size);
    }

    public CupSize validSize(Cup cup, CupSize cupSize) {
        //check if the cup has this size
        //if not, return some size of this cup
        return cup.getSizes().contains(cupSize) ? cupSize : cup.getSizes().stream().findAny().
                orElseThrow(() -> new IllegalArgumentException(
                        "invalid cup size at id: " + cupSize.getId() +
                        " for cup at id: " + cup.getId()));
    }

    public CupSize validSize(CupSize cupSize) {
        //check if the current cup has this size
        //if not, return some size of this cup
        return cup.getSizes().contains(cupSize) ? cupSize : cup.getSizes().stream().findAny().
                orElseThrow(() -> new ArgumentsException(
                        "invalid cup size at id: " + cupSize.getId() + " for cup at id: " + cup.getId(),
                        "caused by validateSize method in CoffeeCup entity, cup size id: " + cupSize.getId(),
                        HttpStatus.BAD_REQUEST
                ));
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

    public void setSize(CupSize checkSize) {
        this.size = checkSize;
    }

    public Double getPrice() {
        return (coffee.getPrice() * size.getSize().getVal()) + cup.getPrice();
    }
}
