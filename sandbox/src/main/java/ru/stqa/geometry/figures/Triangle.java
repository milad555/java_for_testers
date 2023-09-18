package ru.stqa.geometry.figures;

public class Triangle {
    private double side1;
    private double side2;
    private double side3;

    public Triangle (double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public static void printTriangleArea(Triangle a){
        String text = String.format("Площадь треугольника со сторонами %f, %f , %f  = %f ",a.side1, a.side2, a.side3, a.tArea());
        System.out.println(text);
    }

    public double tArea(){
        double p = 0.5 * (this.side1 + this.side2 + this.side3);
        return Math.sqrt(p * ((p - this.side1) * (p - this.side2) * (p - this.side3)));
    }

    public double tPerimeter(){
        return this.side1 + this.side2 + this.side3;
    }
    public static void printTrianglePerimeter(Triangle a){
        String text = String.format("Площадь треугольника со сторонами %f, %f , %f  = %f ",a.side1, a.side2, a.side3, a.tPerimeter());
        System.out.println(text);
    }
}