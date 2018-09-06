package com.company;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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

        System.out.println("EXERCISES 4");
        (IntSequence.of(1,2,3,4)).printMe();

        System.out.println("EXERCISES 5");
        IntSequence.constant(5, value -> System.out.println(value + " " + value + " " + value + "..."));

        System.out.println("EXERCISES 8");
        Greeter greeter1 = new Greeter(1, "Nguyen");
        Greeter greeter2 = new Greeter(2, "Dung");
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

        System.out.println("EXERCISES 11");
        String fileterName = "Applications";
        String[] filesName = files.list((dir, name) -> name.equals(fileterName));
        System.out.println(Arrays.toString(filesName));

        System.out.println("EXERCISES 12");
        List<File> sortedFiles = Arrays.stream((new File("/Users/nguyenvk/Desktop")).listFiles())
                .sorted(Comparator.comparing(File::isDirectory).thenComparing(file -> file.getName().length()))
                .collect(Collectors.toList()
                );
        System.out.println(Arrays.toString(sortedFiles.toArray()));

        System.out.println("EXERCISES 13");
        Runnable[] runnables = {greeter1, greeter2};
        runableAll(runnables).run();

        System.out.println("EXERCISES 14");
        Arrays.stream(employees)
                .sorted(Comparator.comparing(Employee::getMeasure).thenComparing(Employee::getName).reversed())
                .collect(Collectors.toList());
    }

    /**
     * EXERCISES 13
     * @param runnables Runnable Object
     * @return
     */
    public Runnable runableAll(Runnable[] runnables) {
        return () -> {
            for (Runnable runable: runnables) {
                runable.run();
            }
        };
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
    public interface IntSequence {
        public void printMe();
        public static IntSequence of(int... values) {
            return new IntSequence() {
                public void printMe() {
                    List<Integer> arrayList = Arrays.stream(values).boxed().collect(Collectors.toList());
                    System.out.println(arrayList.toString());
                }
            };
        }
        public static void constant(int value, InfinityPrint print) {
            print.infinityPrint(value);
        }
    }
    public interface InfinityPrint {
        public void infinityPrint(int value);
    }
}
