package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
     Square.printSquareArea(new Square(7.0));

     Triangle.printTriangleArea(new Triangle(4,5,6));

     Triangle.printTrianglePerimeter(new Triangle(2,2,2));

     Rectangle.printRectangleArea(7.0, 9.0);

    }

}
