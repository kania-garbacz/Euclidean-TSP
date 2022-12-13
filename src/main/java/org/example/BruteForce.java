package org.example;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    private static List<Point> points;
    private final List<Route> bFRoutePerms = new ArrayList<>();
    private Route shortestRoute;

    public BruteForce(List<Point> points) {
        BruteForce.points = points;
    }

    public void findShortestPermutation() {
        double theShortestDistance = Double.MAX_VALUE;

        for (Route route : bFRoutePerms) {
            route.calculateLength();
            if (route.getLength() < theShortestDistance) {
                theShortestDistance = route.getLength();
                this.shortestRoute = route;
            }
        }

        System.out.println("Dlugosc najkrotszej drogi: " + theShortestDistance);
        System.out.println("Najkrotsza droga: ");
        System.out.println(shortestRoute.toString());
    }

    public void permute(Route r, List<Integer> notVisited) {
        if (!notVisited.isEmpty()) {
            for (int i = 0; i < notVisited.size(); i++) {
                int temp = notVisited.remove(0);

                Route newRoute = new Route();
                for (Point c1 : r.getPoints()) {
                    newRoute.getPoints().add(c1);
                }

                newRoute.getPoints().add(points.get(temp));
                permute(newRoute, notVisited);
                notVisited.add(temp);
            }
        } else {
            bFRoutePerms.add(r);
        }
    }
}
