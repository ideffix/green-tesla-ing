package com.ideffix.green.tesla.ing.onlinegame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkippableLinkedListTest {

    @Test
    public void addsElementsAndIterate() {
        SkippableLinkedList<Integer> l = new SkippableLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        int expected = 1;
        for (SkippableLinkedList.Node<Integer> node : l) {
            assertEquals(expected, node.value);
            expected++;
        }
    }

}