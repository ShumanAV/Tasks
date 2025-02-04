package org.example.arrays.moveZeroes;

import java.util.Arrays;

/*
Задача - Дан целочисленный массив nums. Необходимо переместить все нулевые элементы в конец массива, сохраняя относительный
порядок элементов, не являющихся нулем. Решение должно производиться на месте, без использования дополнительного массива,
а также должно иметь минимальную сложность по времени и пространству.
 */

public class App {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(moveZeroes(arr)));
        System.out.println(Arrays.toString(moveZeroes2(arr)));
    }

    /*
    Сложность O(n)
     */

    private static int[] moveZeroes2(int[] arr) {
        //создадим номер числа, которое не равно 0, и просто будем перезаписывать этот же массив
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }
        while (index < arr.length) {
            arr[index++] = 0;
        }
        return arr;
    }

    /*
    Сложность O(n2)
     */
    private static int[] moveZeroes(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                moveElements(arr, i);
            }
        }
        return arr;
    }

    private static void moveElements(int[] arr, int i) {
        for (int j = i; j < arr.length; j++) {
            if (j == arr.length - 1) {
                arr[arr.length - 1] = 0;
                break;
            }
            arr[j] = arr[j + 1];
        }
    }
}
