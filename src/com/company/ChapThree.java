package com.company;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChapThree {
    public ChapThree() {
        System.out.println("BEGIN EXERCISES CHAP 3 ");
        Employee employee1 = new Employee(1.0, "Nguyen");
        Employee employee2 = new Employee(2.0, "Dung");
        Employee employee3 = new Employee(6.0, "Ong");
        Employee[] employees = {employee1, employee2, employee3};
        System.out.println("EXERCISES 1,2");
        System.out.println("Average:" + Employee.average(employees));
        System.out.println("Largest:" + ((Employee) Employee.largest(employees)).getName());
        System.out.println("EXERCISES 8");
        Greeter greeter1 = new Greeter(2, "Nguyen");
        Greeter greeter2 = new Greeter(3, "Dung");
        (new Thread(greeter1)).start();
        (new Thread(greeter2)).start();
        System.out.println("EXERCISES 9");
        Greeter.runTogether(greeter1, greeter2);
        Greeter.runInOrder(greeter1, greeter2);

        System.out.println("EXERCISES 10");
        File files = new File("/");
        File[] direcs = files.listFiles(File::isDirectory);
        for (File direc: direcs) {
            System.out.println(direc);
        }
    }
    public interface Measurable {
        public double getMeasure();
    }
    public static class Employee implements Measurable {
        private double measure;
        private String name;
        public Employee(Double measure, String name) {
            this.measure = measure;
            this.name = name;
        }
        @Override
        public double getMeasure() {
            return measure;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static double average(Measurable[] measurables) {
            return Stream.of(measurables).collect(Collectors.averagingDouble(Measurable::getMeasure));
        }
        public static Measurable largest(Measurable[] measurables) {
            Optional<Measurable> measurable = Stream.of(measurables).max(Comparator.comparing(Measurable::getMeasure));
            return measurable.orElse(null);
        }
    }
    public static class Greeter implements Runnable {
        private Integer n;
        private String target;
        public Greeter(Integer n, String target) {
            this.n = n;
            this.target = target;
        }

        @Override
        public void run() {
            for (Integer i = 0; i < n; i++) {
                System.out.println("Hello, " + this.target);
            }
        }
        public static void runTogether(Runnable... tasks) {
            for (Runnable task: tasks) {
                (new Thread(task)).start();
            }

        }
        public static void runInOrder(Runnable... tasks) {
            for (Runnable task: tasks) {
                task.run();
            }
        }
    }
}
