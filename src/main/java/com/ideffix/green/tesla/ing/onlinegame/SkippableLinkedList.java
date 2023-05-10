package com.ideffix.green.tesla.ing.onlinegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkippableLinkedList<T> implements Iterable<SkippableLinkedList.Node<T>> {
    private final Node<T> head;
    private final Node<T> tail;
    private int size = 0;


    public SkippableLinkedList() {
        this.head = new Node<>(-1);
        this.tail = new Node<>(-1);
        head.next = tail;
        head.skippableNext = tail;
        tail.prev = head;
        tail.skippablePrev = head;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current.skippableNext != tail;
            }

            @Override
            public Node<T> next() {
                Node<T> next = current.skippableNext;
                current = next;
                return next;
            }
        };
    }

    public Node<T> add(T element) {
        Node<T> currentLast = tail.prev;
        Node<T> currentSkippableLast = tail.skippablePrev;
        Node<T> node = new Node<>(element, size++);

        currentLast.next = node;
        currentSkippableLast.skippableNext = node;
        node.prev = currentLast;
        node.next = tail;
        node.skippableNext = tail;
        node.skippablePrev = currentSkippableLast;
        tail.prev = node;
        tail.skippablePrev = node;

        return node;
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();

        Node<T> current = head.next;
        while (current != tail) {
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    public List<T> withoutSkippedToList() {
        List<T> result = new ArrayList<>();

        Node<T> current = head.skippableNext;
        while (current != tail) {
            result.add(current.value);
            current = current.skippableNext;
        }
        return result;
    }

    static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private Node<T> skippableNext;
        private Node<T> skippablePrev;
        private T value;
        private final int index;

        Node(int index) {
            this.index = index;
        }

        public Node(T value, int index) {
            this(index);
            this.value = value;
        }

        public void skip() {
            skippablePrev.skippableNext = skippableNext;
            skippableNext.skippablePrev = skippablePrev;
        }

        public T getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }
    }
}