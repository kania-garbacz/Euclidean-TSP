package org.example;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final PointNew startPoint;
    private PointNew currentPoint;
    private List<PointNew> route = new ArrayList<>();


    public Route(PointNew startPoint) {
        this.startPoint = startPoint;
        this.currentPoint = startPoint;
        this.route.add(startPoint);
    }


    public PointNew getStartPoint() {
        return startPoint;
    }

    public PointNew getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(PointNew currentPoint) {
        this.currentPoint = currentPoint;
    }

    public List<PointNew> getRoutes() {
        return route;
    }

    public void setRoute(List<PointNew> route) {
        this.route = route;
    }

    public Route() {
        startPoint = null;
    }

    public void toString2() {
        for(int i = 0; i<this.route.size()-1; i++){
            this.route.get(i).showPoint2();
            System.out.print("->");
        }
        this.route.get(this.route.size()-1).showPoint2();
        System.out.println("\n");
    }

    public int getRouteSize(){
        return this.route.size();
    }
}
