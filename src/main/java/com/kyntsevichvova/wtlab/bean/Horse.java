package com.kyntsevichvova.wtlab.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Comparator;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Horse extends BaseEntity implements Serializable, Comparable<Horse> {
    private String name;
    private int wins;

    private final static Comparator<Horse> nameComparator =
            Comparator.comparing(Horse::getName).thenComparing(Horse::getWins);
    private final static Comparator<Horse> winsComparator =
            Comparator.comparing(Horse::getWins).thenComparing(Horse::getName);

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
}
