package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализовать коллекцию SimpleSet которая не хранит дубликаты
 * Для хранения элементов необходимо использовать коллекцию SimpleArrayList
 * @param <T>
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {

    /**
     * Коллекция для хранения элементов
     */
    SimpleArrayList<T> set = new SimpleArrayList<>(16);

    /**
     * Метод добавляет элемент в множество включая null, при этом исключая дубликаты
     * @param value добавляемый элемент
     * @return Возвращает true в случае добавления элемента, в противном случае вернет false
     */
    @Override
    public boolean add(T value) {
        boolean result = !contains(value);
        if (result) {
            set.add(value);
        }
        return result;
    }


    /**
     * Метод проверяет содержит ли множество добавляемый в метод элемент
     * @param value добавляемый элемент
     * @return Возвращает true если множество содержит элемент, в проивном случае вернет false
     */
    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T elements : set) {
            if (Objects.equals(elements, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
