package com.web.coffee_api.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coffees_tb")
public class Coffee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @lombok.NonNull
    private String name;
    @lombok.NonNull
    private String description;
    @lombok.NonNull
    @OneToOne(mappedBy = "coffee")
    private Cup cup;
    @lombok.NonNull
    private Double price;
}
