package com.campanha.rpg.types;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Pair implements Serializable {
    private static final long serialVersionUID = 1L;

    private int first;
    private int second;

    public Pair() {
        this.first = 0;
        this.second = 0;
    }

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public Pair(int[] values) {
        if (values == null || values.length != 2) {
            throw new IllegalArgumentException("Array must contain exactly two elements.");
        }
        this.first = values[0];
        this.second = values[1];
    }

    @JsonCreator
    public static Pair fromJson(int[] values) {
        if (values == null || values.length != 2) {
            throw new IllegalArgumentException("Array must contain exactly two elements");
        }
        return new Pair(values[0], values[1]);
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
