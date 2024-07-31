package com.web.coffee_api.entities;
import lombok.*;

import java.util.ArrayList;

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
