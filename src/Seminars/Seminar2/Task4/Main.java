package Seminars.Seminar2.Task4;

import Seminars.Seminar2.Task3.BackEndProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        List<Developer> developers = new ArrayList<>();

        int tempInt = rand.nextInt(0, 100);
        for (int i = 0; i < tempInt; i++) {
            if (rand.nextBoolean()) {
                developers.add(new FrontEndDeveloper());
            } else {
                developers.add(new BackEndDeveloper());
            }

        }

        for (Developer developer : developers) {
            checkSpec(developer);
        }

        var test1 = new BackEndProgramming() {
            @Override
            public void writeBackEndCode() {
            }
        };


        var test2 = new BackEndProgramming() {
            @Override
            public void writeBackEndCode() {

            }
        };

        System.out.println(test1.getClass().toString());
        System.out.println(test2.getClass().toString());
    }

    static void checkSpec(Developer developer) {
        if (developer instanceof FrontEndDeveloper) {
            ((FrontEndDeveloper) developer).developGUI();
        } else {
            System.out.println("Create GUI not supported by this Developer");
        }

    }
}
