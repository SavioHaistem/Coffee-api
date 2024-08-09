package com.web.coffee_api.entities.enums;

import lombok.Getter;

@Getter
public enum Size {
    BIG(3),
    MEDIUM(2),
    SMALL(1);

    public final Integer sizeValue;

    private Size(Integer sizeValue) {
        this.sizeValue = sizeValue;
    }

    public Size fromSizeValue(Integer value) {
        for (Size size : values()) {
            if (size.getSizeValue() == value) {
                return size;
            }
        }
        return null;
    }
}
