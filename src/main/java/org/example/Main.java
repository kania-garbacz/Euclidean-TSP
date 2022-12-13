package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        points.add(new Point(0, 110, -80));
        points.add(new Point(1, 1, 1));
        points.add(new Point(2, 2, 2));
        points.add(new Point(3, 30, 3));
        points.add(new Point(4, 4, 10));
        points.add(new Point(5, 4, 10));

        testBruteForce();
        testNearestNeighbour();
    }

    private static void testBruteForce() {
        System.out.println("Brute force test");
        BruteForce bruteForce = new BruteForce(points);

        List<Integer> pointsNums = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            pointsNums.add(i);
        }

        bruteForce.permute(new Route(), pointsNums);
        bruteForce.findShortestPermutation();

        Route route = bruteForce.getShortestRoute();

        System.out.println("Dlugosc najkrotszej drogi: " + route.getLength());
        System.out.println("Najkrotsza droga: ");
        System.out.println(route);
    }

    private static void testNearestNeighbour() {
        System.out.println("Nearest neighbour test");
        NearestNeighbor nearestNeighbor = new NearestNeighbor(points);

        nearestNeighbor.setStartingPoint();
        nearestNeighbor.solve();
        nearestNeighbor.showHamiltonCycle();

        System.out.println("\n");
        System.out.println("Koszt drogi:" + nearestNeighbor.getRouteCost());

    }


}


