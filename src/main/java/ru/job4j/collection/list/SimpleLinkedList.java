package ru.job4j.collection.list;

import org.w3c.dom.Node;

import java.util.*;

/**
 * Создать контейнер на базе связанного списка
 * Необходимо создать динамический контейнер с методами:
 * add(E value); (добавляет в конец)
 *  E get(int index); (перебирает элементы до указанного индекса и возвращает значение из найденной ноды)
 *  реализовать интерфейс Iterable<E>
 * @param <E>
 * @author  Ilya Kaltygin
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private int modCount;
    private Node<E> firstNode;
    private Node<E> lastNode;

    /**
     * Метод добавляет элемент в конец списка
     * @param value добавляемый элемент в список
     */
    @Override
    public void add(E value) {
        Node<E> lastN = lastNode;
        Node<E> newNode = new Node<>(lastN, value, null);
        lastNode = newNode;
        if (lastN == null) {
            firstNode = newNode;
        } else {
            lastN.next =  newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Метод возвращает элемент из списка по заданному индексу
     * @param index индекс возвращаемого элемента
     * @return возвращаемый элемент
     * checkIndex(index, size) проверяет, находится ли ндекс в диапозоне списка
     * если индекс вышел за диапозон, ты выбрасывается исключение IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = firstNode;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.element;
    }

    private static class Node<E> {
        /**
         * элемент
         */
        E element;
        /**
         * Ссылка на следующий объект
         */
        private Node<E> next;
        /**
         * Ссылка на предыдущий объект
         */
        private Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            /**
             * fail-fast поведение
             */
            private final int expectedModCount = modCount;
            /**
             * Указатель с помощью которого перемещаюсь по списку
             */
            SimpleLinkedList.Node<E> point = firstNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = point.element;
                point = point.next;
                return value;
            }
        };
    }
}

