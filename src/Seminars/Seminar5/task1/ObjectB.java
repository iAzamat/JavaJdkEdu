/*
Создать два класс ObjectA, ObjectB
Реализовать класс в котором два потока вызовут DeadLock
 */
package Seminars.Seminar5.task1;

public class ObjectB implements Runnable {
    private String name;
    @Override
    public void run() {
        System.out.println("Выполняется класс Б");
    }

    void sleep() throws InterruptedException {
        Thread.sleep(4000);
    }
}
