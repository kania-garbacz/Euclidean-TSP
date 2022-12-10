package org.example;

import java.util.List;

public class Utils {
    public static double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    public static double calculateDistanceInRoute(Route r) {
        double distance = 0.0;
        List<Point> pointsList = r.getRoutes();

        for (int i = 0; i < pointsList.size() - 1; i++) {
            distance += calculateDistance(pointsList.get(i), pointsList.get(i + 1));
        }
        distance += calculateDistance(pointsList.get(0), pointsList.get(pointsList.size() - 1));

        return distance;
    }
}
