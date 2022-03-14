package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * Реализация методов интерфейса ListIterator
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class ListUtils {

    /**
     * Метод вставляет элемент в список до индекса
     * @param list Список элементов
     * @param index Индекс перед которым нужно вставить элемент
     * @param value Вставляемый элемент
     * @param <T> Тип элементов в списке
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод вставляет элемент в список  после индекса
     * @param list Список элементов
     * @param index Индекс после которого нужно вставить элемент
     * @param value Вставляемый элемент
     * @param <T> Тип элементов в списке
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.nextIndex() > index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод удялает все элементы в списке которые удовлетворяют предикату
     * @param list Список элементов
     * @param filter Предикат
     * @param <T> Тип элементов в списке
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы в списке которые соответствуют предикату
     * @param list Список элементов
     * @param filter Предикат
     * @param value Вставляемый элемент
     * @param <T> Тип элементов в спике
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет из списка list те элементы которые есть в списке elements
     * @param list Список элементов
     * @param elements Список удаляемых элементов
     * @param <T> Тип элементов в списке
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }
}
