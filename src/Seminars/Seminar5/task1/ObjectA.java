package Seminars.Seminar5.task1;

public class ObjectA extends Thread{
    private String name;
    @Override
    public void run() {
        System.out.println("Выполняется класс A");
    }

    void sleep() throws InterruptedException {
        Thread.sleep(4000);
    }


}
