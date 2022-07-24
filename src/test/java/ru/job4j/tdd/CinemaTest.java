package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Что такое TDD?
 * Описание поведения пользователя через тесты
 *
 * @author Ilya kaltygin
 */
public class CinemaTest {

    /**
     *
     * Покупка билета
     */
    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    /**
     * Поиск сеанса
     */
    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    /**
     * Нет подходящего сеанса
     */
    @Ignore
    @Test
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(new ArrayList<>()));
    }

    /**
     * Место занято
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenSeatIsTaken() {
        Account account = new AccountCinema();
        Account account1 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket ticket1 = cinema.buy(account1, 1, 1, date);
    }

    /**
     * Невалидная дата
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 37, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    /**
     * Невалидное место
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 0, 0, date);
    }

}
