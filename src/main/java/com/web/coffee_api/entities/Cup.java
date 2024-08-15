package com.web.coffee_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.coffee_api.entities.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "cup_tb")
public class Cup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
