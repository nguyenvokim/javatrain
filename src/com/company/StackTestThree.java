package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StackTestThree {
    public static void main(String[] args) throws InterruptedException {
        StackTestThree stackTestThree = new StackTestThree();
        (new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                stackTestThree.push(i);
            }
        })).start();
        (new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                stackTestThree.push(i);
            }
        })).start();
        (new Thread(() -> {
            for (int i = 21; i <= 30; i++) {
                stackTestThree.push(i);
            }
        })).start();
        Thread.sleep(1000);
        List<Object> arr = Arrays.asList(stackTestThree.getValues());
        System.out.println("Actual Size:" + arr.size());
        System.out.println("Actual:" + stackTestThree.getSize());
        System.out.println("Out" + arr);
    }

    private Object[] values = new Object[10];
    private int size;

    public void push(Object newValue) {
        synchronized (values) {
            if (size == values.length)
                values = Arrays.copyOf(values, 2 * size);
            values[size] = newValue;
            size++;
        }
    }
    public Object[] getValues() {
        return values;
    }

    public int getSize() {
        return size;
    }
}
