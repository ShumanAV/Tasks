package org.example.arrays.temperaturePlus;

import java.util.Arrays;
import java.util.Stack;

/*
Задача: каждое число - это температура дня, найти для каждого дня когда будет повышение температуры по отношению к
каждому дню, для последнего дня можно ставить 0, т.к. нет дольше информации
 */

public class App {
    public static void main(String[] args) {
        int[] arr = {13, 12, 15, 11, 9, 12, 16};
        System.out.println(Arrays.toString(searchWarmDays(arr)));
        System.out.println(Arrays.toString(searchWarmDaysStack(arr)));
    }

    // Сложность в самом худшем варианте O(n2), в лучшем O(n), в среднем O(n log(n)), Память в худшем варианте O(n),
    // в лучшем O(n2)
    // Решение, по массиву идем с конца, т.к. для температуры для каждого чтобы понять когда будет потепление интересны
    // дни, которые справа, создаем стек, в стек записываем температуру дней, при сравнении с текущим днем, все более
    // меньшие дни удаляем из стека, таким образом уменьшая количество элементов в стеке и количество операций сравнения
    private static int[] searchWarmDaysStack(int[] arr) {
        int[] answer = new int[arr.length];
        answer[answer.length - 1] = 0;

        Stack<Day> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.empty() && stack.peek().value <= arr[i]) {
                stack.pop();
            }
            if (!stack.empty()) {
                answer[i] = stack.peek().index - i;
            }
            stack.push(new Day(arr[i], i));
        }
        return answer;
    }

    // Создаем класс для хранения двух переменных значение и индекс в массиве
    static class Day{
        int value, index;

        public Day(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    // Сложность O(n2), память O(n) - массив ответов + две переменные
    // Решение, в двух циклах проходимся по массиву, и сравниваем каждое число с последующими, когда попадется число
    // большее чем текущее
    private static int[] searchWarmDays(int[] arr) {
        int[] answer = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }
}
