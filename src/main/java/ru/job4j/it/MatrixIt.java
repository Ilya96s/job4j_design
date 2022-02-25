package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает работу итератора для двухмерно массива int[][]
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int cell = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод hasNExt() проверяет есть ли следующий элемент в массиве.
     * В теле цицкла происходит перемещение итератора по строке.
     * @return проверяет что индексы корректные
     * @return
     */
    @Override
    public boolean hasNext() {
         while (row < data.length - 1 && cell == data[row].length) {
             row++;
             cell = 0;
         }
         return cell < data[row].length;
    }

    /**
     * Метод next возвращает элементы массива
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][cell++];
    }
}
