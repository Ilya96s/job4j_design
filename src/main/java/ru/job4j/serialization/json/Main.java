package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Преобразование объекта в JSON и обратно
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        final Car car = new Car(false, 2085, "Hatchback", new Driver("Ivan", 35),
                new Driver[] {new Driver("Oleg", 29), new Driver("Fedor", 45)});

        /* Сериализация объекта в JSON */
        final Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(car);
        System.out.println(jsonString);

        /* Десериализация JSON в объект */
        Car carFromJson = gson.fromJson(jsonString, Car.class);
        System.out.println(carFromJson);
    }
}
