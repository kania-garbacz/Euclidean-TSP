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
        String[] header = {"Liczba punktow","Liczba operacji" ,"Czas dzialania (ms)","droga_BF" ,"droga_NN","L","Błąd względny"};

        statisticlist = new ArrayList<>();
        statisticlist.add(header);

        //testBruteForce();
        //testNearestNeighbour();
        //compareDistances();
        //BruteForceWithResults();
        toCompareWithReferenceBruteForce();
        // default all fields are enclosed in double quotes
        // default separator is a comma
        //NearestNeighbourWithResults();
        try (CSVWriter writer = new CSVWriter(new FileWriter("./result.csv"), ';', '"', '"', "\n")) {
            writer.writeAll(statisticlist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         //toCompareWithReference();

        }

    private static void testBruteForce() {
        System.out.println("Brute force test");

        int repeatCount = 10;
        long timeCount;
        long memoryCount;

        for (int counter = 1; counter < 11; counter++) {
            timeCount = 0;
            memoryCount = 0;

//            for (int repeats = 0; repeats < repeatCount; repeats++) {
            points = Utils.generatePoints(counter, -3, 10);


            BruteForce bruteForce = new BruteForce(points);

            List<Integer> pointsNums = new ArrayList<>();
            for (int i = 0; i < points.size(); i++) {
                pointsNums.add(i);
            }

            long start = System.currentTimeMillis();
//        Metoda generująca najkrótszą ścieżkę Hamiltona
            bruteForce.solve(new Route(), pointsNums);
            long end = System.currentTimeMillis();

            timeCount += (end - start);
            memoryCount += bruteForce.getMemorySize();

            System.out.println("Liczba puntkow: " + counter);
            System.out.println("Najkrotsza trasa: ");
            System.out.println(bruteForce.getNajkrotszaTrasa().toString());
            System.out.println("Dlugosc najkrotszej trasy: " + bruteForce.getNajkrotszyDystans());
            System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");
            System.out.println("Zuzycie pamieci: " + bruteForce.getMemorySize());

            String[] csvData = {String.valueOf(counter), String.valueOf(bruteForce.getLiczba_operacji()), String.valueOf(timeCount), String.valueOf(memoryCount)};
            statisticlist.add(csvData);
        }


//            String[] csvData = {String.valueOf(counter), String.valueOf((double) timeCount / repeatCount), String.valueOf(memoryCount / repeatCount)};
//        statisticlist.add(csvData);
//        }
    }

    private static void testNearestNeighbour() {
        System.out.println("Nearest neighbour test");
        for (int counter = 1; counter < 10000; counter++) {
            points = Utils.generatePoints(counter, -3, 10);
            NearestNeighbor nearestNeighbor = new NearestNeighbor(points);

            //nearestNeighbor.setStartingPoint();
            long start = System.currentTimeMillis();
            nearestNeighbor.solve();
            long end = System.currentTimeMillis();
            //nearestNeighbor.showHamiltonCycle();





            System.out.println("\n");
            System.out.println("Dlugosc trasy: " + nearestNeighbor.getRouteCost());
            System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");
            System.out.println("-------------------------------------------------------------------");

            String[] csvData = {String.valueOf(counter), String.valueOf(nearestNeighbor.getLiczba_operacji()), String.valueOf((end - start)), String.valueOf(nearestNeighbor.getMemorySize())};
            statisticlist.add(csvData);
        }


    }
    private static void BruteForceWithResults(){
        points = Utils.generatePoints(10, -3, 10);


        BruteForce bruteForce = new BruteForce(points);

        List<Integer> pointsNums = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            pointsNums.add(i);
        }

        long start = System.currentTimeMillis();
//        Metoda generująca najkrótszą ścieżkę Hamiltona
        bruteForce.solve(new Route(), pointsNums);
        long end = System.currentTimeMillis();



        System.out.println("Liczba puntkow: " + points.size());
        System.out.println("Najkrotsza trasa: ");
        System.out.println(bruteForce.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce.getNajkrotszyDystans());



    }
    private static void NearestNeighbourWithResults(){
        System.out.println("Nearest neighbour");
        points = Utils.generatePoints(10, -3, 10);
        NearestNeighbor nearestNeighbor = new NearestNeighbor(points);
        System.out.println("Liczba puntkow: " + points.size());
        nearestNeighbor.setStartingPoint();
        nearestNeighbor.solve();
        nearestNeighbor.showHamiltonCycle();
        System.out.println("Dlugosc trasy trasy: " + nearestNeighbor.getRouteCost());
    }
    private static void compareDistances(){
        long start = System.currentTimeMillis();
        int size = 10;
        for (int counter = 1; counter < 1001 ; counter++){




            points = Utils.generatePoints(size, -10, 10);
            BruteForce bruteForce = new BruteForce(points);

            List<Integer> pointsNums = new ArrayList<>();
            for (int i = 0; i < points.size(); i++) {
                pointsNums.add(i);
            }

            bruteForce.solve(new Route(), pointsNums);

            NearestNeighbor nearestNeighbor = new NearestNeighbor(points);
            nearestNeighbor.solve();

            double droga_NN = nearestNeighbor.getRouteCost();
            double droga_BF = bruteForce.getNajkrotszyDystans();
            double bezwzgledny = Math.sqrt(Math.pow(droga_BF-droga_NN,2));

            System.out.println("--------------------------------");
            System.out.println("droga_NN="+droga_NN);
            System.out.println("droga_BF="+droga_BF);
            System.out.println("wzgledny="+((bezwzgledny/droga_BF)*100));
            System.out.println("--------------------------------");

            String[] csvData = {String.valueOf(size), String.valueOf("---"),String.valueOf("---"), String.valueOf(droga_NN), String.valueOf(droga_BF),String.valueOf((bezwzgledny/droga_BF)*100)};
            statisticlist.add(csvData);

        }
        long end = System.currentTimeMillis();
        System.out.println("Czas dzialania algorytmu: " + (end - start) + " ms");

    }
    private static void toCompareWithReference(){
        // porównałem z https://github.com/KubaWisniewski/TSPGeneticAlgorithm
        List<Point> points1 = new ArrayList<>();
        List<Point> points2 = new ArrayList<>();
        List<Point> points3 = new ArrayList<>();
        List<Point> points4 = new ArrayList<>();
        List<Point> points5 = new ArrayList<>();
        points1 = Utils.getCitiesFromFile("cities1.txt");
        points2 = Utils.getCitiesFromFile("cities2.txt");
        points3 = Utils.getCitiesFromFile("cities3.txt");
        points4 = Utils.getCitiesFromFile("cities4.txt");
        points5 = Utils.getCitiesFromFile("cities5.txt");
        NearestNeighbor nearestNeighbor = new NearestNeighbor(points1);
        nearestNeighbor.solve();
        System.out.println("Dlugosc trasy trasy: " + nearestNeighbor.getRouteCost());

        NearestNeighbor nearestNeighbor2 = new NearestNeighbor(points2);
        nearestNeighbor2.solve();
        System.out.println("Dlugosc trasy trasy: " + nearestNeighbor2.getRouteCost());

        NearestNeighbor nearestNeighbor3 = new NearestNeighbor(points3);
        nearestNeighbor3.solve();
        System.out.println("Dlugosc trasy trasy: " + nearestNeighbor3.getRouteCost());

        NearestNeighbor nearestNeighbor4 = new NearestNeighbor(points4);
        nearestNeighbor4.solve();
        System.out.println("Dlugosc trasy trasy: " + nearestNeighbor4.getRouteCost());

        NearestNeighbor nearestNeighbor5 = new NearestNeighbor(points5);
        nearestNeighbor5.solve();
        System.out.println("Dlugosc trasy trasy: " + nearestNeighbor5.getRouteCost());
    }
    private static void toCompareWithReferenceBruteForce(){
        // porównałem z https://github.com/KubaWisniewski/TSPGeneticAlgorithm
        List<Point> points1 = new ArrayList<>();
        List<Point> points2 = new ArrayList<>();
        List<Point> points3 = new ArrayList<>();
        List<Point> points4 = new ArrayList<>();
        List<Point> points5 = new ArrayList<>();
        points1 = Utils.getCitiesFromFile("cities1.txt");
        points2 = Utils.getCitiesFromFile("cities2.txt");
        points3 = Utils.getCitiesFromFile("cities3.txt");
        points4 = Utils.getCitiesFromFile("cities4.txt");
        points5 = Utils.getCitiesFromFile("cities5.txt");

        BruteForce bruteForce1 = new BruteForce(points1);

        List<Integer> pointsNums = new ArrayList<>();
        for (int i = 0; i < points1.size(); i++) {
            pointsNums.add(i);
        }

        bruteForce1.solve(new Route(), pointsNums);
        System.out.println(bruteForce1.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce1.getNajkrotszyDystans());

        BruteForce bruteForce2 = new BruteForce(points2);
        bruteForce2.solve(new Route(), pointsNums);
        System.out.println(bruteForce2.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce2.getNajkrotszyDystans());

        BruteForce bruteForce3 = new BruteForce(points3);
        bruteForce3.solve(new Route(), pointsNums);
        System.out.println(bruteForce3.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce3.getNajkrotszyDystans());


        BruteForce bruteForce4 = new BruteForce(points4);
        bruteForce4.solve(new Route(), pointsNums);
        System.out.println(bruteForce4.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce4.getNajkrotszyDystans());

        BruteForce bruteForce5 = new BruteForce(points5);
        bruteForce5.solve(new Route(), pointsNums);
        System.out.println(bruteForce5.getNajkrotszaTrasa().toString());
        System.out.println("Dlugosc najkrotszej trasy: " + bruteForce5.getNajkrotszyDystans());


    }


}


