package org.example.graphs.robot;

/*
Задача: рассчитать сколько потребуется ходов роботу для выхода, если робот может ходить только вверх или направо
 */

import java.util.*;

public class App {

    private final static XY EXIT = new XY(4, 3);
    private final static XY START = new XY(1, 1);

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println("Количество ходов: " + searchStepMy());
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println("Количество ходов: " + searchStepFromEnd(EXIT.x, EXIT.y));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println("Количество ходов: " + searchStepFromStart(START.x, START.y));
        System.out.println(System.currentTimeMillis() - start);

    }

    // Сложность O(n2)
    // Решение, методом поиска в ширину BFS для графов, ищем все возможные ходы, копируем новые ходы в очередь,
    // каждый раз в цикле достаем из очереди первый элемент, находим у него следующие шаги, и т.д.
    private static int searchStepMy() {
        // Создаем очередь планируемые шаги и добавляем в нее первый элемент точка старта
        Queue<XY> plannedSteps = new LinkedList<>();
        plannedSteps.add(START);

        // создаем мапу, в которой для каждой ячейки будем хранить координаты и новые ходы в виде списка, это позволит
        // для дубликатов не искать новые ходы, а брать из мапы
        Map<XY, List<XY>> memory = new HashMap<>(EXIT.x * EXIT.y);

        // Переменная количество путей, по умолчанию есть как минимум один путь, поэтому зададим сразу значение равное 1
        int numOfPaths = 1;

        // создаем итератор, т.к. будем в процессе цикла менять очередь и пока в очереди есть элементы идем по ней
        Iterator<XY> iterator = plannedSteps.iterator();
        while (iterator.hasNext()) {

            // берем из очереди очередной шаг
            XY currentStep = plannedSteps.poll();

            // измеряем текущий размер планируемых шагов, для измерения количества добавленных новых шагов
            int wereSize = plannedSteps.size();

            // если в мапе не содержится текущего шага, добавляем в мапу
            if (!memory.containsKey(currentStep)) {
                memory.put(currentStep, new ArrayList<>(newMoves(currentStep.x, currentStep.y)));
            }
            // копируем из мапы новые шаги из текущих координат
            plannedSteps.addAll(memory.get(currentStep));

            // создаем булеву переменную для удобства - создание нового пути, в случае если из текущих координат
            // количество новых шагов с координатами добавлено два, т.е. больше одного - один шаг вверх, другой вправо,
            // то это значит, что в этой точке добавляется еще один новый путь, происходит разъединение, поэтому
            // увеличиваем количество новых путей на 1
            boolean creatingNewPath = plannedSteps.size() - wereSize > 1;
            if (creatingNewPath) {
                numOfPaths += 1;
            }
        }
        // возвращаем количество новых путей
        return numOfPaths;
    }

    // Метод новые ходы для каждого текущего хода с координатами создает новые ходы с учетом ограничений поля
    private static Queue<XY> newMoves(int x, int y) {
        Queue<XY> newSteps = new LinkedList<>();
        if (x < EXIT.x) {
            newSteps.add(new XY(x + 1, y));
        }
        if (y < EXIT.y) {
            newSteps.add(new XY(x, y + 1));
        }
        return newSteps;
    }

    // Класс координаты
    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            XY xy = (XY) object;

            if (x != xy.x) return false;
            return y == xy.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    // Сложность O(n2)
    private static int searchStepFromEnd(int x, int y) {
        return memory(x, y, new int[x + 1][y + 1]);
    }

    static int memory(int x, int y, int[][] arr) {
        if (x < START.x || y < START.y) {
            return 0;
        }
        if (x == START.x && y == START.y) {
            return 1;
        }
        if (arr[x][y] != 0) {
            return arr[x][y];
        }
        arr[x][y] = memory(x - 1, y, arr) + memory(x, y - 1, arr);
        return arr[x][y];
    }

    ;

    // Сложность (2 в степени (x+y))
    private static int searchStepFromStart(int x, int y) {
        if (x > EXIT.x || y > EXIT.x) {
            return 0;
        }
        if (x == EXIT.x && y == EXIT.y) {
            return 1;
        }
        return searchStepFromStart(x + 1, y) + searchStepFromStart(x, y + 1);
    }
}
