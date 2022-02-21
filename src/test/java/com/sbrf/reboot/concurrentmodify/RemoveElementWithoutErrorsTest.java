package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    final List<Integer> res = new ArrayList<Integer>() {{
        add(1);
        add(3);
    }};
    List<Integer> list = null;

    @BeforeEach
    public void init() {
        list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};
    }

    @Test
    public void successConcurrentModificationException() {

        assertThrows(ConcurrentModificationException.class, () -> {

            for (Integer integer : list) {
                list.remove(1);
            }

        });

    }

    @Test
    public void successRemoveElementWithoutErrors() {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer number = it.next();
            if (number == 2) it.remove();
        }
        assertEquals(res, list);
    }

    @Test
    public void successRemoveElementWithoutErrorsAdditionOption() {
        list.removeIf(number -> number == 2);
        assertEquals(res, list);
    }

}
