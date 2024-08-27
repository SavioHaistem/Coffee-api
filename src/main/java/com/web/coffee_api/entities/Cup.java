package com.web.coffee_api.entities;
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

    @ManyToMany
    @JoinTable(name = "cup_size",
            joinColumns = @JoinColumn(name = "cup_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private final Set<CupSize> sizes = new HashSet<>();

    public Cup() {
    }

    public Cup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSize(CupSize size) {
        sizes.add(size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CupSize> getSizes() {
        return sizes;
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
}
