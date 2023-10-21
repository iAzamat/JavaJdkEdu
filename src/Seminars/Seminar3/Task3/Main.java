package Seminars.Seminar3.Task3;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        GenericIterator<Integer> genericIterator = new GenericIterator<>(arr);

        while (genericIterator.hasNext()){
            System.out.println(genericIterator.next());
        }

        for (Integer item : genericIterator) {
            System.out.println(item);
        }
    }
}