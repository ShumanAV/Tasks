package org.example.sort.bubbleSort;

import java.util.Arrays;

import static org.example.GeneralMethods.feelArray;

public class App {
    public static void main(String[] args) {
        int[] arr = feelArray(1000_000);

        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(bubbleSort(arr)));
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения :" + (end - start));

//        start = System.currentTimeMillis();
//        System.out.println(Arrays.toString(bubbleSortRecursion(arr, 0)));
//        end = System.currentTimeMillis();
//        System.out.println("Время выполнения :" + (end - start));
    }

    private static int[] bubbleSortRecursion(int[] arr, int numOfCycles) {
        // выход из рекурсии, когда количество сделанных рекурсивных циклов равно длина массива - 1, это значит, что
        // были определены максимумы и уведены вправо массива, кол-во таких максимумов как раз и составляет длина
        // массива - 1, т.е. весь массив отсортирован
        if (numOfCycles == arr.length - 1) {
            return arr;
        }

        //каждый раз уменьшаем максимум второго цикла на 1, за счет увеличения numOfCycles, -1 сделана для возможности
        // проверки предпоследнего элемента с последним, если идти до последнего то получим ArrayIndexOutOfBoundsException
        int maxCount = arr.length - numOfCycles - 1;
        //каждый цикл начинаем с самого начала
        for (int n = 0; n < maxCount; n++) {
            if (arr[n] > arr[n + 1]) {
                int buffer = arr[n];
                arr[n] = arr[n + 1];
                arr[n + 1] = buffer;
            }
        }
        return bubbleSortRecursion(arr, numOfCycles + 1);
    }

    //сложность O(n2)
    private static int[] bubbleSort(int[] arr) {

        //За один цикл мы получаем самое большое число справа в массиве,
        // цикл в цикле нужен для того, чтобы n раз получить максимум из оставшегося массива,
        // сначала проверяем весь массив, далее на 1 меньше, т.к. после первого прохода уже есть самый максимум,
        for (int i = 0; i < arr.length; i++) {

            //каждый раз уменьшаем максимум второго цикла на 1, за счет увеличения i, -1 сделана для проверки
            // предпоследнего элемента с последним, если идти до последнего то получим ArrayIndexOutOfBoundsException
            int maxCount = arr.length - i - 1;
            //каждый цикл начинаем с самого начала
            for (int n = 0; n < maxCount; n++) {
                if (arr[n] > arr[n + 1]) {
                    int buffer = arr[n];
                    arr[n] = arr[n + 1];
                    arr[n + 1] = buffer;
                }
            }
        }
        return arr;
    }
}
