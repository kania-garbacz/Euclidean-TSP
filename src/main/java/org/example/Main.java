package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        points.add(new Point(0, 0, 0, false));
        points.add(new Point(1, 1, 1, false));
        points.add(new Point(2, 2, 2, false));
        points.add(new Point(3, 30, 3, false));
        points.add(new Point(4, 4, 10, false));
        points.add(new Point(5, 4, 10, false));

        testBruteForce();
//        testNearestNeighbour();
    }

    private static void testBruteForce() {
        System.out.println("Brute force test");
        BruteForce bruteForce = new BruteForce(points);

        List<Integer> pointsNums = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            pointsNums.add(i);
        }

        // find all permutations
        bruteForce.permute(new Route(), pointsNums);

        // Find shortest route in permutations
        bruteForce.findShortestPermutation();
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


