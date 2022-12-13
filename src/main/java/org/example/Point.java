package org.example;


//test
public class Point {
    private final int id;
    private final double x;
    private final double y;

    public Point(int vid, double vx, double vy) {
        this.id = vid;
        this.x = vx;
        this.y = vy;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}