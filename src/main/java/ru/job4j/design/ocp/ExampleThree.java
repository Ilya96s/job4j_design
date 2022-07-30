package ru.job4j.design.ocp;

import java.util.ArrayList;

/**
 * Пример № 3
 *
 * Нарушение принципа OCP заключается в том, поле list представляет конкретную реализацию. Так же вместе типа String нужно использовать
 * обобщенный тип (WildCard <?>)
 *
 * @author Ilya Kaltygin
 */
public class ExampleThree {

    private ArrayList<String> list;

    public ExampleThree(ArrayList<String> list) {
        this.list = list;
    }
}
