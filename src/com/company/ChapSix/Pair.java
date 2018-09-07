package com.company.ChapSix;

public class Pair<E extends Comparable<E>> implements Comparable<Pair<E>> {
    protected E first;
    protected E second;
    public Pair(E first, E second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }
    public E max() {
        return first.compareTo(second) >= 0 ? first : second;
    }
    public E min() {
        return first.compareTo(second) >= 0 ? second : first;
    }

    @Override
    public int compareTo(Pair<E> o) {
        return getFirst().compareTo(o.getFirst());
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
