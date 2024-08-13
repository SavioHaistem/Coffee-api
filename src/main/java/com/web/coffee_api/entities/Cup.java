package com.web.coffee_api.entities;

import com.web.coffee_api.entities.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "cup_tb")
public class Cup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @lombok.NonNull
    private String name;

    @ManyToMany
    @JoinTable(name = "cup_coffee_tb",
            joinColumns = @JoinColumn(name = "cup_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id"))
    private Set<Coffee> coffees = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "cups_cup_size_tb",
            joinColumns = @JoinColumn(name = "cup_id"),
            inverseJoinColumns = @JoinColumn(name = "cup_size_id"))
    private Set<CupSize> cupSizes = new HashSet<>();
}
