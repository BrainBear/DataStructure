package me.brainbear.set;

import me.brainbear.tree.BinarySearchTree;

import java.util.List;

public class BinarySearchTreeSet<T extends Comparable<T>> implements Set<T> {

    private final BinarySearchTree<T> tree;

    public BinarySearchTreeSet() {
        tree = new BinarySearchTree<>();
    }

    @Override
    public void add(T t) {
        tree.add(t);
    }

    @Override
    public void remove(T t) {
        tree.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return tree.contains(t);
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size: ");
        sb.append(getSize());
        sb.append(' ');
        sb.append('[');

        List<T> list = tree.preOrder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));

            if (i != list.size() - 1) {
                sb.append(',');
            }
        }
        sb.append(']');

        return sb.toString();
    }
}
