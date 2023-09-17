package ru.stqa.geometry.figures;

public class Square {
   public static void printSquareArea(double side) {
       String text = String.format("Площадь квадрата со стороной %f = %f ", side, square(side));
       System.out.println(text);
    }

    public static double square(double a) {
        return a * a;
    }

    public static double perimeter(double a) {
       return 4 * a ;
    }
}
