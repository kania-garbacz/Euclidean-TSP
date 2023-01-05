package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        points = Utils.generatePoints(10, -3, 10);

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

        long start = System.currentTimeMillis();
        bruteForce.permute(new Route(), pointsNums);
        long end = System.currentTimeMillis();


        System.out.println("Najkrotsza trasa: ");
        System.out.println(bruteForce.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce.getNajkrotszyDystans());
        System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");

    }

    private static void testNearestNeighbour() {
        System.out.println("Nearest neighbour test");
        NearestNeighbor nearestNeighbor = new NearestNeighbor(points);

        nearestNeighbor.setStartingPoint();
        long start = System.currentTimeMillis();
        nearestNeighbor.solve();
        long end = System.currentTimeMillis();
        nearestNeighbor.showHamiltonCycle();

        System.out.println("\n");
        System.out.println("Dlugosc najkrotszej trasy: " + nearestNeighbor.getRouteCost());
        System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");

    }


}


