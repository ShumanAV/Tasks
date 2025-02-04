package org.example.arrays.diagonalSum;

/*
Задача - найти сумму элементов по диагонали
 */

public class App {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3},
                       {4, 5, 6},
                       {7, 8, 9}};
        System.out.println(findSumOfDiagonal(arr));
    }

    /*
    Сложность O(n)
     */
    private static int findSumOfDiagonal(int[][] arr) {
        int sumOne = 0;
        int sumTwo = 0;
        for (int i = 0; i < arr.length; i++) {
            sumOne += arr[i][i];
            sumTwo += arr[i][arr.length - 1 - i];
        }
        System.out.println(sumTwo);
        return sumOne;
    }
}
