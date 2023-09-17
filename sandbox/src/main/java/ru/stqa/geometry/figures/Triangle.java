package ru.stqa.geometry.figures;

public class Triangle {
//    private double side1;
//    private double side2;
//    private double side3;
//
//    public Triangle (double side1, double side2, double side3){
//        this.side1 = side1;
//        this.side2 = side2;
//        this.side3 = side3;
//    }

    public static void printTriangleArea(double a, double b, double c) {
        double area = tArea(a, b, c);
        var text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f", a, b, c, area);
        System.out.println(text);
    }


    public static double tArea(double a, double b, double c) {
        double p = 0.5 * (a + b + c);
        return Math.sqrt(p * ((p - a) * (p - b) * (p - c)));
    }

    public static double tPerimeter(double a, double b, double c){
        return a + b + c;

    }

    public static void printTrianglePerimeter(double a, double b, double c){
        double perimeter = tPerimeter(a,b,c);
        var text = String.format("Периметр треугольника со сторонами %f, %f, %f = %f ", a, b, c, perimeter);
        System.out.println(text);
    }

}
