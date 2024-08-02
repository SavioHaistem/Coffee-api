package com.web.coffee_api.entities;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coffee {
    private Long id;
    private String name;
    private String description;
    private Double quantity;
    private Double price;
}
