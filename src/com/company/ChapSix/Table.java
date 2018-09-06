package com.company.ChapSix;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Table<K, V> {
    protected ArrayList<Entry<K, V>> arrayList;
    public Table() {

    }
    public void put(K key, V value) {
        Optional<Entry<K, V>> result = findByKey(key);
        result.ifPresent(kvEntry -> kvEntry.setValue(value));
    }
    public V get(K key) {
        Optional<Entry<K, V>> result = findByKey(key);
        return result.map(Entry::getValue).orElse(null);
    }
    public void remove(K key) {
        Optional<Entry<K, V>> result = findByKey(key);
        if (result.isPresent()) {
            int index = arrayList.indexOf(result.get());
            arrayList.remove(index);
        }
    }
    private Optional<Entry<K, V>> findByKey(K key) {
        return arrayList
                .stream()
                .filter(kvEntry -> kvEntry.getKey().equals(key))
                .findFirst();
    }
    public void print() {
        System.out.println(arrayList);
    }
}
