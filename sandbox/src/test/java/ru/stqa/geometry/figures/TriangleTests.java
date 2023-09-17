package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalcArea(){
        double extRes = 9.922;
        var s = new Triangle(4,5,6);
        String resultFormatted = String.format("%.3f",s.tArea());
        double parsedDouble = Double.parseDouble(resultFormatted);
        Assertions.assertEquals(extRes, parsedDouble);

        System.out.println("Expected result = " + extRes);
        System.out.println("Actual result = " + parsedDouble);
    }

    @Test
    void canCalcPerimeter(){
        var p = new Triangle(2,2,2);
        Assertions.assertEquals(6, p.tPerimeter());

    }
}
