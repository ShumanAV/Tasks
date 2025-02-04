package org.example.sort.quickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.GeneralMethods.feelArray;

public class App {
    public static void main(String[] args) {
        int[] arr = feelArray(1000_000);

        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(quickSort(arr)));
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения :" + (end - start));

    }

    // Сложность O(n log(n))
    private static int[] quickSort(int[] arr) {
        // Выход из рекурсии:
        // первый - когда массив у нас пустой, это происходит тогда, когда слева от опорного элемента или
        // справа от опорного элемента нет элементов, тогда возвращаем сам пустой массив,
        // и второй - когда длинна массива равна 1, это значит, что массив с данной длинной уже отсортирован,
        // его и возвращаем
        if (arr.length <= 1) {
            return arr;
        }

        // Находим опорный элемент массива, по умолчанию берем всегда последний
        int pivot = arr[arr.length - 1];

        // Создаем левую часть элементов массива, те элементы, которые меньше или равны чем опорный элемент и
        // правую часть элементов массива, те, которые больше чем опорный элемент,
        // для удобства добавления элементов создаем их в виде листов
        List<Integer> leftPart = new ArrayList<>();
        List<Integer> rightPart = new ArrayList<>();

        // Проходим в цикле по текущему массиву, и каждый рекурсивный вызов данного метода, распределяем элементы
        // массива по левой и правой части
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > pivot) {
                rightPart.add(arr[i]);
            } else {
                leftPart.add(arr[i]);
            }
        }

        // Возвращаем объединение всех частей массива в один массив: левая часть + опорный элемент + правая часть,
        // идет преобразование листов в массивы такого же типа int
        return combiningArrays(quickSort(leftPart.stream().mapToInt(i -> i).toArray()), pivot,
                quickSort(rightPart.stream().mapToInt(i -> i).toArray()));
    }

    // Метод объединяет все части массива в один массив: левая часть + опорный элемент + правая часть,
    // в случае когда левая или правая часть без элементов, т.е. их длина равна 0, то и никаких элементов не добавляется
    // при копировании массивов
    private static int[] combiningArrays(int[] leftPart, int pivot, int[] rightPart) {
        // создаем результирующий массив, в который будем копировать все части, длинна его равна сумме длин левой части
        // массива, правой части массива и плюс 1 для опорного элемента
        int[] result = new int[leftPart.length + 1 + rightPart.length];

        // сначала копируем левую часть массива в результирующий
        System.arraycopy(leftPart, 0, result, 0, leftPart.length);

        // добавляем опорный элемент в результирующий массив, позиция равна длине левой части
        result[leftPart.length] = pivot;

        // копируем правую часть массива в результирующий массив, вставляем результирующий массив начиная со следующего
        // от опорного элемента это соответствует длине левой части + 1
        System.arraycopy(rightPart, 0, result, leftPart.length + 1, rightPart.length);

        // возвращаем результирующий массив
        return result;
    }
}
