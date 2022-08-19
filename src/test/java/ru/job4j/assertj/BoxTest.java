package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 16);
        String name = box.whatThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(12, 12);
        String name = box.whatThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenNumberOfVertexIs4() {
        Box box = new Box(4, 8);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(4);
    }

    @Test
    void whenNumberOfVertexIsMinus1() {
        Box box = new Box(4, 0);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(-1);
    }

    @Test
    void whenBoxIsExist() {
        Box box = new Box(4, 8);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void whenBoxIsNotExist() {
        Box box = new Box(-1, 8);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenBoxHasArea() {
        Box box = new Box(8, 12);
        double boxArea = box.getArea();
        assertThat(boxArea).isEqualTo(864)
                .isGreaterThan(800)
                .isLessThan(900)
                .isPositive();
    }


    @Test
    void whenBoxHasNotArea() {
        Box box = new Box(5, 10);
        double boxArea = box.getArea();
        assertThat(boxArea).isEqualTo(0);
    }

}