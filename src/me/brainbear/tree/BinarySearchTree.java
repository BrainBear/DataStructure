package me.brainbear.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void add(T t) {
        root = add(root, t);
    }


    private Node<T> add(Node<T> node, T t) {

        if (node == null) {
            size++;
            return new Node<>(t);
        }

        if (t.compareTo(node.data) < 0) {
            node.left = add(node.left, t);
        } else if (t.compareTo(node.data) > 0) {
            node.right = add(node.right, t);
        }

        return node;
    }


    public boolean contains(T t) {
        return contains(root, t);
    }

    public boolean contains(Node<T> node, T t) {
        if (null == node) {
            return false;
        }

        if (t.compareTo(node.data) < 0) {
            return contains(node.left, t);
        } else if (t.compareTo(node.data) > 0) {
            return contains(node.right, t);
        } else {
            return true;
        }

    }


    public List<T> preOrder() {
        List<T> list = new ArrayList<>(size);
        preOrder(root, list);

        return list;
    }

    private void preOrder(Node<T> node, List<T> list) {
        if (null == node) {
            return;
        }

        list.add(node.data);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    public List<T> inOrder() {
        List<T> list = new ArrayList<>(size);
        inOrder(root, list);
        return list;
    }

    private void inOrder(Node<T> node, List<T> list) {
        if (null == node) {
            return;
        }

        inOrder(node.left, list);
        list.add(node.data);
        inOrder(node.right, list);
    }


    public List<T> postOrder() {
        List<T> list = new ArrayList<>(size);
        postOrder(root, list);
        return list;
    }

    private void postOrder(Node<T> node, List<T> list) {
        if (null == node) {
            return;
        }

        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.data);
    }


    public List<T> levelOrder() {

        List<T> output = new ArrayList<>(size);

        if (null == root) {
            return output;
        }
        LinkedList<Node<T>> list = new LinkedList<>();

        list.add(root);
        while (!list.isEmpty()) {
            Node<T> node = list.remove();

            output.add(node.data);

            if (node.left != null) {
                list.add(node.left);
            }

            if (node.right != null) {
                list.add(node.right);
            }
        }

        return output;
    }


    public T findMax() {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }
        return findMax(root).data;
    }

    private Node<T> findMax(Node<T> node) {
        if (null == node.right) {
            return node;
        }

        return findMax(node.right);
    }


    public T findMin() {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }
        return findMin(root).data;
    }

    public Node<T> findMin(Node<T> node) {
        if (null == node.left) {
            return node;
        }
        return findMin(node.left);
    }


    public T removeMin() {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }

        Node<T> min = findMin(root);
        root = removeMin(root);
        return min.data;
    }

    public Node<T> removeMin(Node<T> node) {

        if (null == node.left) {
            Node<T> rightNode = node.right;
            size--;
            node.right = null;

            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    public T removeMax() {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }
        Node<T> max = findMax(root);
        root = removeMax(root);
        return max.data;
    }


    private Node<T> removeMax(Node<T> node) {
        if (null == node.right) {
            Node<T> leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }


    private void remove(T t) {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }

        remove(root, t);
    }

    private Node<T> remove(Node<T> node, T t) {

        if (null == node) {
            return null;
        }

        if (t.compareTo(node.data) < 0) {
            return remove(node.left, t);
        } else if (t.compareTo(node.data) > 0) {
            return remove(node.right, t);
        } else {
            if (null == node.left) {
                Node<T> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (null == node.right) {
                Node<T> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {

                Node<T> min = findMin(node.right);
                min.left = node.left;
                min.right = removeMin(node.right);

                node.left = null;
                node.right = null;
                return min;
            }
        }
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    private class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

}
