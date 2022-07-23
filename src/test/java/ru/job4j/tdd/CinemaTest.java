package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    @Test
    @Disabled
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    /**
     * Поиск сеанса
     */
    @Test
    @Disabled
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isEqualTo(Arrays.asList(new Session3D()));
    }

    /**
     * Нет подходящего сеанса
     */
    @Test
    @Disabled
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions).isNull();
    }

    /**
     * Место занято
     */
    @Test
    @Disabled
    public void whenIncorrectPlace() {
        Account account = new AccountCinema();
        Account account1 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket ticket1 = cinema.buy(account1, 1, 1, date);
        assertThat(ticket1).isNull();
    }

    /**
     * Доступных билетов на выбранную дату нет
     */
    @Test
    @Disabled
    public void whenIncorrectDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isNull();
    }
}