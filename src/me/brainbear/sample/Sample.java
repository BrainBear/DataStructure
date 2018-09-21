package me.brainbear.sample;

import me.brainbear.array.Array;
import me.brainbear.heap.BinaryMaxHeap;
import me.brainbear.linked.LinkedList;
import me.brainbear.queue.LinkedListQueue;
import me.brainbear.set.BinarySearchTreeSet;
import me.brainbear.stack.LinkedListStack;
import me.brainbear.tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        for (int i = 0; i < 5; i++) {
            list.addHead(i);
            System.out.println(list.toString());
        }

        for (int i = 5; i < 10; i++) {
            list.addTail(i);
            System.out.println(list.toString());
        }

        for (int i = 10; i < 15; i++) {
            list.add(5, i);
            System.out.println(list.toString());
        }


        for (int i = 0; i < list.getSize(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        System.out.println(list.index(0));
        System.out.println(list.index(5));
        System.out.println(list.index(15));

        for (int i = 0; i < 5; i++) {
            list.remove(5);
            System.out.println(list.toString());
        }

        for (int i = 0; i < 5; i++) {
            list.removeHead();
            System.out.println(list.toString());
        }

        for (int i = 5; i < 10; i++) {
            list.removeTail();
            System.out.println(list.toString());
        }
    }


    private static void testLinkedLinkStack() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for (int i = 1; i < 30; i++) {
            if (i % 3 == 0) {
                Integer pop = stack.pop();
                System.out.println("pop: " + pop);
            } else {
                stack.push(i);
                System.out.println("push: " + i);
            }

            System.out.println(stack.toString());
        }
    }

    private static void testLinkedListQueue() {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();

        for (int i = 1; i < 30; i++) {
            if (i % 3 == 0) {
                Integer pop = linkedListQueue.dequeue();
                System.out.println("dequeue: " + pop);
            } else {
                linkedListQueue.enqueue(i);
                System.out.println("enqueue: " + i);
            }

            System.out.println(linkedListQueue.toString());
        }
    }

    private static void testBinarySearchTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        int[] nums = new int[]{5, 3, 6, 8, 4, 2};
        for (int i : nums) {
            tree.add(i);
        }
        List<Integer> list = tree.preOrder();
        System.out.println(list);

        list = tree.inOrder();
        System.out.println(list);

        list = tree.postOrder();
        System.out.println(list);

        list = tree.levelOrder();
        System.out.println(list);


        Random random = new Random();
        list = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }

        BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
        for (int i : list) {
            tree1.add(i);
        }

        while (!tree1.isEmpty()) {
            System.out.print(tree1.removeMin() + " ");
        }

        System.out.println();

        for (int i : list) {
            tree1.add(i);
        }

        while (!tree1.isEmpty()) {
            System.out.print(tree1.removeMax() + " ");
        }
    }


    private static void testSet() {
        BinarySearchTreeSet<Integer> set = new BinarySearchTreeSet<>();

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int item = random.nextInt(10);
            System.out.println("add item:" + item);
            set.add(item);
            System.out.println(set.toString());
        }
    }


    private static void testHeap() {
        BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<>();

        Random random = new Random();

        int count = 20;
        for(int i = 0; i < count; i++){
            maxHeap.add(random.nextInt(20));
            System.out.println(maxHeap.toString());
        }

        while (!maxHeap.isEmpty()) {
            Integer max = maxHeap.extractMax();
            System.out.println("extract max:" + max + " " + maxHeap.toString());
        }
    }


    public static void main(String[] args) {

//        testArray();
//        testLinkedList();
//        testLinkedLinkStack();
//        testLinkedListQueue();
//        testBinarySearchTree();
//        testSet();
        testHeap();
    }
}
