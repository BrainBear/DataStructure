package me.brainbear.sample;

import me.brainbear.array.Array;
import me.brainbear.linked.LinkedList;

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

    private static void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0; i < 5; i++){
            list.addHead(i);
            System.out.println(list.toString());
        }

        for(int i = 5; i < 10; i++){
            list.addTail(i);
            System.out.println(list.toString());
        }

        for(int i = 10; i < 15; i++){
            list.add(5, i);
            System.out.println(list.toString());
        }

        System.out.println(list.index(0));
        System.out.println(list.index(5));
        System.out.println(list.index(15));

        for(int i = 0; i < 5; i++){
            list.remove(5);
            System.out.println(list.toString());
        }

        for(int i = 0; i < 5; i++){
            list.removeHead();
            System.out.println(list.toString());
        }

        for(int i = 5; i < 10; i++){
            list.removeTail();
            System.out.println(list.toString());
        }
    }

    public static void main(String[] args) {

//        testArray();
        testLinkedList();
    }
}
