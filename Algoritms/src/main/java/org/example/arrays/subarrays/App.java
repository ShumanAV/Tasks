package org.example.arrays.subarrays;

/*
Задача: дается массив и число, необходимо найти и вернуть количество всех подмассивов в этом массиве, сумма которых
равна числу
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 1, 2, -3, 5, -8};
        int k = 5;
        System.out.println("Число подмассивов: " + returnAllSubArraysClassical(arr, k));
        System.out.println("Число подмассивов: " + returnAllSubArraysHashSet(arr, k));
        System.out.println("Число подмассивов: " + returnAllSubArraysHashMap(arr, k));
    }

    private static int returnAllSubArraysHashMap(int[] arr, int k) {
        int answer = 0, sum = 0, countSum = 1;
        Map<Integer, Integer> set = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            set.put(sum, countSum);   // 4, 6, 8, 9, 11, 8, 13, 5
            Integer contains = set.get(sum - k);
            if (contains != null) {
                answer++;
            }
            if (arr[i] == k) {
                answer++;
            }
            if (sum == k) {
                answer++;
            }
        }
        return answer;
    }

    private static int returnAllSubArraysHashSet(int[] arr, int k) {
        int answer = 0, sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            set.add(sum);   // 4, 6, 8, 9, 11, 8, 13, 5
            if (set.contains(sum - k)) {
                answer++;
            }
            if (arr[i] == k) {
                answer++;
            }
            if (sum == k) {
                answer++;
            }
        }
        return answer;
    }

    // Сложность O(n2)
    // Решение №1, в цикле проходимся по границам, делаем две границы левая и правая, левая меняется от начала массива
    // до конца, а правая не меняется, она всегда равна последнему элементу массива, вторым циклом суммируем все элементы
    // между этими границами и если сумма равна этой цифре увеличиваем ответ
    private static int returnAllSubArraysClassical(int[] arr, int k) {
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
