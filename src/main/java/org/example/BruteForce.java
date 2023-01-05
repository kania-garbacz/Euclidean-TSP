package org.example;

import java.util.List;

import static org.example.Utils.calculateDistanceInRoute;

public class BruteForce {

    private final List<Point> points;

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
            routeSize = calculateDistanceInRoute(r);

            if (routeSize < najkrotszyDystans) {
                najkrotszyDystans = routeSize;
                najkrotszaTrasa = r;
            }
        }
    }
}
