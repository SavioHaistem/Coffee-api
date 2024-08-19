package com.web.coffee_api.entities;

import lombok.Getter;

@Getter
public enum Size {
    SMALL(1),
    MEDIUM(2),
    BIG(3);

    private int size;

    Size(int size) {
    }

    public Size valueOf(int size) {
        Size search = null;
        for (Size cupSize : values()) {
            search = cupSize.size == size ? cupSize : null;
        }
        return search;
    }
}
