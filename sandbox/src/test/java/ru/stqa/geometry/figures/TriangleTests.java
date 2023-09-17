package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.stqa.geometry.figures.Triangle.tArea;

public class TriangleTests {
    @Test
    void canCalcArea(){
        double extRes = 9.922;
        String resultFormatted = String.format("%.3f",tArea(4,5,6));
        double parsedDouble = Double.parseDouble(resultFormatted);
        Assertions.assertEquals(extRes, parsedDouble);

        System.out.println("Expected result = " + extRes);
        System.out.println("Actual result = " + parsedDouble);
    }

    @Test
    void canCalcPerimeter(){
       // var p = new Triangle(2,2,2);
        Assertions.assertEquals(6.0, Triangle.tPerimeter(2,2,2));

        System.out.println("Expected result = " + 6);
        System.out.println("Actual result = " + Triangle.tPerimeter(2,2,2));

    }
}
