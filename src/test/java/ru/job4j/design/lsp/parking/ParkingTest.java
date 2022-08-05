package ru.job4j.design.lsp.parking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты для парковки машин
 *
 * @author Ilya Kaltygin
 */
class ParkingTest {

    /**
     * Когда добавили автомобиль на парковку
     */
    @Test
    void whenAdd() {
        ParkStore park = new Parking(2, 1);
        Auto passengerAuto1 = new PassengerAuto();
        Auto passengerAuto2 = new PassengerAuto();
        Auto truckAuto = new TruckAuto(2);
        assertThat(park.add(passengerAuto1)).isTrue();
        assertThat(park.add(passengerAuto2)).isTrue();
        assertThat(park.add(truckAuto)).isTrue();
    }

    /**
     * Получение размеров автомобилей
     */
    @Test
    void whenGetSize() {
        Auto truckAuto = new TruckAuto(3);
        Auto passengerAuto = new PassengerAuto();
        int passengerSize = passengerAuto.getSize();
        int truckSize = truckAuto.getSize();
        assertThat(passengerSize).isEqualTo(1);
        assertThat(truckSize).isEqualTo(3);
    }

    /**
     * Когда на парковке нет мест и автомобили не добавились
     */
    @Test
    void whenParkIsFull() {
        ParkStore park = new Parking(2, 2);
        Auto passengerAuto1 = new PassengerAuto();
        Auto passengerAuto2 = new PassengerAuto();
        Auto passengerAuto3 = new PassengerAuto();
        Auto truckAuto1 = new TruckAuto(2);
        Auto truckAuto2 = new TruckAuto(2);
        Auto truckAuto3 = new TruckAuto(2);
        park.add(passengerAuto1);
        park.add(passengerAuto2);
        park.add(truckAuto1);
        park.add(truckAuto2);
        assertThat(park.add(passengerAuto3)).isFalse();
        assertThat(park.add(truckAuto3)).isFalse();
    }

    /**
     * Когда добавили грузовые автомобили на места легковых
     */
    @Test
    void whenAddTruckAutoToThePassengerPark() {
        ParkStore park = new Parking(7, 0);
        Auto truckAuto1 = new TruckAuto(2);
        Auto truckAuto2 = new TruckAuto(2);
        Auto truckAuto3 = new TruckAuto(3);
        boolean rsl1 = park.add(truckAuto1);
        boolean rsl2 = park.add(truckAuto2);
        boolean rsl3 = park.add(truckAuto3);
        assertThat(rsl1).isTrue();
        assertThat(rsl2).isTrue();
        assertThat(rsl3).isTrue();
    }

    /**
     * Когда ожидаем появлиние исключения
     */
    @Test
    void whenExpectedException() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Auto truckAuto = new TruckAuto(1);
        });
        Assertions.assertEquals("Truck auto size must be greater than 1", thrown.getMessage());
    }
}