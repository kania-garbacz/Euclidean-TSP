package org.example;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private Point currentPoint;
    private List<Point> points = new ArrayList<>();
    private double length;

    public Route(Point startPoint) {
        this.currentPoint = startPoint;
        this.points.add(startPoint);
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public double getLength() {
        return length;
    }

    public void calculateLength() {
        double distance = 0.0;

        for (int i = 0; i < points.size() - 1; i++) {
            distance += calculateDistance(points.get(i), points.get(i + 1));
        }
        distance += calculateDistance(points.get(points.size() - 1), points.get(0));

        this.length = distance;
    }

    public Route() {
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < this.points.size() - 1; i++) {
            s.append(this.points.get(i).toString()).append("->");
        }
        s.append(this.points.get(this.points.size() - 1).toString());
        return s.toString();
    }

    public double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}
