package org.example.arrays.sumInRow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Задача:
 есть массив и цифра, нужно найти и вернуть элементы, сумма которых равна этой цифре, если таких комбинаций несколько
 вернуть любую из них, если такой комбинации нет вернуть пустой массив
 */

public class App {
    public static void main(String[] args) {
        int[] arr = {-4, 0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int k = 30;
        System.out.println(Arrays.toString(sumInRowClassical(arr, k)));
        System.out.println(Arrays.toString(sumInRowHash(arr, k)));
        System.out.println(Arrays.toString(sumInRowBinarySearch(arr, k)));
        System.out.println(Arrays.toString(sumInRowTwoPointerTechnique(arr, k)));
    }

    // Сложность О (n)
    // Решение методом двух указателей, массив должен быть отсортирован, помещаем два указателя, один на первый элемент
    // массива, другой на последний, в цикле проверяем, если сумма элементов меньше необходимой, значит сдвигаем левый
    // указатель вправо, если больше, значит сдвигаем правый указатель влево, и так делаем пока указатели не будут равны
    private static int[] sumInRowTwoPointerTechnique(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left != right) {
            if (arr[left] + arr[right] == k) {
                return new int[]{arr[left], arr[right]};
            } else if (arr[left] + arr[right] < k) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    // Сложность O(n log(n))
    // Решение №3 - с помощью бинарного поиска, массив должен быть отсортирован, по массиву проходимся один раз,
    // и недостающее число ищем бинарным поиском в оставшейся части массива
    private static int[] sumInRowBinarySearch(int[] arr, int k) {
        int pos = 0;
        for (int i = 0; i < arr.length; i++) {
            int numToFind = k - arr[i];
            if (numToFind > arr[arr.length - 1] || numToFind < arr[i + 1]) {
                continue;
            }
            pos = i + (arr.length - i) / 2;
            while (true) {
                if (numToFind == arr[pos]) {
                    return new int[]{arr[i], arr[pos]};
                } else if (numToFind < arr[pos]) {
                    pos += (pos - i) / 2;
                } else {
                    pos += (arr.length - pos) / 2;
                }
            }
        }
        return new int[0];
    }

    // Сложность O(n)
    // Решение №2 - при помощи множества, проходимся по массиву один раз, помещая все числа в множество, в множестве
    // ищем недостающее число
    private static int[] sumInRowHash(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(k - num)) {
                return new int[]{k - num, num};
            } else {
                set.add(num);
            }
        }
        return new int[0];
    }

    // Сложность O(n2)
    // Решение №1 - последовательный поиск недостающего числа в массиве, суммируем каждое число со всеми другими
    private static int[] sumInRowClassical(int[] arr, int k) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == k) {
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return new int[0];
    }
}
