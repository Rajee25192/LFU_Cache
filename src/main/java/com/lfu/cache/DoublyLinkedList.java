package com.lfu.cache;
class DoublyLinkedList {
    private int size;
    private Node head;
    private Node tail;

    public void add(Node node) {
        if (null == head) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
        }
        tail = node;
        size++;
    }

    public void remove(Node node) {
        if(null == head || null == node) {
            return;
        }
        if(this.size() == 1 && head.equals(node)) {
            head = null;
            tail = null;
        } else if (head.equals(node)) {
            head = node.getNext();
            head.setPrev(null);
        } else if (tail.equals(node)) {
            Node prevToTail = tail.getPrev();
            prevToTail.setNext(null);
            tail = prevToTail;
        } else {
            Node current = head.getNext();

            while(!current.equals(tail)) {
                if(current.equals(node)) {
                    Node prevToCurrent = current.getPrev();
                    Node nextToCurrent = current.getNext();
                    prevToCurrent.setNext(nextToCurrent);
                    nextToCurrent.setPrev(prevToCurrent);
                    break;
                }
               current = current.getNext();
            }
        }
        size--;
    }

    public Node head() {
        return head;
    }

    public int size() {
        return size;
    }
}