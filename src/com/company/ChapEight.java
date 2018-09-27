package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChapEight {
    public ChapEight() {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("string1", "string12", "string131", "string131sss2315", "string15"));
        ArrayList<String> strings2 = new ArrayList<>(Arrays.asList("xilo1", "xilowawd1", "xilo1dasd", "xilo134234", "xilodasdad1"));

        OptionalDouble avg = strings.stream()
                .flatMapToInt((string) -> IntStream.of(string.length()))
                .average();
        System.out.println("EX9: Avg string length: " + avg.orElse(0));

        Optional<String> maxString = strings.stream()
                .max(Comparator.comparing(String::length));
        Optional<String> max10 = strings.stream()
                .filter(s -> s.length() > maxString.orElse("").length())
                .reduce((s, s2) -> s + ", " + s2);

        System.out.println("EX10:" + max10.orElse(""));

        //EX 13
        System.out.println("EX13:" + joinMe(Stream.of(strings, strings2)));

        //EX 15
        IntStream.iterate(0, i -> i + 1)
                .filter(ChapEight::isPrime)
                .limit(500)
                .forEach(System.out::println);
    }

    /**
     * EX 12
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        return Stream.concat(first, second);
    }
    private static <T> ArrayList<T> joinMe(Stream<ArrayList<T>> stream) {
        ArrayList<T> emptyArr = new ArrayList<>();
        return stream.reduce(emptyArr, (ts, ts2) -> {
            ts.addAll(ts2);
            return ts;
        });
    }
    private static <T> ArrayList<T> joinMe2(Stream<ArrayList<T>> stream) {
        Optional<ArrayList<T>> arrayList = stream.reduce((ts, ts2) -> {
            ts.addAll(ts2);
            return ts;
        });
        return arrayList.orElse(new ArrayList<T>());
    }
    public static boolean isPrime(int n) {
        if (n % 2==0) return false;
        for(int i= 3;i * i <= n; i += 2) {
            if(n % i==0)
                return false;
        }
        return true;
    }
}
