package ru.job4j.srp;

/**
 * Нарушение принципа единственной ответственности
 *
 * Необходимо выделить отдельную абстракцию для сохранения объектов Animal в базу данных
 *
 * @author Ilya Kaltygin
 */
public interface Animal {
    public void eat();

    public void saveAnimalInDB();
}
