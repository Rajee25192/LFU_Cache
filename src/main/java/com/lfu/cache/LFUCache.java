package com.lfu.cache;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LFUCache {

    private Map<Integer, Node> cache = new HashMap<>();
    private Map<Integer, Integer> counts = new HashMap<>();
    private TreeMap<Integer, DoublyLinkedList> frequencies = new TreeMap<>();
    private final int CAPACITY;

    public LFUCache(int capacity) {
        this.CAPACITY = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);

        int frequency = counts.get(key);
        frequencies.get(frequency).remove(new Node(node.key(), node.value()));
        removeFreq(frequency);
        frequencies.computeIfAbsent(frequency + 1, k -> new DoublyLinkedList()).add(new Node(node.key(), node.value()));

        counts.put(key, frequency + 1);
        return cache.get(key).value();
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {

            Node node = new Node(key, value);

            if (cache.size() == CAPACITY) {

                int l_count = frequencies.firstKey();
                Node deleteThisNode = frequencies.get(l_count).head();
                frequencies.get(l_count).remove(deleteThisNode);

                int deleteThisKey = deleteThisNode.key();
                removeFreq(l_count);
                cache.remove(deleteThisKey);
                counts.remove(deleteThisKey);
            }

            cache.put(key, node);
            counts.put(key, 1);
            frequencies.computeIfAbsent(1, k -> new DoublyLinkedList()).add(node);
        }
    }

    private void removeFreq(int frequency) {
        if (frequencies.get(frequency).size() == 0) {
            frequencies.remove(frequency);
        }
    }

    public Map<Integer, Node> getCache() {
        return cache;
    }

    public Map<Integer, Integer> getCounts() {
        return counts;
    }

    public TreeMap<Integer, DoublyLinkedList> getFrequencies() {
        return frequencies;
    }
}
