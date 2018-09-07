package com.company.ChapSix;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class ChapSiz {
    public ChapSiz() {
        Double[] result = swap(0, 1, 1.5, 2.0, 3.0);
        Double[] result2 = ChapSiz.<Double>swap(0, 1, 1.5, 2.0, 3.3);
        System.out.println(Arrays.toString(result2));

        Pair<Integer> pair1 = new Pair<>(1,2);
        Pair<Integer> pair2 = new Pair<>(5,6);
        Pair<Integer> pair3 = new Pair<>(3,4);

        System.out.println("EXERCISES 6");
        ArrayList<Pair<Integer>> a1 = new ArrayList<>(Arrays.asList(pair1));
        ArrayList<Pair<Integer>> a2 = new ArrayList<>(Arrays.asList(pair2));
        mergeArrayExtend(a1, a2);
        System.out.println(Arrays.toString(a1.toArray()));
        mergeArraySuper(a1, a2);
        System.out.println(Arrays.toString(a1.toArray()));

        ArrayList<Pair<Integer>> arrayList = new ArrayList<>(Arrays.asList(pair1, pair2, pair3));
        System.out.println("EXERCISES 9");
        System.out.println(firstLast(arrayList));
        System.out.println("EXERCISES 10");
        System.out.println(min(arrayList));
        System.out.println(max(arrayList));
        System.out.println("EXERCISES 11");
        System.out.println(minMax(arrayList));

        System.out.println("EXERCISES 12");
        ArrayList<Pair<Integer>> minMaxArrayList = new ArrayList<>();
        minmax(arrayList, Comparator.comparing(Pair::getFirst), minMaxArrayList);
        System.out.println(Arrays.toString(minMaxArrayList.toArray()));
        ArrayList<Integer> integers = new ArrayList<>();
        minmax(new ArrayList<>(Arrays.asList(1, 5, 2, 6, -2, 7, 1, 6, 3, 33)), Comparator.comparing(Integer::intValue), integers);
        System.out.println(Arrays.toString(integers.toArray()));

        System.out.println("EXERCISES 13");
        ArrayList<Integer> integers2 = new ArrayList<>();
        maxmin(new ArrayList<>(Arrays.asList(1, 5, 2, 6, -2, 7, 1, 6, 3, 33)), Comparator.comparing(Integer::intValue), integers2);
        System.out.println(Arrays.toString(integers2.toArray()));

        System.out.println("EXERCISES 15");
        List<String> strings = map(integers2, Object::toString);
        System.out.println(Arrays.toString(strings.toArray()));
        List<Integer> integers3 = map(strings, Integer::valueOf);
        System.out.println(Arrays.toString(integers3.toArray()));
    }
    public static <T> T[] swap(int i, int j, T... values) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        return values;
    }
    public static <T> void swapHelper(List<T> elements, int i, int j) {
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
    /**
     * EXERCISES 6
     * @param arrayList1
     * @param arrayList2
     * @param <T>
     */
    public static <T> void mergeArrayExtend(ArrayList<T> arrayList1, ArrayList<? extends T> arrayList2) {
        arrayList1.addAll(arrayList2);
    }
    public static <T> void mergeArraySuper(ArrayList<? super T> arrayList1, ArrayList<T> arrayList2) {
        arrayList1.addAll(arrayList2);
    }
    /**
     * EXERCISES 9
     * @param a
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> Pair<Pair<E>> firstLast(ArrayList<Pair<E>> a) {
        return new Pair<>(a.get(0), a.get(a.size() - 1));
    }

    /**
     * EXERCISES 10
     * @param a
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E min(ArrayList<E> a) {
        Optional<E> result = a.stream().min(Comparable::compareTo);
        return result.get();
    }

    /**
     * EXC 10
     * @param a
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E max(ArrayList<E> a) {
        Optional<E> result = a.stream().max(Comparable::compareTo);
        return result.get();
    }

    /**
     * EXERCISES 11
     * @param a
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> Pair<E> minMax(ArrayList<E> a) {
        return new Pair<>(min(a), max(a));
    }
    public static <T> void minmax(List<T> elements, Comparator<? super T> comp, List<? super T> result) {
        Optional<T> min = elements.stream().min(comp);
        Optional<T> max = elements.stream().max(comp);
        result.add(min.get());
        result.add(max.get());
    }

    /**
     * EXERCISES 13
     * @param elements
     * @param comp
     * @param result
     * @param <T>
     */
    public static <T> void maxmin(List<T> elements, Comparator<? super T> comp, List<? super T> result) {
        minmax(elements, comp, result);
        swapHelper(result, 0, 1);
    }
    public static <T, R> List<R> map(List<T> a, Function<T, R> func) {
        return a.stream().map(func).collect(Collectors.toList());
    }

    /**
     * EXERCISES 18, actually , not really clear
     * @param n
     * @param obj
     * @param constr
     * @param <T>
     * @return
     */
    public static <T> T[] repeat(int n, T obj, IntFunction<T[]> constr){
        return constr.apply(n);
    }
}
