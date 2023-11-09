package Homeworks.Homework5;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger philosopher_1 = new AtomicInteger(); // 1 - кушает, 0 - размышляет
    static AtomicInteger philosopher_2 = new AtomicInteger();
    static AtomicInteger philosopher_3 = new AtomicInteger();
    static AtomicInteger philosopher_4 = new AtomicInteger();
    static AtomicInteger philosopher_5 = new AtomicInteger();
    static AtomicInteger foodTable = new AtomicInteger();  // 1 - стол занят, 0 - стол свободен
    static Random rand = new Random();

    public static void philosopherAction(String name, int count, AtomicInteger atomicInteger) throws InterruptedException {
        System.out.println("Философ " + name + " начал размышлять");
        while (count != 3) {
            if (foodTable.get() == 0) {
                foodTable.set(1);
                atomicInteger.set(1);
                System.out.println("Философ " + name + " кушает");
                Thread.sleep(500);
                count++;
                atomicInteger.set(0);
                foodTable.set(0);
                System.out.println("Философ " + name + " продолжает размышлять");
                Thread.sleep(rand.nextInt(5000));
            }
        }
        System.out.println("Философ " + name + " покушал " + count + " раза");
    }

    public static void main(String[] args) {
        Thread philA = new Thread(() -> {
            int count = 0;
            try {
                philosopherAction("1", count, philosopher_1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread philB = new Thread(() -> {
            int count = 0;
            try {
                philosopherAction("2", count, philosopher_2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread philC = new Thread(() -> {
            int count = 0;
            try {
                philosopherAction("3", count, philosopher_3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread philD = new Thread(() -> {
            int count = 0;
            try {
                philosopherAction("4", count, philosopher_4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread philE = new Thread(() -> {
            int count = 0;
            try {
                philosopherAction("5", count, philosopher_5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        philA.start();
        philB.start();
        philC.start();
        philD.start();
        philE.start();
    }
}
