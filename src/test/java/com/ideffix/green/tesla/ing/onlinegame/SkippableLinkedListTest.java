package com.ideffix.green.tesla.ing.onlinegame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkippableLinkedListTest {

    @Test
    public void isEmpty() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();

        for (var ignored : l) {
            fail();
        }

        var actual = l.toList();
        var expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void addsElementsAndIterate() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        int expected = 1;
        for (var node : l) {
            assertEquals(expected, node.value);
            expected++;
        }
    }

    @Test
    public void createsList() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        var actual = l.toList();
        var expected = List.of(1, 2, 3);

        assertEquals(expected, actual);
    }

    @Test
    public void createsListWithoutSkipped() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        var actual = l.withoutSkippedToList();
        var expected = List.of(1, 2, 3);

        assertEquals(expected, actual);
    }

    @Test
    public void skipFirst() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        for (var node : l) {
            if (node.value == 1) {
                node.skip();
                break;
            }
        }

        var actual = l.withoutSkippedToList();
        var expected = List.of(2, 3);

        assertEquals(expected, actual);
    }

    @Test
    public void skipMiddle() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        for (var node : l) {
            if (node.value == 2) {
                node.skip();
                break;
            }
        }

        var actual = l.withoutSkippedToList();
        var expected = List.of(1, 3);

        assertEquals(expected, actual);
    }

    @Test
    public void skipLast() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        for (var node : l) {
            if (node.value == 3) {
                node.skip();
                break;
            }
        }

        var actual = l.withoutSkippedToList();
        var expected = List.of(1, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void skipAll() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        for (var node : l) {
            node.skip();
        }

        var actual = l.withoutSkippedToList();
        var expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void skipAllAndAdd() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        for (var node : l) {
            node.skip();
        }

        l.add(4);
        l.add(5);

        var actualSkippable = l.withoutSkippedToList();
        var expectedSkippable = List.of(4, 5);

        assertEquals(expectedSkippable, actualSkippable);

        var actual = l.toList();
        var expected = List.of(1, 2, 3, 4, 5);

        assertEquals(expected, actual);
    }

}