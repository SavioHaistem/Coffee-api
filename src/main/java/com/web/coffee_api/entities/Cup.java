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
@Table(name = "cup_tb")
public class Cup implements Serializable {
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
    @OneToOne
    @JoinColumn(name = "coffee_id",referencedColumnName = "id")
    private Coffee coffee;
    @ManyToOne
    @JoinColumn(name = "cup_size_id")
    @lombok.NonNull
    private CupSize cupSize;
}
