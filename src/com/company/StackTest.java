package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
//Start EX 21, 22
public class StackTest {
    private Object myLock;
    public static void main(String[] args) throws InterruptedException {
        StackTest stackTest = new StackTest();
        (new Thread(() -> {
            System.out.println("Thread 1:" + stackTest.getObj());
            stackTest.push("1");
        })).start();
        (new Thread(() -> {
            System.out.println("Thread 2:" + stackTest.getObj());
            stackTest.push("2");
        })).start();
        (new Thread(() -> {
            try {
                Thread.sleep(10);
                System.out.println("Thread 3:" + stackTest.getObj());
                stackTest.push("3");
            } catch (InterruptedException e) {

            }
        })).start();
        (new Thread(() -> {
            System.out.println("Thread 4:" + stackTest.getObj());
            stackTest.push("4");
        })).start();
        (new Thread(() -> {
            System.out.println("Thread 5:" + stackTest.getObj());
            stackTest.push("5");
        })).start();
        Thread.sleep(1000);
        System.out.println(stackTest.getObj());
    }
    public void push(Object newValue) {
        synchronized(myLock) {
            myLock = newValue;
        }
    }
    public void push2(Object newValue) {
        synchronized (new ReentrantLock()) {
            System.out.println("DMDMDM");
            myLock = newValue;
        }
    }
    public Object getObj() {
        return myLock;
    }
}
