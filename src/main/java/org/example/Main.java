package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testBruteForce();
        testNearestNeighbour();
    }

    private static void testBruteForce() {
        System.out.println("Brute force test");

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0, 0, false));
        points.add(new Point(1, 1, 1, false));
        points.add(new Point(2, 2, 2, false));
        points.add(new Point(3, 30, 3, false));
        points.add(new Point(4, 4, 10, false));

        BruteForce bruteForce = new BruteForce(points);
        bruteForce.fillTable();

        List<Integer> pointsNums = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            pointsNums.add(i);
        }

        bruteForce.permute(new Route(), pointsNums);

        List<Route> routes = bruteForce.getbFRoutePerms();

        for (int i = 0; i < routes.size(); i++) {
            routes.get(i).toString2();
        }

        bruteForce.findShortestPermutation(routes);
    }

    private static void testNearestNeighbour() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0, 0));
        points.add(new Point(1, 1, 1));
        points.add(new Point(2, 2, 2));
        points.add(new Point(3, 30, 3));
        points.add(new Point(4, 4, 10));


        NearestNeighbor nearestNeighbor = new NearestNeighbor(points);
        nearestNeighbor.setStartingPoint();
        nearestNeighbor.solve();
        nearestNeighbor.showHamiltonCycle();
        System.out.println("\n");
        System.out.println("Koszt drogi:" + nearestNeighbor.getRouteCost());

    }


}


