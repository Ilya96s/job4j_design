package ru.job4j.map;

/**
 * Интерфейс для реализации собственной мапы
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> extends Iterable<K> {
    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);
}
