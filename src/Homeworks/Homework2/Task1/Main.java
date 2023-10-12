package Homeworks.Homework2.Task1;

public class Main {
    public static <T extends Number> double sum(Pair<T, T> pair) {
        T first = pair.getFirst();
        T second = pair.getSecond();
        return first.doubleValue() + second.doubleValue();
    }

    public static <T extends String> String concat(Pair<T, T> pair) {
        T first = pair.getFirst();
        T second = pair.getSecond();
        return first.toString() + second.toString();
    }

    public static void main(String[] args) {
        Pair<Integer, Integer> pair = new Pair<>(10, 20);
        System.out.println("Сумма: " + sum(pair));
        Pair<String, String> pair2 = new Pair<>("Hello", " World!");
        System.out.println(concat(pair2));
    }
}
