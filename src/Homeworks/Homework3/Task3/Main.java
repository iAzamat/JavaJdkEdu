package Homeworks.Homework3.Task3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static <T> boolean compareArrays(T[] arraySrc, T[] arrayDst) {
        if (arraySrc.length != arrayDst.length) {
            return false;
        }

        return IntStream.range(0, arraySrc.length)
                .allMatch(i -> arraySrc[i].equals(arrayDst[i]));
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        Integer[] arr3 = {1, 2, 5};
        Double[] arr4 =  {1.0, 2.0, 3.0};
        Integer[] arr5 = {1, 2, 3, 45};

        System.out.println("array1: "+Arrays.toString(arr1)+
                ", array2: "+Arrays.toString(arr2) +
                ", result: "+compareArrays(arr1, arr2));
        System.out.println("array1: "+Arrays.toString(arr1)+
                ", array2: "+Arrays.toString(arr3) +
                ", result: "+compareArrays(arr1, arr3));
        System.out.println("array1: "+Arrays.toString(arr1)+
                ", array2: "+Arrays.toString(arr4) +
                ", result: "+compareArrays(arr1, arr4));
        System.out.println("array1: "+Arrays.toString(arr1)+
                ", array2: "+Arrays.toString(arr5) +
                ", result: "+compareArrays(arr1, arr5));
    }
}
