package com.company;

import com.company.ChapSix.ChapSiz;
import javafx.application.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static Integer sum = 0;
    public static void main(String[] args) {
        //ChapTwo chapTwo = new ChapTwo();
        //ChapThree chapThree = new ChapThree();
        //ChapSiz chapSiz = new ChapSiz();
//        final LongAdder count = new LongAdder();
//        count.reset();
//        ExecutorService executor = Executors.newCachedThreadPool();
//        System.out.println(count.sum());
//        for (int i = 1; i < 10; i++) {
//            executor.execute(() -> {
//                System.out.println("Start increase");
//                //Main.sum++;
//                count.increment();
//            });
//        }
//        try {
//            Thread.sleep(1000);
//            throw new InterruptedException();
//        } catch (InterruptedException ex) {
//            System.out.println("EXCEADASD");
//            Thread.currentThread().interrupt();
//        }
//        System.out.println(count.sum());
        //System.out.println(Main.sum);
        new ChapEight();
    }
}
