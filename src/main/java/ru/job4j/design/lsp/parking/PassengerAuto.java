package ru.job4j.design.lsp.parking;

/**
 * Легковой автомобил, его размер должен быть = 1
 *
 * @author Ilya Kaltygin
 */
public class PassengerAuto implements Auto {
    private static final int SIZE = 1;

    /**
     * @return Метод возвращает размер автомобиля
     */
    @Override
    public int getSize() {
        return SIZE;
    }
}
