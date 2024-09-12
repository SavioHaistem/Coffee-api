package com.web.coffee_api.entities;

public enum Size {
    SMALL(1),
    MEDIUM(2),
    BIG(3);

    private final int val;

    Size(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
