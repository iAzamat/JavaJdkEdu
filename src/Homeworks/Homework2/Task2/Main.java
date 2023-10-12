package Homeworks.Homework2.Task2;

import Homeworks.Homework2.Task2.AbstractClass.Developer;
import Homeworks.Homework2.Task2.Developer.BackEnd;
import Homeworks.Homework2.Task2.Developer.FrontEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void checkSpec(Developer developer) {
        if (developer instanceof FrontEnd) {
            ((FrontEnd) developer).programUIForms();
        } else {
            System.out.println("Create GUI not supported by this Developer");
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<Developer> developers = new ArrayList<>();

        int tempInt = rand.nextInt(0, 100);
        for (int i = 0; i < tempInt; i++) {
            if (rand.nextBoolean()) {
                developers.add(new FrontEnd());
            } else {
                developers.add(new BackEnd());
            }

        }

        for (Developer developer : developers) {
            checkSpec(developer);
        }
    }
}
