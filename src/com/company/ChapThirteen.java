package com.company;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Stream;

public class ChapThirteen {
    public static void main(String[] args) {
        ex1();
        ex3();
        ex4();
        ex5();
        ex6();
    }

    public static void ex1() {
        System.out.println("Start ex 1");
        LocalTime localTime = LocalTime.now();
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.CHINA).withZone(ZoneId.systemDefault()).format(Instant.now()));
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.FRANCE).withZone(ZoneId.systemDefault()).format(Instant.now()));
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(new Locale("th")).withZone(ZoneId.systemDefault()).format(Instant.now()));
        Locale thaiLocal = new Locale("th");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(thaiLocal);
        System.out.println(numberFormat.format(1235123.1231));
    }
    public static void ex3() {
        System.out.println("Start EX 3");
        Locale[] locales = DateFormat.getAvailableLocales();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YY");
        String dateString = simpleDateFormat.format(new Date());
        String dateString2 = (new SimpleDateFormat("MM/d/YY")).format(new Date());
        Stream.of(locales).forEach((locale -> {
            DateFormat format = DateFormat.getDateInstance(3, locale);
            if (format.format(date).compareTo(dateString) == 0 || format.format(date).compareTo(dateString2) == 0) {
                System.out.println(locale.toString());
            }
        }));
    }
    public static void ex4() {
        System.out.println("Start EX 4");
        Optional<String> allLanguage = Stream.of(Locale.getISOLanguages())
                .distinct()
                .reduce((locale1, locale2) -> locale1 + ", " + locale2);
        System.out.println(allLanguage.get());
    }
    public static void ex5() {
        System.out.println("Start EX 5");
        Optional<String> allCurrencies = Currency.getAvailableCurrencies()
                .stream()
                .map((Currency::getDisplayName))
                .distinct()
                .reduce((c1, c2) -> c1 + ", " + c2);
        System.out.println(allCurrencies.get());
    }
    public static void ex6() {
        System.out.println("Start ex 6");
        ArrayList<Currency> currencies = getCurrencies();
        System.out.println(currencies.stream().map((currency -> {
            return currency.getSymbol(Locale.US);
        })).reduce((c1, c2) -> c1 + ", " + c2));;
    }
    public static ArrayList<Currency> getCurrencies() {
        ArrayList<Currency> currencies = new ArrayList<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                Currency currency = Currency.getInstance(locale);
                currencies.add(currency);
            }
            catch (Exception e){
                // skip strange locale
            }
        }
        return currencies;
    }
}

