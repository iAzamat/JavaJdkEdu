package Seminars.Seminar3.Task2;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenericArray<T> {
    Object[] arr;

    GenericArray() {
        arr = new Object[0];
    }


    public void add(T t) {
        arr = Stream.concat(Arrays.stream(arr), Stream.of(t))
                .toArray();
    }

    public void remove(T t) {
        OptionalInt result = find(t);
        if (result.isPresent()) {
            arr = Arrays.stream(arr)
                    .filter(i -> i != arr[result.getAsInt()])
                    .toArray();
        } else {
            System.out.println("Элемент " + t + " не найден в массиве");
        }
    }

    public OptionalInt find(T t) {
        return IntStream.range(0, arr.length)
                .filter(index -> arr[index].equals(t))
                .findFirst();
    }

    public String info() {
        return Arrays.toString(arr);
    }
}


