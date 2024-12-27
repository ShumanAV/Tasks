package org.example.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
Условие задачи: начальное положение коня x = 0, y = 0
координаты выхода x = -2, y = -2
необходимо посчитать мин количество ходов до выхода
 */

// Без проверки повторения прошлых ходов и без ограничения новых ходов в границах поля
public class ChessWithoutCheck {

    // Можно ради любопытства менять выход и границы поля, сравнивать время работы алгоритма, оно сильно отличается от
    // алгоритма без проверки прошлых ходов и без ограничения поля
    private final static XY EXIT = new XY(-2, -2, 0);

    public static void main(String[] args) {
        // запускаем метод BFS и замеряем время его работы
        long start = System.currentTimeMillis();
        bfs();
        System.out.println("Время работы алгоритма: " + (System.currentTimeMillis() - start));
    }

    private static void bfs() {
        // создаем очередь планируемых ходов
        Queue<XY> plannedMoves = new LinkedList<>();
        plannedMoves.add(new XY(0, 0,0));

        // создаем счетчик операций для информативности
        int countOperations = 0;

        // проходимся в цикле по планируемой очереди ходов пока в ней есть записи
        Iterator<XY> iterator = plannedMoves.iterator();
        while (iterator.hasNext()) {
            // инкремент счетчика операций
            countOperations++;
            // достаем из очереди первый элемент, и удаляем его из очереди
            XY currentXY = plannedMoves.poll();
            // проверяем координаты текущего хода на равенство с координатами выхода, если равны выводим уровень координат в
            // консоль и останавливаем цикл
            if (EXIT.equals(currentXY)) {
                System.out.println(currentXY.getLevel());
                System.out.println("Количество операций:" + countOperations);
                break;
            }
            // копируем новые ходы в очередь планируемых ходов
            plannedMoves.addAll(moves(currentXY.getX(), currentXY.getY(), currentXY.getLevel() + 1));
        }
    }

    // Метод возвращает новые координаты бех учета проверки границ поля
    private static Queue<XY> moves(int x, int y, int level) {
        return new LinkedList<>(Arrays.asList(new XY[]{
                new XY(x - 1, y - 2, level), new XY(x - 2, y - 1, level),
                new XY(x - 2, y + 1, level), new XY(x - 1, y + 2, level),
                new XY(x + 1, y + 2, level), new XY(x + 2, y + 1, level),
                new XY(x + 2, y - 1, level), new XY(x + 1, y - 2, level)}));
    }

    static class XY {
        private int x, y, level;

        public XY(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
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

    }

}
