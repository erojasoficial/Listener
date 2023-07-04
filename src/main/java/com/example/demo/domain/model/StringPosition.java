package com.example.demo.domain.model;

public class StringPosition {
    private final String value;
    private final int position;

    public StringPosition(String value, int position) {
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}
