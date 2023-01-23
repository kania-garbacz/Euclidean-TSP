package org.example;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Point> points = new ArrayList<>();
    private static List<String[]> statisticlist;


    public static void main(String[] args) {
        String[] header = {"Liczba punktow","Liczba operacji" ,"Czas dzialania (ms)", "Liczba obiektow"};

        statisticlist = new ArrayList<>();
        statisticlist.add(header);

        //testBruteForce();
        testNearestNeighbour();

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter("./result.csv"),';', '"', '"', "\n")) {
            writer.writeAll(statisticlist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testBruteForce() {
        System.out.println("Brute force test");

        int repeatCount = 10;
        long timeCount;
        long memoryCount;

        for (int counter = 1; counter < 11; counter++) {
            timeCount = 0;
            memoryCount = 0;

            for (int repeats = 0; repeats < repeatCount; repeats++) {
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

                timeCount += (end - start);
                memoryCount += bruteForce.getMemorySize();

                System.out.println("Liczba puntkow: " + counter);
                System.out.println("Najkrotsza trasa: ");
                System.out.println(bruteForce.getNajkrotszaTrasa().toString());
                System.out.println("Dlugosc najkrotszej trasy: " + bruteForce.getNajkrotszyDystans());
                System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");
                System.out.println("Zuzycie pamieci: " + bruteForce.getMemorySize());
            }


            String[] csvData = {String.valueOf(counter), String.valueOf((double) timeCount / repeatCount), String.valueOf(memoryCount / repeatCount)};
            statisticlist.add(csvData);
        }
    }

    private static void testNearestNeighbour() {
        System.out.println("Nearest neighbour test");
        for (int counter = 1; counter < 501; counter++){
            points = Utils.generatePoints(counter, -3, 10);
            NearestNeighbor nearestNeighbor = new NearestNeighbor(points);

            //nearestNeighbor.setStartingPoint();
            long start = System.nanoTime();
            nearestNeighbor.solve();
            long end = System.nanoTime();
            //nearestNeighbor.showHamiltonCycle();

            System.out.println("\n");
            System.out.println("Dlugosc najkrotszej trasy: " + nearestNeighbor.getRouteCost());
            System.out.println("Czas dzialania algorytmu: " + (end - start) + " ns");
            System.out.println("-------------------------------------------------------------------");

            String[] csvData = {String.valueOf(counter), String.valueOf( nearestNeighbor.getLiczba_operacji()), String.valueOf((end - start)), String.valueOf(nearestNeighbor.getMemorySize())};
            statisticlist.add(csvData);
        }


    }


}


