package com.company.ChapSix;

import java.util.ArrayList;

public class Stack<E> {
    protected ArrayList<E> arrayList;
    public Stack() {
        arrayList = new ArrayList<>();
    }
    public void push(E item) {
        arrayList.add(item);
    }
    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            return arrayList.remove(arrayList.size() - 1);
        }
    }
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
}
