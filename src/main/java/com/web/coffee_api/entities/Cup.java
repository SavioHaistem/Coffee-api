package com.web.coffee_api.entities;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cup_tb")
public class Cup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private CupSize size;

    public Cup() {
    }

    public Cup(Long id, String name, CupSize size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CupSize getSize() {
        return size;
    }

    public void setSize(CupSize size) {
        this.size = size;
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
