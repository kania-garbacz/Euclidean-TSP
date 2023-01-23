package org.example;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Point> points = new ArrayList<>();

    private Runtime rt = Runtime.getRuntime();
    private long total_mem = rt.totalMemory();
    private long free_mem = rt.freeMemory();


    public static void main(String[] args) {
        testBruteForce();
//        testNearestNeighbour();


        List<String[]> csvData = createCsvDataSpecial();

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter("./result.csv"))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String[]> createCsvDataSpecial() {

        String[] header = {"Liczba punktow", "Czas dzialania (ms)", "Liczba obiektow"};
        String[] record1 = {"Dell", "P3421W", "Dell 34, Curved, USB-C Monitor", "2499.00"};
        // embedded double quotes

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);


        return list;

    }

    private static void testBruteForce() {
        System.out.println("Brute force test");

        for (int counter = 5; counter < 11; counter++) {
            points = Utils.generatePoints(counter, -3, 10);


            BruteForce bruteForce = new BruteForce(points);

            List<Integer> pointsNums = new ArrayList<>();
            for (int i = 0; i < points.size(); i++) {
                pointsNums.add(i);
            }

            long start = System.currentTimeMillis();
//        Metoda generująca najkrótszą ścieżkę Hamiltona
            bruteForce.permute(new Route(), pointsNums);
            long end = System.currentTimeMillis();


            System.out.println("Liczba puntkow: " + counter);
            System.out.println("Najkrotsza trasa: ");
            System.out.println(bruteForce.getNajkrotszaTrasa().toString());
            System.out.println("Dlugosc najkrotszej trasy: " + bruteForce.getNajkrotszyDystans());
            System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");
            System.out.println("Zuzycie pamieci: " + bruteForce.getMemorySize());
        }
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


