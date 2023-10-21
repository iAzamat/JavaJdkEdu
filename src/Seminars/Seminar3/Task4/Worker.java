package Seminars.Seminar3.Task4;

public class Worker implements Person{
    @Override
    public void doWork() {
        System.out.println(this.getClass().getName() + " Ready to work, sir");
    }

    @Override
    public void haveRest() {
        System.out.println(this.getClass().getName() + " i can't rest");
    }
}
