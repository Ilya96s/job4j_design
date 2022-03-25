package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Создать элементарную структуру дерева
 * @param <E>
 * @author  Ilya Kaltygin
 * @version 1.2
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    /**
     * Конструктор принимает в качестве параметра root(корень), т.е при
     * создании объекта - создается корень дерева и к нему уже с помощью метода add можно
     * добавлять потомков и т.д
     * @param root
     */
    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод находит узел по значению parent и добавляет в него дочерний узел child
     * @param parent Узел-родителя для которого добавляем потомка
     * @param child Потомок
     * @return Возвразает true если добавил, иначе false
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> foundParent = findBy(parent);
        Optional<Node<E>> foundChild = findBy(child);
        boolean rsl = foundParent.isPresent() && foundChild.isEmpty();
        if (rsl) {
            foundParent.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод поиска узла по его значению.
     * @param value Значение для поиска
     * @return Найденный узел
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> condition = (e -> e.value.equals(value));
        return findByPredicate(condition);
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве,
     * если их > 2, то дерево не бинарное
     * @return true если дерево бинарное, иначе false
     */
    public boolean isBinary() {
        Predicate<Node<E>> condition = (e -> e.children.size() > 2);
        return findByPredicate(condition).isEmpty();
    }

    /**
     * Метод обхода дерева, поиск узла по предикату. Используется алгоритм обхода
     * дерева в ширину на струткре Queue
     * @param condition Предикат
     * @return результат предиката
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
