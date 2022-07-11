package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный кэш
 *
 * @author Ilya Kaltygin
 */
public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод отправляет данные в кэш
     * @param key Ключ данных в кэше
     * @param value Значения отправялемые в кэш
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Метод получения данных из кэша
     * Если данных с таким ключем нет, то они загружаются
     * @param key Ключ данных в кэше
     * @return Данные
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    /**
     * Абстрактный метод загрузки данных по ключу
     * @param key Ключ данных
     * @return Данные
     */
    protected abstract V load(K key);
}
