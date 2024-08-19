package com.web.coffee_api.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private Set<Coffee> coffees = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cup_size_tb",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "size_id", referencedColumnName = "id"))
    private Set<CupSize> sizes = new HashSet<>();

    public Cup(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
