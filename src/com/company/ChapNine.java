package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChapNine {

    public ChapNine() {
        List<String> words = new ArrayList<>();
        words.add("Word 1");
        words.add("Word 2");
        words.add("Word 3");
        words.add("Word 4");
        words.add("Word 5");
        words.add("Word 6");
        words.add("Word 7");
        words.add("Word 8");
        words.add("Word 81");
        words.add("Word 82");
        words.add("Word 83");
        words.add("Word 83wwwww");

        long filterCount = words.parallelStream().filter(word -> word.length() > 6).count();
        System.out.println(filterCount);
        Stream<String> sortedWord = words.stream().sorted(Comparator.comparing(String::length).reversed());
        Optional<String> maxWord = words.stream().max(String::compareToIgnoreCase);
        System.out.println(maxWord.get());

        Optional<String> stringOptional = Optional.ofNullable(null);
        System.out.println(stringOptional.orElse("Nothing here"));
        System.out.println(stringOptional.orElseGet(() -> {
            return maxWord.get();
        }));
        maxWord.ifPresent(m -> {
            System.out.println(m);
        });
        words.stream().forEach(System.out::println);
        Map<Integer, String> newWords = words.stream().collect(Collectors.toMap(String::length, Function.identity(), (existValue, value) -> existValue));
        System.out.println(newWords);

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Viet Nam", "vn"));
        countries.add(new Country("Viet Nam", "vn"));
        countries.add(new Country("English", "en"));
        countries.add(new Country("Sweden", "sv"));
        countries.add(new Country("Sweden", "sv"));
        countries.add(new Country("United State", "us"));
        countries.add(new Country("Singapore", "si"));

        Map<String, Long> groupCountry = countries.stream().collect(Collectors.groupingBy(Country::getName, Collectors.counting()));
        System.out.println(groupCountry);
        Map<Boolean, List<Country>> vnAndOther = countries.stream().collect(Collectors.partitioningBy(country -> country.getCode().equals("vn")));
        System.out.println(vnAndOther);
        Map<String, IntSummaryStatistics> countryPopulation = countries.stream().collect(
                Collectors.groupingBy(Country::getCode, Collectors.summarizingInt(Country::getPopulation))
        );
        System.out.println(countryPopulation);
        Optional<Country> cityMinPopulation = countries.stream().reduce((x, y) -> {
            if (x.getPopulation() > y.getPopulation()) {
                return y;
            } else {
                return x;
            }
        });
        cityMinPopulation.ifPresent(System.out::println);

        Integer totalChar = words.stream().reduce(0, (total, word) -> total + word.length(), (t1, t2) -> t1 + t2);
        System.out.println(totalChar);
    }

    public static class Country {
        private String name;
        private String code;
        private Integer population;

        public Country(String name, String code) {
            this.name = name;
            this.code = code;
            this.population = Double.valueOf(Math.random() * 1000000).intValue();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getPopulation() {
            return population;
        }

        public void setPopulation(Integer population) {
            this.population = population;
        }

        @Override
        public String toString() {
            return super.toString() + "@" + this.getPopulation() + "@" + this.getName();
        }
    }
}
