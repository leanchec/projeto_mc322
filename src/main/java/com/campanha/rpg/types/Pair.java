package com.campanha.rpg.types;

public class Pair {
    private int first;
    private int second;

    public Pair(int l_first, int l_second) {
        this.first = l_first;
        this.second = l_second;
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
