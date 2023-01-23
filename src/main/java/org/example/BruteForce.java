package org.example;

import java.util.List;

import static org.example.Utils.calculateDistanceInRoute;

public class BruteForce {
    private int liczba_operacji = 0;

    public int getLiczba_operacji() {
        return liczba_operacji;
    }

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

    public void solve(Route r, List<Integer> notVisited) {
        double routeSize;
        memorySize += 1;

//        Dodanie wielkosci pamieci za pola najkrotszaTrasa oraz najkrotszyDystans
        memorySize += 2;

        if (!notVisited.isEmpty()) {
            memorySize += r.getRouteSize() + notVisited.size();
            for (int i = 0; i < notVisited.size(); i++) {
                // Pointer to first city in list
                int temp = notVisited.remove(0);
                liczba_operacji++;

                Route newRoute = new Route();
                liczba_operacji++;

                for (Point c1 : r.getRoutes()) {
                    newRoute.getRoutes().add(c1);
                    liczba_operacji++;
                }

                memorySize += newRoute.getRouteSize();
                liczba_operacji++;

                // Add the first city from notVisited to the route
                newRoute.getRoutes().add(points.get(temp));
                liczba_operacji++;

                // Recursive call
                solve(newRoute, notVisited);


                notVisited.add(temp);
                liczba_operacji++;
            }
        } else {
            // Route is complete
            routeSize = calculateDistanceInRoute(r);
            liczba_operacji++;

            if (routeSize < najkrotszyDystans) {
                najkrotszyDystans = routeSize;
                najkrotszaTrasa = r;
                liczba_operacji += 2;
            }

        }
    }
}
