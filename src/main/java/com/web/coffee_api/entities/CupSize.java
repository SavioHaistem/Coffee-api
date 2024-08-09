package com.web.coffee_api.entities;

import com.web.coffee_api.entities.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cup_size_tb")
public class CupSize implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @lombok.NonNull
    private Size size;
    @lombok.NonNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cupSize")
    private List<Cup> cups;
}
