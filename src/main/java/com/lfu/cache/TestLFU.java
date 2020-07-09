package com.lfu.cache;
import java.util.Map;

public class TestLFU {

    public static void main(String[] args) {
    	TestLFU client = new TestLFU();
        LFUCache cache = new LFUCache(4);
        cache.put(1, 11);
        cache.put(2, 12);
        cache.put(3, 13);
        cache.put(4, 14);
        cache.put(5, 15);
        client.print(cache.getFrequencies());

        cache.get(3);
        cache.get(3);
        cache.get(3);
        cache.get(4);
        cache.get(4);
        cache.get(4);
        cache.get(4);
        
        cache.put(6, 16);
        client.print(cache.getCache());
        client.print(cache.getCounts());
        client.print(cache.getFrequencies());
    }

    public void print(Map<Integer, ? extends Object> map) {

        for(Map.Entry<Integer, ? extends Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof Node) {
                System.out.println("Cache Key => "+entry.getKey()+", Cache Value => "+((Node) entry.getValue()).toString());
            } else if (entry.getValue() instanceof DoublyLinkedList) {
                System.out.println("Frequency Key => "+entry.getKey()+" Frequency Values => [");
                Node head = ((DoublyLinkedList) entry.getValue()).head();
                while(null != head) {
                    System.out.println(head.toString());
                    head = head.getNext();
                }
                System.out.println(" ]");
            } else {
                System.out.println("Count Key => "+entry.getKey()+", Count Value => "+entry.getValue());
            }
        }
    }
}