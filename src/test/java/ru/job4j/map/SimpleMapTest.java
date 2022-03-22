package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleMapTest {

    @Test
    public void whenPutAndCheckIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenPutReturnFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertThat(map.put(1, "two"), is(false));
    }

    @Test
    public void whenPutReturnTrue() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertThat(map.put(2, "one"), is(true));
    }

    @Test
    public void whenGetReturnEl() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertThat(map.get(1), is("one"));
    }

    @Test
    public void whenRemove() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertThat(map.remove(1), is(true));
        assertThat(map.remove(2), is(true));
        assertThat(map.remove(3), is(false));
        assertNull(map.get(3));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
       Iterator<Integer> it = map.iterator();
       map.put(3, "three");
       it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Iterator<Integer> it = map.iterator();
        map.remove(2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.iterator().next();

    }
}