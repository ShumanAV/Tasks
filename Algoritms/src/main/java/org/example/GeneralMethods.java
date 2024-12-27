package org.example;

import java.util.Random;

public class GeneralMethods {
    public static int[] feelArray(int maxArray) {
        int[] arr = new int[maxArray];
        Random random = new Random();
        for (int i = 0; i < maxArray; i++) {
            arr[i] = random.nextInt(maxArray);
        }
        return arr;
    }
}
