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
@Table(name = "cup_size_tb")
public class CupSize implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private int size;
}
