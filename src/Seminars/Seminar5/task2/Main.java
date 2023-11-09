/*
Создайте два потока A и B.
Поток A меняет значение Boolean переменной switcher с задержкой 1000 миллисекунд (true в состояние false и наоборот).
Поток B ожидает состояния true переменной switcher и выводит на консоль обратный
отсчет от 100 с задержкой 100 миллисекунд и приостанавливает свое действие, как только поток
A переключит switcher в состояние false.
Условием завершения работы потоков является достижение отсчета нулевой отметки.
 */

package Seminars.Seminar5.task2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicBoolean switcher = new AtomicBoolean(true);
        AtomicInteger a = new AtomicInteger(100);
        Object x = new Object();


        Thread threadA = new Thread(() -> {
            while (a.get() > 0) {
                try {
                    Thread.sleep(10000);
                    //System.out.println("Thread A work " + switcher);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switcher.set(!switcher.get());
                //System.out.println(switcher);
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (a.get() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (switcher.get()) {
                    System.out.println(a);
                    a.set(a.get() - 1);
                } else {

                }

            }
        });

        threadA.start();
        threadB.start();
    }


}
