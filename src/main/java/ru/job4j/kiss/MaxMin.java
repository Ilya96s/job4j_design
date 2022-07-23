package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Знакомство с принципами проекторивания приложений, такими как: Принципы Kiss, Dry и Yagni
 *
 * Класс для поиска максимального и минимального элемента по критерию java.util.Comparator
 *
 * @author Ilya Kaltygin
 */
public class MaxMin {
    /**
     * Поиск максимального элемента в списке
     *
     * @param value Список элементов типа Т
     * @param comparator Comparator типа Т
     * @param <T> Обобщенный тип данных
     * @return Максимальное значение
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, comparator);
    }

    /**
     * Поиск минимального значение в списке
     * @param value Список элементов типа Т
     * @param comparator Comparator типа Т
     * @param <T> Обобщенный тип данных
     * @return Минимальное значение
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, comparator.reversed());
    }

    /**
     * Метод сравнения элементов в списке
     * @param value Список элементов типа Т
     * @param comparator Comparator типа Т
     * @param <T> Обобщенный тип данных
     * @return Результат сравнения элементов в списке
     */
    public <T> T search(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            return null;
        }
        T rsl = value.get(0);
        for (T element : value) {
            rsl = comparator.compare(rsl, element) > 0 ? rsl : element;
        }
        return rsl;
    }
}