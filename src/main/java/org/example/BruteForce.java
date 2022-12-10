package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.Utils.calculateDistance;
import static org.example.Utils.calculateDistanceInRoute;

public class BruteForce {

    private double[][] tablicaDrog;
    private static List<Point> points;
    private int n;


    public List<Route> getbFRoutePerms() {
        return bFRoutePerms;
    }

    private List<Route> bFRoutePerms = new ArrayList<>();

    public BruteForce(List<Point> points) {
        this.points = points;
    }

    public void fillTable() {
        if (points != null) {
            n = points.size();
            tablicaDrog = new double[n][n];

            for (int i = 0; i < points.size(); i++) {
                for (int j = 0; j < points.size(); j++) {
                    if (i == j) {
                        tablicaDrog[i][j] = 0.0;
                    } else {
                        tablicaDrog[i][j] = calculateDistance(points.get(i), points.get(j));
                    }
                }
            }
        }
    }

    public void findShortestPermutation(List<Route> bfRoutePerms) {
        double theShortestDistance = Double.MAX_VALUE;
        Route shortestRoute = null;

        for (Route route : bfRoutePerms) {
            double currentDistance = calculateDistanceInRoute(route);
            if (currentDistance < theShortestDistance) {
                theShortestDistance = currentDistance;
                shortestRoute = route;
            }
        }
        System.out.println("Dlugosc najkrotszej trasy: " + theShortestDistance);
        System.out.println("Najkrotsza trasa: ");
        shortestRoute.toString2();
    }

    public void permute(Route r, List<Integer> notVisited) {

        boolean isBruteForce = true;

        if (!notVisited.isEmpty()) {

            for (int i = 0; i < notVisited.size(); i++) {
                // Pointer to first city in list
                int temp = notVisited.remove(0);

                Route newRoute = new Route();
                // Lazy copy
                for (Point c1 : r.getRoutes()) {
                    newRoute.getRoutes().add(c1);
                }

                // Add the first city from notVisited to the route
                newRoute.getRoutes().add(points.get(temp));

                // Recursive call
                permute(newRoute, notVisited);
                // Add first city back into notVisited list
                notVisited.add(temp);
            }
        } else {
            // Route is complete
            bFRoutePerms.add(r);
        }
    }

    private void solve(boolean[] visited, double d) {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                for (int j = 0; j < n; j++) {
                    if ((i != j) && !visited[j]) {
                        d += calculateDistance(points.get(i), points.get(j));
                        solve(visited, d);
                    }
                }
            }
        }
    }
}
