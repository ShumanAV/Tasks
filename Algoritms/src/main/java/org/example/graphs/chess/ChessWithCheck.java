package org.example.graphs.chess;

import java.util.*;
/*
Условие задачи:
начальное положение коня x = 0, y = 0
координаты выхода x = -2, y = -2
необходимо посчитать мин количество ходов до выхода
 */

// С проверкой повторения прошлых ходов и с ограничением новых ходов в границах поля
public class ChessWithCheck {

    // Можно ради любопытства менять выход и границы поля, сравнивать время работы алгоритма, оно сильно отличается от
    // алгоритма без проверки прошлых ходов и без ограничения поля
    private final static XY EXIT = new XY(-2, -2, 0);
    private final static int X_MIN = -2;
    private final static int X_MAX = 2;
    private final static int Y_MIN = -2;
    private final static int Y_MAX = 2;

    public static void main(String[] args) {
        // запускаем метод BFS и замеряем время его работы
        long start = System.currentTimeMillis();
        bfs();
        System.out.println("Время работы алгоритма: " + (System.currentTimeMillis() - start));
    }

    private static void bfs() {
        // создаем список уже проверенных ходов
        List<XY> previousMoves = new ArrayList<>();

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
            // записываем его в уже проверенные ходы
            previousMoves.add(currentXY);
            // проверяем координаты текущего хода на равенство с координатами выхода, если равны выводим уровень координат в
            // консоль и останавливаем цикл
            if (EXIT.equals(currentXY)) {
                System.out.println("Уровень слоя: " + currentXY.getLevel());
                System.out.println("Количество операций:" + countOperations);
                break;
            }
            // копируем новые ходы в очередь планируемых ходов
            copy(moves(currentXY.getX(), currentXY.getY(), currentXY.getLevel() + 1), plannedMoves, previousMoves);
        }
    }

    // Метод копирования ходов, проверяет на дублирование уже проверенных ходов и планируемых
    private static void copy(Queue<XY> newMoves, Queue<XY> plannedMoves, List<XY> previousMoves) {
        for (XY xy: newMoves) {
            if (!previousMoves.contains(xy) && !plannedMoves.contains(xy)) {
                plannedMoves.add(xy);
            }
        }
    }

    // Метод возвращает новые координаты, с учетом проверки границ поля
    private static Queue<XY> moves(int x, int y, int level) {
        Queue<XY> newMoves = new LinkedList<>();

        for (int deltaX = -2; deltaX <= 2; deltaX++) {
            if (deltaX == 0) {
                continue;
            }
            for (int deltaY = -2; deltaY <= 2; deltaY++) {
                if (deltaY == 0 || Math.abs(deltaX) == Math.abs(deltaY)) {
                    continue;
                }
                if (x + deltaX >= X_MIN && x + deltaX <= X_MAX &&
                        y + deltaY >= Y_MIN && y + deltaY <= Y_MAX) {
                    newMoves.add(new XY(x + deltaX, y + deltaY, level));
                }
            }
        }
        return newMoves;
    }

    // Простой класс, координат, где есть сами координаты x и y, и уровень координат level
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
