package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализовать метод Delete для односвязного списка
 * @param <T>
 * @author Ilya Kaltygin
 * @version 1.4
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /**
     * Метод добавляет элемент в конец списка
     * @param value добавляемый элемент в список
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод позволяет перевенуть связанный список
     * @return возвращает false если список пуст или содержит 1 элемент, иначе true;
     */
    public boolean revert() {
        boolean result = head == null || head.next == null;
        if (!result) {
            Node<T> current = head;
            Node<T> previous = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
        }
        return !result;
    }

    /**
     * Метод добавляет элемент в начало списка
     * @param value
     */
    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * Метод удаляет первый элемент в списке
     * @return возвращает удаленный элемент
     * @throws NoSuchElementException бросает исключение если элемента нет в списке
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> result = head;
        head = head.next;
        result.next = null;
        T temp = result.value;
        result.value = null;
        return temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}