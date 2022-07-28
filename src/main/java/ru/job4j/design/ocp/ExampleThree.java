package ru.job4j.design.ocp;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример № 3
 *
 * Нарушение принципа OCP заключается в том, что поле list не является абстракцией, а представляет собой реализацию интерфейса List
 *
 * @author Ilya Kaltygin
 */
public class ExampleThree {
    private List<String> list = new ArrayList<>();

    public void add(String s) {
        list.add(s);
    }

    public void remove(String s) {
        list.remove(s);
    }
}
