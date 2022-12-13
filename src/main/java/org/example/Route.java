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

    public String toString() {
        String s = "";

        for (int i = 0; i < this.route.size() - 1; i++) {
            s += this.route.get(i).toString() + "->";
        }
        s += this.route.get(this.route.size() - 1).toString();
        return s;
    }
}
