package Lections.Lection2.samples;

public interface Human {
    default void walk() {
        System.out.println("Walks on two feet");
    }


    public void talk();
}
