package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        distance += calculateDistance(pointsList.get(pointsList.size() - 1), pointsList.get(0));

        return distance;
    }

    // Funkcja generujaca liste punktow do zadania ETSP
        // size - liczba punktow,
        // minValue - dolny przedzial losowania,
        // maxValue - gorny przedzial losowania,
    public static List<Point> generatePoints(int size, double minValue, double maxValue) {
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            double x = ThreadLocalRandom.current().nextDouble(minValue, maxValue);
            double y = ThreadLocalRandom.current().nextDouble(minValue, maxValue);

            points.add(new Point(i, x, y));
        }
        return points;
    }
}
