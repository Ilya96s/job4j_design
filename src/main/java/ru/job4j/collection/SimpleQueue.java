package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Необходимо реализовать очередь на двух стеках по принципу FIFO
 * FIFO - first in first out
 * @param <T>
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize;
    private int outSize;

    /**
     * Метод poll() должен возвращать первое значене и удалять его из коллекции
     * @return первое значение в коллекции
     */
    public T poll() {
        if (outSize == 0) {
            if (inSize == 0) {
                throw new NoSuchElementException();
            }
            while (inSize != 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        outSize--;
        return out.pop();
    }

    /**
     * Метод помещает значение в конец коллекции(очереди)
     * @param value помещаемое значение
     */
    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
