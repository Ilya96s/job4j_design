package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Преобразование JSON в POJO. JsonObject
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) throws Exception {

        JSONObject jsonCurrentDriver = new JSONObject("{\"name\":\"Ivan\", \"age\" : \"35\"}");

        /* JSONArray из ArrayList */
        List<Driver> list = new ArrayList<>();
        list.add(new Driver("Oleg", 29));
        list.add(new Driver("Fedor", 45));
        JSONArray jsonPreviousDrivers = new JSONArray(list);

        final Car car = new Car(false, 2085, "Hatchback", new Driver("Ivan", 35),
                new Driver[] {new Driver("Oleg", 29), new Driver("Fedor", 45)});

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isElectroCat", car.isElectroCar());
        jsonObject.put("weight", car.getWeight());
        jsonObject.put("type", car.getType());
        jsonObject.put("currentDriver", jsonCurrentDriver);
        jsonObject.put("previousDrivers", jsonPreviousDrivers);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(car));
    }
}
