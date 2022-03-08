package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

/**
 * Реализация списка на основе динамического массива, аналог ArrayList.
 * @param <T>
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    /**
     * Конструктор создает экземпляр класса заданной вместимости
     * @param capacity вместимость листа
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод добавляет элемент в конец списка. Сначала проверяется кол-во элементов и длина массива
     * если они равны, то происходит расширение массива в 2 раза с помощью метода copyOf
     * @param value добавляемый элемент
     */
    @Override
    public void add(T value) {
        if (size == container.length - 1) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод позволяет заменить значение элемента, который имеет индекс index, значением neValue
     * @param index индекс элемента, значение которого нужно заменить
     * @param newValue новое значение элемента
     * @return метод возвращает старое значение элемента с индексом index
     * метод checkIndex класса Objects проверяет, находится ли индекс в диапозоне массива,
     * если нет, то выбросит исключение IndexOutOfBoundsException
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T element = container[index];
        container[index] = newValue;
        return element;
    }

    /**
     * Метод позволяет удалить элемент из списка по индексу
     * @param index индекс элемента, который нужно удалить
     * @return возвращает удаленный элемент
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeElement = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index -  1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removeElement;
    }


    /**
     * Метод возвращает элемент из списка по индексу
     * @param index индекс возвращаемого элемента
     * @return возвращаемый элемент
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Метод возвращает кол-во элементов в списке
     * @return кол-во элементов
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
           private int expectedModCount = modCount;
           private int indexIterator = 0;
            @Override
            public boolean hasNext() {
                return indexIterator < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[indexIterator++];
            }
        };
    }
}
