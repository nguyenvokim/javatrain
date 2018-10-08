package com.company;

import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class ChapEleven {
    public static void main(String[] args) {
        ChapEleven chapEleven = new ChapEleven();
        testAnno(chapEleven);
    }

    @Transient
    public ChapEleven() {
    }
    public static void testAnno(Object object) {
        Class cl = object.getClass();
        System.out.println(cl);
        System.out.println(cl.getDeclaredFields().length);
        for (Field field : cl.getDeclaredFields()) {
            System.out.println(field);
        }
    }
}
