package ru.job4j.design.lsp.parking;

/**
 * Грузовой автомобил, его размер должен быть > 1
 *
 * @author Ilya Kaltygin
 */
public class TruckAuto implements Auto {
    private int size;

    public TruckAuto(int size) {
        this.size = size;
    }

    /**
     * @return Метод возвращает размер автомобиля
     */
    @Override
    public int getSize() {
        return size;
    }
}
