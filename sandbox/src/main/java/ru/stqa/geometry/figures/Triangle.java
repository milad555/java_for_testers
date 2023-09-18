package ru.stqa.geometry.figures;

public record Triangle( double a, double b, double c) {


    public static void printTriangleArea(Triangle a){
        String text = String.format("Площадь треугольника со сторонами %f, %f , %f  = %f ",a.a, a.b, a.c, a.tArea());
        System.out.println(text);
    }

    public double tArea(){
        double p = 0.5 * (this.a + this.b + this.c);
        return Math.sqrt(p * ((p - this.a) * (p - this.b) * (p - this.c)));
    }

    public double tPerimeter(){
        return this.a + this.b + this.c;
    }
    public static void printTrianglePerimeter(Triangle a){
        String text = String.format("Площадь треугольника со сторонами %f, %f , %f  = %f ",a.a, a.b, a.c, a.tPerimeter());
        System.out.println(text);
    }
}