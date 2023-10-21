package Seminars.Seminar3.Task4;

import java.util.Random;

public class Main{
    public static void main(String[] args) {
        Random rand = new Random();

        int quantity = rand.nextInt(10, 20);
        Person[] persons = new Person[quantity];

        for (int i = 0; i < quantity; i++) {
            if (rand.nextBoolean()){
                persons[i] = new Worker();
            } else {
                persons[i] = new Idle();
            }
        }

        System.out.println("------------Club -------------");
        Club<Person> club = new Club<>(persons);
        System.out.println("------------Workplace -------------");
        Workplace<Person> workplace = new Workplace<>(persons);
    }
}