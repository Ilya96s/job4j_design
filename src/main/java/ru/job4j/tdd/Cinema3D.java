package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Что такое TDD?
 * Реализация сервиса кинотеатр через TDD
 * Пустой клаас реализующий интерфейс кинотеатра
 *
 * @author Ilya Kaltygin
 */
public class Cinema3D implements Cinema {
    /**
     * Поиск сеанса
     * @param filter Predicate поиска
     * @return список сеансов
     */
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    /**
     * Покупка билета
     * @param account Аккаунт пользователя
     * @param row Ряд
     * @param column Место
     * @param date Дата
     * @return Билет
     */
    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    /**
     * Добавить сеанс
     * @param session Сеанс который хотим добавить
     */
    @Override
    public void add(Session session) {

    }
}
