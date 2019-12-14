package com.kyntsevichvova.wtlab.bean;

import java.io.Serializable;
import java.util.Comparator;

public class Horse extends BaseEntity implements Serializable, Comparable<Horse> {
    private int id;
    private String name;
    private int wins;

    private final static Comparator<Horse> nameComparator =
            Comparator.comparing(Horse::getName).thenComparing(Horse::getWins);
    private final static Comparator<Horse> winsComparator =
            Comparator.comparing(Horse::getWins).thenComparing(Horse::getName);

    public Horse() {
    }

    public Horse(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Horse o) {
        return nameComparator.compare(this, o);
    }

    public static Comparator<Horse> getNameComparator() {
        return nameComparator;
    }

    public static Comparator<Horse> getWinsComparator() {
        return winsComparator;
    }

    @Override
    public String toString() {
        return "Horse: " + name + ", " + wins;
    }
}
