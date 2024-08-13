package com.web.coffee_api.entities;

import com.web.coffee_api.entities.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "cup_size_tb")
public class CupSize implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @lombok.NonNull
    private int size;

    @ManyToMany(mappedBy = "cupSizes", cascade = CascadeType.ALL)
    private Set<Cup> cups = new HashSet<>();

    public CupSize(Size size) {
        this.size = size.getSizeValue();
    }
}
