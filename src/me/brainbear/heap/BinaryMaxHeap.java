package me.brainbear.heap;

import java.util.ArrayList;
import java.util.List;

public class BinaryMaxHeap<T extends Comparable<T>> {


    private List<T> data;

    public BinaryMaxHeap() {
        data = new ArrayList<T>();
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    /**
     * 返回给定索引的父节点索引
     */
    private int parentIndex(int index) {
        if (0 == index) {
            throw new IllegalArgumentException("index 0 doesn't have parent");
        }

        return (index - 1) / 2;
    }


    /**
     * 返回给定索引的左边子节点的索引
     */
    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }


    /**
     * 返回给定索引的右边子节点的索引
     */
    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }


    public void add(T t) {
        data.add(t);
        siftUp(data.size() - 1);
    }


    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parentIndex(index))) > 0) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }


    private void swap(int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    public T extractMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("empty heap");
        }

        T ret = data.get(0);

        T tail = data.remove(data.size() - 1);
        if(!data.isEmpty()) {
            data.set(0, tail);
        }
        siftDown(0);

        return ret;
    }

    private void siftDown(int index) {

        if (leftChildIndex(index) >= data.size()) {
            return;
        }

        if (rightChildIndex(index) < data.size()) {
            int maxIndex = data.get(rightChildIndex(index)).compareTo(data.get(leftChildIndex(index))) > 0 ? rightChildIndex(index) : leftChildIndex(index);

            if (data.get(index).compareTo(data.get(maxIndex)) < 0) {
                swap(index, maxIndex);
                siftDown(maxIndex);
            }
        }
    }


    @Override
    public String toString() {
        return data.toString();
    }
}
