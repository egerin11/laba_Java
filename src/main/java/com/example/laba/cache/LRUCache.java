package com.example.laba.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyCache<K, V> {
    int capacity;
    private final LinkedHashMap<K, V> cache;

    public MyCache(int maxSize) {
        this.capacity = maxSize;
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    public V get(K key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }


}