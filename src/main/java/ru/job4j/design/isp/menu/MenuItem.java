package ru.job4j.design.isp.menu;

import java.util.List;

/**
 * Элементы меню
 *
 * В задании сказано, что пункт меню имеет имя, а также дочерние элементы. Но обратите внимание на последний пункт.
 * Он говорит, что при выборе элемента меню, мы получаем действие. Соответственно, пункт меню и действие связаны.
 * Но связаны не через стороннюю структуру (напр., Map),а через сам элемент меню, потому что на элементах меню строится само меню.
 * Исходя из это получаем следующий интерфейс для пункта меню
 */
public interface MenuItem {

    /**
     * Имя пункта меню
     * @return
     */
    String getName();

    /**
     * Дочерние элементы меню, т.е подпункты
     * @return Список с дочерними элементами
     */
    List<MenuItem> getChildren();

    /**
     * Выполнение действий при выборе меню
     * @return Объект типа ActionDelegate
     */
    ActionDelegate getActionDelegate();

}