package org.example;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private Point startPoint;
    private Point currentPoint;
    private List<Point> pointsList = new ArrayList<>();


    public Route(Point startPoint) {
        this.startPoint = startPoint;
        this.currentPoint = startPoint;
        this.pointsList.add(startPoint);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public List<Point> getRoutes() {
        return pointsList;
    }

    public void setPointsList(List<Point> pointsList) {
        this.pointsList = pointsList;
    }

    public Route() {
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.pointsList.size() - 1; i++) {
            result.append("(").append(this.pointsList.get(i).getX()).append(",").append(this.pointsList.get(i).getY()).append(") -> ");
        }
        result.append("(").append(this.pointsList.get(this.pointsList.size() - 1).getX()).append(",").append(this.pointsList.get(this.pointsList.size() - 1).getY()).append(")");
        return result.toString();
    }

    public int getRouteSize() {
        return this.pointsList.size();
    }

    public double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}