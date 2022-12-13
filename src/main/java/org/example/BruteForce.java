package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.Utils.calculateDistanceInRoute;

public class BruteForce {
    private static List<Point> points;
    private Route shortestRoute;


    public List<Route> getbFRoutePerms() {
        return bFRoutePerms;
    }

    private List<Route> bFRoutePerms = new ArrayList<>();

    public BruteForce(List<Point> points) {
        this.points = points;
    }

    public void findShortestPermutation(List<Route> bfRoutePerms) {
        double theShortestDistance = Double.MAX_VALUE;

        for (Route route : bfRoutePerms) {
            double currentDistance = calculateDistanceInRoute(route);
            if (currentDistance < theShortestDistance) {
                theShortestDistance = currentDistance;
                this.shortestRoute = route;
            }
        }

        System.out.println("Dlugosc najkrotszej trasy: " + theShortestDistance);
        System.out.println("Najkrotsza trasa: ");
        System.out.println(shortestRoute.toString());
    }

    public void permute(Route r, List<Integer> notVisited) {
        if (!notVisited.isEmpty()) {
            for (int i = 0; i < notVisited.size(); i++) {
                int temp = notVisited.remove(0);

                Route newRoute = new Route();
                for (Point c1 : r.getRoutes()) {
                    newRoute.getRoutes().add(c1);
                }

                newRoute.getRoutes().add(points.get(temp));
                permute(newRoute, notVisited);
                notVisited.add(temp);
            }
        } else {
            bFRoutePerms.add(r);
        }
    }
}
