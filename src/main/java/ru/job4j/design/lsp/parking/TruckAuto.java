package ru.job4j.design.lsp.parking;
import static ru.job4j.design.lsp.parking.PassengerAuto.SIZE;

/**
 * Грузовой автомобил, его размер должен быть > 1
 *
 * @author Ilya Kaltygin
 */
public class TruckAuto implements Auto {
    private int size;

    public TruckAuto(int size) {
        if (size <= SIZE) {
            throw new IllegalArgumentException("Truck auto size must be greater than 1");
        }
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
