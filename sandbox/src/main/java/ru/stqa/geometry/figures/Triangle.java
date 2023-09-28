package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(
        double a,
        double b,
        double c
) {

    public Triangle {
        if (((a < 0 || b < 0 || c < 0))){
            throw new IllegalArgumentException("Triangle sides should be non-negative");
        }
        if ((a + b < c) || (a + c < b) || (b + c < a)){
            throw new IllegalArgumentException("Sum of any two of triangle sides should not be less than third side");
        }

    }
    public static void printTriangleArea(Triangle a){
        String text = String.format("Площадь треугольника со сторонами %f, %f , %f  = %f ",a.a, a.b, a.c, a.tArea());
        System.out.println(text);
    }

    public double tArea(){
        double p = 0.5 * tPerimeter();
        return Math.sqrt(p * ((p - this.a) * (p - this.b) * (p - this.c)));
    }

    public double tPerimeter(){
        return this.a + this.b + this.c;
    }
    public static void printTrianglePerimeter(Triangle a){
        String text = String.format("Площадь треугольника со сторонами %f, %f , %f  = %f ",a.a, a.b, a.c, a.tPerimeter());
        System.out.println(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return     (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.a) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}