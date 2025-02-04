package org.example.arrays.flipOverArr;

import java.io.Closeable;
import java.util.Arrays;

/*
Задача - поменять местами элементы массива
 */

public class App {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(flipOverArr(arr)));
    }

    private static int[] flipOverArr(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int buf = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = buf;
        }
        return arr;
    }
}

abstract class AAA {
    protected AAA() {

    }
    public abstract void mmm ();



}

