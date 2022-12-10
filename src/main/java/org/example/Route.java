package org.example;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final Point startPoint;
    private Point currentPoint;
    private List<Point> route = new ArrayList<>();


    public Route(Point startPoint) {
        this.startPoint = startPoint;
        this.currentPoint = startPoint;
        this.route.add(startPoint);
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
        return route;
    }

    public void setRoute(List<Point> route) {
        this.route = route;
    }

    public Route() {
        startPoint = null;
    }

    public void toString2() {
        for (int i = 0; i < this.route.size() - 1; i++) {
            this.route.get(i).showPoint2();
            System.out.print("->");
        }
        this.route.get(this.route.size() - 1).showPoint2();
        System.out.println("\n");
    }

    public int getRouteSize() {
        return this.route.size();
    }
}
