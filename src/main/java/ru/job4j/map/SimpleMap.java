package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация собственной структуры данных HashMap()
 * @param <K> Обощенный тип
 * @param <V> Обобщенный тип
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод вставляет элемент по ключу и его значению
     * @param key Ключ
     * @param value Значение
     * @return Если бакет пустой, то элемент будет добавлен и метод вернет true,
     * иначе false если бакет занят и элемент не будет добавлен
     * Перед вставкой проводится проверка на коэффициент загрузки
     */
    @Override
    public boolean put(K key, V value) {
        if (count >= (LOAD_FACTOR * table.length)) {
            expand();
        }

        MapEntry<K, V> newElement = new MapEntry<>(key, value);
        int index = indexFor(hash(key.hashCode()));
        boolean result = table[index] == null;
        if (result) {
            table[index] = newElement;
            modCount++;
            count++;
        }
        return result;
    }

    /**
     * Метод определяет размер хэш таблицы
     * @return Количество бакетов в хэш таблице
     */
    public int size() {
        return capacity;
    }

    /**
     * Хэш функция
     * @param hashCode hasCode ключа
     * @return хэш значение
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * Метод вычисляет бакет для элемента
     * @param hash хэш
     * @return бакет
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    /**
     * Метод расширяет хэш таблицу
     * Создает новую таблицу и копирует элементы из старой таблицы в новую
     */
    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        Iterator<K> it = iterator();
        while (it.hasNext()) {
            K elementKey = it.next();
            MapEntry<K, V> newElement = new MapEntry<>(elementKey, get(elementKey));
            int index = indexFor(hash(elementKey.hashCode()));
            newTable[index] = newElement;
        }
        table = newTable;
    }

    /**
     * Метод возвращает запись по ключу
     * @param key Ключ элемента
     * @return В случае отсутствия записи возвращает null, иначе само значение
     */
    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> element = table[index];
        if (table[index] != null && table[index].key.equals(key)) {
            return element.value;
        }
        return null;
    }

    /**
     * Метод удаляет элемент из хэш таблицы по его ключу
     * @param key Ключ элемнта
     * @return В случае успешного удаления возвращает true, иначе случае false.
     */
    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    /**
     * Итератор обладает fail-fast поведение
     * @return
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    /**
     * Вложенный класс
     * @param <K> Ключ
     * @param <V> Значение
     */
    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
