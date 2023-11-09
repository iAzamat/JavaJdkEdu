/*
Создать два класс ObjectA, ObjectB
Реализовать класс в котором два потока вызовут DeadLock
 */

package Seminars.Seminar5.task1;

public class Main {
    public static void main(String[] args) {
        ObjectA obA = new ObjectA();
        ObjectB obB = new ObjectB();
        Object a = new Object();
        Object b = new Object();


        Thread threadA = new Thread(() -> {
            synchronized (obA) {
                System.out.println("ObjectA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ObjectB.class) {
                    System.out.println("ObjectB");
                }
            }

        });


        Thread threadB = new Thread(() -> {
            synchronized (ObjectB.class) {
                System.out.println("Thread B ObjectB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obA) {
                    System.out.println("ThreadB ObjectA");
                }
            }


        });

        threadA.start();
        threadB.start();

    }
}

//    private static final Object lock1 = new Object();
//    private static final Object lock2 = new Object();
//
//    public static void main(String[] args) {
//        DeadThreadOne threadOne = new DeadThreadOne();
//        DeadThreadTwo threadTwo = new DeadThreadTwo();
//        threadOne.start();
//        threadTwo.start();
//    }
//
//    private static class DeadThreadOne extends Thread {
//        public void run() {
//            synchronized (lock1) {
//                System.out.println("DeadThreadOne is holding LOCK 1...");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("DeadThreadOne is waiting for Lock 2...");
//                synchronized (lock2) {
//                    System.out.println("DeadThreadOne  is holding Lock 1 and Lock 2...");
//                }
//            }
//        }
//    }
//}
//
//    private static class DeadThreadTwo extends Thread {
//        public void run() {
//            synchronized (lock2) {
//                System.out.println("DeadThreadTwo is holding LOCK 2...");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("DeadThreadTwo is waiting for Lock 1...");
//                synchronized (lock1) {
//                    System.out.println("DeadThreadTwo  is holding Lock 1 and Lock 2...");
//                }
//            }
//        }
//    }
//}

