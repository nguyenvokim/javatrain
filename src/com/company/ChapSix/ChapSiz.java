package com.company.ChapSix;

import java.util.Arrays;

public class ChapSiz {
    public ChapSiz() {
        Double[] result = swap(0, 1, 1.5, 2.0, 3.0);
        Double[] result2 = ChapSiz.<Double>swap(0, 1, 1.5, 2.0, 3.3);
    }
    public static <T> T[] swap(int i, int j, T... values) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        return values;
    }
}
