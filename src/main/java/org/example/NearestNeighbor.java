package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NearestNeighbor {
    private ArrayList<Point> points;
    private ArrayList<Point> hamiltonCycle;
    private double routeCost = 0;
    private int numberOfUnvisitedPoints;


    public NearestNeighbor(ArrayList<Point> listPoints) {
        this.points = listPoints;
        this.numberOfUnvisitedPoints = this.points.size();
        this.hamiltonCycle = new ArrayList<>();
    }

    public double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    //wyświetl wszystkie elementy w liście punktów typu punkt
    public void showPoints() {
        for (Point p : points) {
            System.out.println(p.toString());
        }
    }

    public void showHamiltonCycle() {
        for (int i = 0; i < this.hamiltonCycle.size() - 1; i++) {
            System.out.print(this.hamiltonCycle.get(i).toString());
            System.out.print("->");
        }
        System.out.println(this.hamiltonCycle.get(this.hamiltonCycle.size() - 1).toString());
    }

    public double getRouteCost() {
        return this.routeCost;
    }

    //Ustawienie pierwszego punktu na początek listy
    public void setStartingPoint() {
        this.showPoints();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an ID: ");
        int number = input.nextInt();
        input.close();
        //znajdz element w liście puntów
        for (Point spoint : this.points) {
            if (spoint.getId() == number) {
                System.out.println(spoint);
                int index = this.points.indexOf(spoint);
                Collections.swap(this.points, index, 0);
            }
        }
        this.showPoints();
    }

    public void solve() {
        //początkowy punkt
        Point curretPointBuffor = this.points.get(0);//Wyjmujemy punkt z naszej listy nieodwiedzonych puntków
        Point firstPoint = curretPointBuffor; // musimy zapamiętać pierwszy punkt
        this.hamiltonCycle.add(curretPointBuffor);//Początkowy punkt dodajemy do naszego cyklu hamiltona
        this.points.remove(0);// Punkt podstawowy usuwamy z naszej listy punktów nieodwiedzonych
        this.numberOfUnvisitedPoints--;// Odwiedzilismy pierwszy punkt więc dekrementujemy liczbę nieodwiedzonych punktów


        while (this.numberOfUnvisitedPoints > 0) {
            // sprawdzamy która ścieżka jest nakrótsza
            double previousDistance = Double.MAX_VALUE; // wartości początkowe
            Point previousPoint = null;
            int index = 0;

            for (Point point : this.points) {
                double currentDistance = this.distance(curretPointBuffor, point);

                if (currentDistance < previousDistance) {
                    previousDistance = currentDistance;
                    previousPoint = point;
                    index = this.points.indexOf(point);
                    System.out.println(previousDistance);
                }
            }
            //po sprawdzeniu która ścieżka jest najkrótsza, to wybieramy ten punkt i dodajemy go do cyklu Hamiltona
            this.hamiltonCycle.add(previousPoint);
            //dodajemy koszt ścieżki
            this.routeCost += previousDistance;
            //Ustawiamy punkt[i+1] jako aktualny
            curretPointBuffor = previousPoint;
            //teraz usuwamy punkt[i+1] z listy
            this.points.remove(index);
            // dekrementujemy liczbę nieodwiedzonych puntków
            this.numberOfUnvisitedPoints--;

        }
        this.hamiltonCycle.add(firstPoint);
        double Distance = this.distance(curretPointBuffor, firstPoint);
        //Teraz musimy obliczyć odległość między ostatnim a pierwszym i dodać go na koniec wizualizacji cyklu hamiltona
        this.routeCost += Distance;
        System.out.println(Distance);


    }


}
