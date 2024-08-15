package com.web.coffee_api.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "coffees_tb")
public class Coffee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String description;
    private Double price;
}
