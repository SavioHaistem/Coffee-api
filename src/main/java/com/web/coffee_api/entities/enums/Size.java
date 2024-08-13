package com.web.coffee_api.entities.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Size {
    BIG(3),
    MEDIUM(2),
    SMALL(1);

    public final Integer sizeValue;

    Size(Integer sizeValue) {
        this.sizeValue = sizeValue;
    }

    public static Size valueOf(int value) {
        for (Size size : values()) {
            if (Objects.equals(size.getSizeValue(), value)) {
                return size;
            }
        }
        return null;
    }
}
