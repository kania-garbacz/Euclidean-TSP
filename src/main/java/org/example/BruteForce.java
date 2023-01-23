package org.example;

import java.util.List;

import static org.example.Utils.calculateDistanceInRoute;

public class BruteForce {

    private int liczba_operacji =0;

    private int memorySize = 0;

    private final List<Point> points;

    public int getMemorySize() {
        return memorySize;
    }

    private Route najkrotszaTrasa;
    private double najkrotszyDystans;

    public BruteForce(List<Point> points) {
        this.points = points;
        this.najkrotszyDystans = Double.MAX_VALUE;
    }

    public Route getNajkrotszaTrasa() {
        return najkrotszaTrasa;
    }

    public double getNajkrotszyDystans() {
        return najkrotszyDystans;
    }

    public void permute(Route r, List<Integer> notVisited) {
        double routeSize;

        if (!notVisited.isEmpty()) {
            memorySize += r.getRouteSize() + notVisited.size();
            for (int i = 0; i < notVisited.size(); i++) {
                // Pointer to first city in list
                int temp = notVisited.remove(0);

                Route newRoute = new Route();

                for (Point c1 : r.getRoutes()) {
                    newRoute.getRoutes().add(c1);
                }

                memorySize += newRoute.getRouteSize();

                // Add the first city from notVisited to the route
                newRoute.getRoutes().add(points.get(temp));

                // Recursive call
                permute(newRoute, notVisited);

                notVisited.add(temp);
            }
        } else {
            // Route is complete
            routeSize = calculateDistanceInRoute(r);
            if (routeSize < najkrotszyDystans) {
                najkrotszyDystans = routeSize;
                najkrotszaTrasa = r;
            }
        }
    }
}
