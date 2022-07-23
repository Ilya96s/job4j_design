package ru.job4j.kiss;


import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Знакомство с принципами проекторивания приложений, такими как: Принципы Kiss, Dry и Yagni
 *
 * Класс для поиска максимального и минимального элемента по критерию java.util.Comparator
 *
 * @author Ilya Kaltygin
 */
public class MaxMinTest {
    private List<Integer> list;

    @Before
    public void listCreation() {
        list = List.of(1, 7, 5, 9, 4, 0, 3, 6, 5);
    }

    @Test
    public void whenMaxIsNine() {
        int expected = 9;
        int result = new MaxMin().max(list, Comparator.naturalOrder());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenMinIs0() {
        int expected = 0;
        int result = new MaxMin().min(list, Comparator.naturalOrder());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenListIsEmpty() {
        List<Integer> emptyList = new ArrayList<>();
        assertThat(new MaxMin().max(emptyList, Comparator.naturalOrder())).isNull();
        assertThat(new MaxMin().min(emptyList, Comparator.naturalOrder())).isNull();
    }
}