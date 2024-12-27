package org.example.mergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.GeneralMethods.feelArray;

public class App {
    public static void main(String[] args) {
        int[] arr = feelArray(1000_000);

        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(mergeSort(inList(arr), arr.length)));
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения :" + (end - start));
    }

    // Метод трансформирует массив в лист массивов по одному элементу, это нужно для упрощения реализации рекурсии
    private static List<int[]> inList(int[] arr) {
        List<int[]> listOfArrays = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            listOfArrays.add(new int[]{arr[i]});
        }
        return listOfArrays;
    }

    // Сложность О(n log(n))
    // Метод рекурсивный, сливает группы элементов и сортирует, в метод передаем лист массивов, изначально этот лист
    // массивов по одному элементу, второй параметр - это длина изначального массива, передается для того, чтобы выйти
    // из рекурсии когда, соберем элементы обратно в массив
    private static int[] mergeSort(List<int[]> src, int fullLength) {

        // Выход из рекурсии, т.к. собираем все элементы изначального массива в один массив, когда весь массив
        // будет собран выходим
        if (src.get(0).length == fullLength) {
            return src.get(0);
        }

        // создаем промежуточный лист массивов для слияния элементов или групп элементов
        List<int[]> result = new ArrayList<>();

        // циклом проходимся по массиву источнику, шаг инкремента выбран 2 потому, как объединяем с сортировкой сразу два
        // элемента листа
        for (int i = 0; i < src.size(); i += 2) {
            // проверяем если разница между текущим элементом и размером листа массивов источника больше или равно 2,
            // это значит, что, либо элементов или групп элементов осталось 2 или более,
            // если менее 2, это значит что остался один элемент или группа элементов
            if (src.size() - i >= 2) {
                // в этом случае отправляем в метод для слияния и сортировки промежуточный результирующий лист массивов
                // для записи в него отсортированных и объединенных элементов двух групп, также отправляем сами два элемента
                // или две группы элементов для сортировки и слияния, текущая и следующая
                sortElementsInGroups(result, src.get(i), src.get(i + 1), true);
            } else {
                // в этом случае, также отправляем промежуточный результирующий лист массивов и два элемента или две
                // группы элементов, где первая группа это последняя группа элементов уже отсортированная и объединенная
                // из результирующего листа, а вторая это последний элемент или группа элементов из листа источника, так
                // сделано потому, что остался последний элемент или группа элементов и ее сливать уже не с чем, поэтому
                // ее сливаем с уже отсортированной группой элементов
                sortElementsInGroups(result, result.get(result.size() - 1), src.get(i), false);
            }
        }
        return mergeSort(result, fullLength);
    }

    // Метод сливает элементы двух групп в одну группу, при сливании сортирует элементы, на вход подается промежуточный
    // результирующий лист массивов для записи в него уже отсортированных элементов, первая и вторая группы элементов,
    // и булевая переменная, которая необходима для определения случая, когда нужно добавить новую строку в
    // результирующий лист, или записывать в существующую последнюю строку в случае, когда сливаем уже отсортированную
    // группу элементов из результирующего листа и последнюю группу элементов
    private static void sortElementsInGroups(List<int[]> result, int[] group1, int[] group2, boolean addNewRow) {

        if (addNewRow) {
            // если нужно добавить новую строку, добавляем и создаем массив длинной - сумма длин массивов первой и
            // второй группы элементов
            result.add(new int[group1.length + group2.length]);
        } else {
            // если не нужно добавлять новую строку, в последней существующей строке создаем массив длиной - также
            // сумма длин массивов первой и второй группы элементов, данные в ней стираются, но они переданы в первой группе
            result.set(result.size() - 1, new int[group1.length + group2.length]);
        }

        // создаем переменную для задания начала для второго цикла, требуется это для того, чтобы, в случае когда
        // часть элементов второй группы уже отсортированы и записаны в результирующий лист, чтобы не начинать цикл
        // заново по второй группе каждый раз, когда элемент первой группы меньше чем второй, а продолжать с того
        // элемента на, котором остановились
        int start2 = 0;

        // первым циклом проходимся по первой группе, вторым циклом по второй группе элементов
        for (int i = 0; i < group1.length; i++) {
            for (int j = start2; j < group2.length; j++) {

                // если элемент первой группы меньше чем элемент второй
                if (group1[i] < group2[j]) {

                    // в этом случае записываем элемент первой группы в массив результирующего листа
                    result.get(result.size() - 1)[i + j] = group1[i];

                    // если дошли до конца элементов первой группы, то копируем оставшиеся элементы, если они есть,
                    // из второй группы в результирующий лист, т.к. уже сравнивать не с чем, элементы во второй группе
                    // уже отсортированы изначально и они точно больше, чем те, которые уже записаны в результирующий
                    // лист, так их сравнивали в процессе данных циклов, поэтому сортировать их не нужно, просто копируем
                    if (i == group1.length - 1) {
                        System.arraycopy(group2, j, result.get(result.size() - 1), i + j + 1, group2.length - j);
                    }

                    // задаем стартовое значение второго цикла, равное текущему элементу
                    start2 = j;

                    // останавливаем второй цикл, т.к. элемент первой группы меньше, чем элемент второй группы, и он
                    // записан в результирующий лист, нужно двигаться дальше по первому циклу
                    break;
                } else {
                    // в случае, если элемент второй группы меньше чем элемент первой группы, записываем его в массив
                    // результирующего списка
                    result.get(result.size() - 1)[i + j] = group2[j];

                    // если дошли до конца элементов второй группы, то копируем оставшиеся элементы, если они есть,
                    // из второй группы в результирующий лист, т.к. уже сравнивать не с чем, элементы в первой группе
                    // уже отсортированы изначально и они точно больше, чем те, которые уже записаны в результирующий
                    // лист, так их сравнивали в процессе данных циклов, поэтому сортировать их не нужно, просто копируем
                    if (j == group2.length - 1) {
                        System.arraycopy(group1, i, result.get(result.size() - 1), i + j + 1, group1.length - i);

                        // задаем текущее значение для первого цикла, равное длине массива первой группы, чтобы его остановить
                        i = group1.length;
                    }
                }
            }
        }
    }
}