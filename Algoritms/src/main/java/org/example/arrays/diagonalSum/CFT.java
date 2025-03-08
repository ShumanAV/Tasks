package org.example.arrays.diagonalSum;

import java.util.HashSet;
import java.util.List;

public class CFT {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        list.stream()
                .filter(i -> {
                    System.out.println("filter " + i);
                    return i % 2 == 0;
                })
                .forEach(i -> System.out.println("foreach " + i));

        MyObject myObject = new MyObject(8);

        HashSet<MyObject> set = new HashSet<>();
        set.add(myObject);
//        myObject.setI(8008);

        System.out.println(set.contains(myObject));
        System.out.println(set.contains(new MyObject(8)));
        System.out.println(set.contains(new MyObject(8008)));
    }

    static class MyObject {
        int i;

        public MyObject(int i) {
            this.i = i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            MyObject myObject = (MyObject) object;

            return i == myObject.i;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }
}
