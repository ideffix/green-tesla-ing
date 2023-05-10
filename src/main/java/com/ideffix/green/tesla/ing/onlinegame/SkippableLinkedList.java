package com.ideffix.green.tesla.ing.onlinegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkippableLinkedList<T> implements Iterable<SkippableLinkedList.Node<T>> {
    Node<T> head;
    Node<T> tail;

    public SkippableLinkedList() {
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        head.skippableNext = tail;
        tail.prev = head;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current.next != tail;
            }

            @Override
            public Node<T> next() {
                Node<T> next = current.next;
                current = next;
                return next;
            }
        };
    }

    public void add(T element) {
        Node<T> currentLast = tail.prev;
        Node<T> node = new Node<>(element);

        currentLast.next = node;
        currentLast.skippableNext = node;
        node.prev = currentLast;
        node.next = tail;
        node.skippableNext = tail;
        tail.prev = node;
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
        Node<T> next;
        Node<T> prev;
        Node<T> skippableNext;
        T value;

        Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public void skip() {
            prev.skippableNext = skippableNext;
        }
    }
}
