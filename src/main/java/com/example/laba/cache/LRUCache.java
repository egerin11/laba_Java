package com.example.laba.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class LRUCache<K, V> {
    private final int capacity;
    private Map<K, V> cache;
    private LinkedList<K> keys;

    public LRUCache(int size) {
        this.capacity = size;
        this.cache = new HashMap<>();
        this.keys = new LinkedList<>();
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            keys.remove(key);
        } else if (cache.size() >= capacity) {
            K oldestKey = keys.removeFirst();
            cache.remove(oldestKey);
        }

        cache.put(key, value);
        keys.addLast(key);
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            keys.remove(key);
            keys.addLast(key);
            return cache.get(key);
        }
        return null;
    }

    public void remove(K key) {
        if (cache.containsKey(key)) {
            keys.remove(key);
            cache.remove(key);
        }
    }

    public void displayCache() {
        System.out.println("LRUCache contents:");
        for (K key : keys) {
            System.out.println(key.toString() + ": " + cache.get(key));
        }
    }
}