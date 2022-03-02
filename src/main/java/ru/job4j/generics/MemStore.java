package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    /**
     * Хранилище для хранения данных
     * ключом будет являться id модели, а значением объект, который будет передаватсья в хранилище
     */
    private final Map<String, T> storage = new HashMap<>();

    /**
     * Добовляет в хранилище объект типа T
     * Метод ничего не возвращает
     */
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * Выполняет замену объекта по id, на объект который передается в параметрах и возвращает true если
     * замена выполнена успешно
     * Если в хранилище есть объект с таким id, то поверх него запишется id, который передается в параметрах метода
     */
    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
       if (storage.containsKey(id)) {
          storage.put(id, model);
           rsl = true;
        }
        return rsl;
    }

    /**
     * Метод удаляет пару ключ-значение по id, который передается в метод и возвращает true, если
     * удаление выполнено успешно
     */
    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        if (storage.get(id) != null) {
            storage.remove(id);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод возвращает объект по ключу, который равен id, передаваемый в качестве параметра метода или возвращает null
     * если по такому ключу в Map еще нет пары ключ-значение
     */
    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
