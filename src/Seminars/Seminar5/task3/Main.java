/*
3 бегуна должны прийти к старту гонки
Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
Программа должна отсчитать “На старт”, “Внимание”, “Марш”
Программа должна завершиться когда все участники закончат гонку.
 */

package Seminars.Seminar5.task3;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    static AtomicInteger runnerA = new AtomicInteger(); // 0 - не готов, 1 - готов, 2 - бежит, 3 - прибежал
    static AtomicInteger runnerB = new AtomicInteger();
    static AtomicInteger runnerC = new AtomicInteger();
    static Random rand = new Random();

    public static void main(String[] args) {
        runnerB.set(0);
        runnerA.set(0);
        runnerC.set(0);

        Thread runA = new Thread(() -> {
            try {
                System.out.println(System.nanoTime() + "Бегун 1 начал подготовку");
                Thread.sleep(rand.nextInt(5000));
                runnerA.set(1);
                System.out.println(System.nanoTime() + "Бегун 1 готов!");
                while (runnerA.get() != 2) {
                    Thread.sleep(2);
                }
                Thread.sleep(rand.nextInt(5000));
                runnerA.set(3);
                System.out.println(System.nanoTime() + "Бегун 1 финишировал");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread runB = new Thread(() -> {
            try {
                System.out.println(System.nanoTime() + "Бегун 2 начал подготовку");
                Thread.sleep(rand.nextInt(5000));
                runnerB.set(1);
                System.out.println(System.nanoTime() + "Бегун 2 готов!");
                while (runnerB.get() != 2) {
                    Thread.sleep(2);
                }
                Thread.sleep(rand.nextInt(5000));
                runnerB.set(3);
                System.out.println(System.nanoTime() + "Бегун 2 финишировал");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread runC = new Thread(() -> {
            try {
                System.out.println(System.nanoTime() + "Бегун 3 начал подготовку");
                Thread.sleep(rand.nextInt(5000));
                runnerC.set(1);
                System.out.println(System.nanoTime() + "Бегун 3 готов!");
                while (runnerC.get() != 2) {
                    Thread.sleep(2);
                }
                Thread.sleep(rand.nextInt(5000));
                runnerC.set(3);
                System.out.println(System.nanoTime() + "Бегун 3 финишировал");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread judge = new Thread(() -> {
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            while (!(runnerA.get() == 3 && runnerB.get() == 3 && runnerC.get() == 3)) {
                if (runnerA.get() == 1 && runnerB.get() == 1 && runnerC.get() == 1) {
                    System.out.println("На старт");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Внимание!");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Марш!!!");
                    runnerA.set(2);
                    runnerB.set(2);
                    runnerC.set(2);

                }
            }
            System.out.println("Гонка завершена!");
        });

        runA.start();
        runB.start();
        runC.start();
        judge.start();


    }
}
