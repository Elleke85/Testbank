package com.example.test.model;

/**
 * Deze classe is gemaakt tijdens de workshop testing om kleine Unittest te proberen
 * Deze classe is geen onderdeel van de testbank
 */
public class Circle {


    private double radius;

    public Circle(double radius) throws IllegalArgumentException {
        super();
        if (radius < 0.00) {
            throw new IllegalArgumentException("Radius can not be negative");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea (){
        return radius * radius * Math.PI;
    }

    public double getCircumference () {
        return 2 * radius * Math.PI;
    }


}
