package Homeworks.Homework3.Task1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenericArray<T> implements Iterable<T> {
    private T[] array;
    private int size;
    private static final int startSize = 10;

    @SuppressWarnings("unchecked")
    public GenericArray() {
        this.array = (T[]) new Object[startSize];
        this.size = 0;
    }

    public void add(T t) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = t;
    }

    @SuppressWarnings("unchecked")
    public void remove(T t) {
        OptionalInt result = find(t);
        if (result.isPresent()) {
            array = (T[]) Stream.concat(Arrays.stream(array)
                            .filter(i -> i != array[result.getAsInt()]), Stream.of((T) null))
                    .toArray();
            size--;
        } else {
            System.out.println("Элемент " + t + " не найден в массиве");
        }
    }

    public OptionalInt find(T t) {
        return IntStream.range(0, size)
                .filter(index -> array[index].equals(t))
                .findFirst();
    }

    public String info() {
        return Arrays.toString(Arrays.stream(array, 0, size).toArray());
    }

    @SuppressWarnings("unchecked")
    private void resizeArray() {
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericIterator();
    }

    private class GenericIterator implements Iterator<T> {
        private int index;

        public GenericIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < size);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() method is not supported");
        }
    }
}


