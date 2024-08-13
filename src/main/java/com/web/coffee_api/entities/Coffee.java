package com.web.coffee_api.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "coffees_tb")
public class Coffee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @lombok.NonNull
    private String name;

    @lombok.NonNull
    private String description;

    @ManyToMany(mappedBy = "coffees")
    private Set<Cup> cups = new HashSet<>();

    @lombok.NonNull
    private Double price;
}
