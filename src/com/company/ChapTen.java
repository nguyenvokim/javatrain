package com.company;

import javafx.concurrent.Task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

public class ChapTen {

    public static volatile int count = 0;

    public static void main(String[] args) {
        //Start EX 7
        ConcurrentHashMap<String, Long> longs = new ConcurrentHashMap<>();
        longs.put("Num1", Long.valueOf("10"));
        longs.put("Num2", Long.valueOf("100"));
        longs.put("Num3", Long.valueOf("11"));
        longs.put("Num4", Long.valueOf("-111"));
        longs.put("Num5", Long.valueOf("-30"));
        Map.Entry result = longs.reduceEntries(1, (prevVal, nextVal) -> {
            //System.out.println(prevVal);
            //System.out.println(nextVal);
            if (prevVal.getValue() > nextVal.getValue()) {
                return prevVal;
            } else {
                return nextVal;
            }
        });
        System.out.println("EX 7: " + result.getKey());
        //END ex 7
        //Start EX 8
        long startTime = System.currentTimeMillis();
        AtomicLong atomicLong = new AtomicLong();
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 1000; i++) {
            (new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    atomicLong.incrementAndGet();
                }
            })).start();
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(atomicLong.get());
        System.out.println("EX8: AtomicLong take:" + elapsedTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            (new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    longAdder.increment();
                }
            })).start();
        }
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println(longAdder.sum());
        System.out.println("EX 8: LongAdder take:" + elapsedTime);
        //End EX 8
        //Start EX 9
        LongAccumulator longAccumulatorMin = new LongAccumulator((v1, v2) -> {
            if (v1 > v2) {
                return v2;
            } else {
                return v1;
            }
        }, 0);
        LongAccumulator longAccumulatorMax = new LongAccumulator((v1, v2) -> {
            if (v1 < v2) {
                return v2;
            } else {
                return v1;
            }
        }, 0);
        (new Random().longs(10, -100, 100))
                .parallel()
                .forEach(l -> {
                    System.out.print(l + " ");
            longAccumulatorMin.accumulate(l);
            longAccumulatorMax.accumulate(l);
        });
        System.out.println("EX9: Max is " + longAccumulatorMax.get() + ", Min is " + longAccumulatorMin.get());
        //End Ex 9
        //Start EX 19
        System.out.println("Ex 19:");
        Stack stack = new Stack();
        AtomicLong atomicLong1 = new AtomicLong();
        atomicLong1.incrementAndGet();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            Runnable task = () -> {
                stack.push(atomicLong1.incrementAndGet());
            };
            executor.execute(task);
        }
        for (int i = 1; i <= 100; i++) {
            System.out.println(stack.pop());
        }
    }

    public static class Stack {
        class Node { Object value; Node next; };
        private Node top;

        public synchronized void push(Object newValue) {
            Node n = new Node();
            n.value = newValue;
            n.next = top;
            top = n;
        }

        public synchronized Object pop() {
            if (top == null) return null;
            Node n = top;
            top = n.next;
            return n.value;
        }
    }
}
