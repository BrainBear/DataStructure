package me.brainbear.sample;

import me.brainbear.array.Array;

public class Sample {

    private static void testArray() {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 20; i++) {
            arr.add(i);
            System.out.println(arr.toString());
        }

        for (int i = 0; i < 19; i++) {
            arr.remove(0);
            System.out.println(arr.toString());
        }

        arr.set(0, -1);
        System.out.println(arr.toString());

        arr.add(1, 1);
        System.out.println(arr.toString());

        System.out.println("arr[0]:" + arr.get(0));
        System.out.println("arr[size - 1]:" + arr.get(arr.getSize() - 1));

        System.out.println("index -1:" + arr.index(-1));
        System.out.println("index 1:" + arr.index(1));
        System.out.println("index 100:" + arr.index(100));
    }

    public static void main(String[] args) {

        testArray();
    }
}
