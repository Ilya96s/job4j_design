package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
/**
 * Что такое TDD?
 * Реализация сервиса кинотеатр через TDD
 * Интерфейс кинотетра
 *
 * @author Ilya Kaltygin
 */
public interface Cinema {

    /**
     * Поиск сеанса
     * @param filter Predicate поиска
     * @return список сеансов
     */
    List<Session> find(Predicate<Session> filter);

    /**
     * Покупка билета
     * @param account Аккаунт пользователя
     * @param row Ряд
     * @param column Место
     * @param date Дата
     * @return Билет
     */
    Ticket buy(Account account, int row, int column, Calendar date);

    /**
     * Добавить сеанс
     * @param session Сеанс который хотим добавить
     */
    void add(Session session);
}
