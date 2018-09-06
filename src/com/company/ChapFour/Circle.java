package com.company.ChapFour;

public class Circle extends Shape {
    protected double radius;
    public Circle(Point point, double radius) {
        super(point);
        this.radius = radius;
    }

    @Override
    public Point getCenter() {
        return null;
    }
}
