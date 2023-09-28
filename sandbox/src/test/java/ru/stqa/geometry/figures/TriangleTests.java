package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalcArea() {
        double extRes = 9.922;
        var s = new Triangle(4, 5, 6);
        String resultFormatted = String.format("%.3f", s.tArea());
        double parsedDouble = Double.parseDouble(resultFormatted);
        Assertions.assertEquals(extRes, parsedDouble);

        System.out.println("Expected result = " + extRes);
        System.out.println("Actual result = " + parsedDouble);
    }

    @Test
    void canCalcPerimeter() {
        double extRes = 6;
        var p = new Triangle(2, 2, 2);
        Assertions.assertEquals(6, p.tPerimeter());
        System.out.println("Expected 6result = " + extRes);
        System.out.println("Actual result = " + p.tPerimeter());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-1.0, 2.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void sum2sidesLessThan3rdSide() {
        try {
            new Triangle(30.0, 2.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(5.0, 4.0, 3.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality3() {
        var t1 = new Triangle(5.0, 4.0, 3.0);
        var t2 = new Triangle(4.0, 5.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testEquality4() {
        var t1 = new Triangle(4.0, 5.0, 3.0);
        var t2 = new Triangle(5.0, 4.0, 3.0);;
        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testEquality5() {
        var t1 = new Triangle(3.0, 5.0, 4.0);
        var t2 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality6(){
        var t1 = new Triangle(1, 2, 3);
        var t2 = new Triangle(1, 3, 2);;
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality7(){
        var t1 = new Triangle(3, 2, 1);
        var t2 = new Triangle(2, 1, 3);;
        Assertions.assertEquals(t1, t2);
    }
}

