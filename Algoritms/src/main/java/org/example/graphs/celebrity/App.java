package org.example.graphs.celebrity;

/*
Задача: есть группа людей, нужно найти среди них знаменитость, если она есть, знаменитость - это значит, что его должны
все знать из этой группы, но при этом сам он должен не знать никого, все это нужно сделать использую минимальное количество
вопросов, знаменитость в группе может быть только одна, т.к. если было две, значит они не были бы знаменитостями,
т.к. никого бы не знали, а знаменитость должны знать все
 */

public class App {
    public static void main(String[] args) {
        Person[] arr = {new Person(), new Person()};
        findToCelebrity(arr);
    }

    // Сложность O (n) + O(2n) = O(n)
    private static Person findToCelebrity(Person[] arr) {
        int left = 0, right = arr.length - 1;
        while (left != right) {
            if (arr[left].knows(arr[right])) {
                left++;
            } else {
                right--;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (left != i && arr[left].knows(arr[i]) || !arr[i].knows(arr[left])) {
                return null;
            }
        }
        return arr[left];
    }

    static class Person {
        public boolean knows(Person person) {
            return true;
        }
    }
}
