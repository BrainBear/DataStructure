package me.brainbear.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void add(T t) {
        root = add(root, t);
    }


    private Node add(Node node, T t) {

        if (node == null) {
            size++;
            return new Node(t);
        }

        if (t.compareTo(node.data) < 0) {
            node.left = add(node.left, t);
        } else if (t.compareTo(node.data) > 0) {
            node.right = add(node.right, t);
        }

        //更新节点的高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        node = balance(node);

        return node;
    }


    private Node rightRotate(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;


        node.height = Math.max(getHeight(node.left), getHeight(node.right));
        leftChild.height = Math.max(getHeight(leftChild.left), getHeight(leftChild.right));

        return leftChild;
    }


    private Node leftRotate(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        node.height = Math.max(getHeight(node.left), getHeight(node.right));
        rightChild.height = Math.max(getHeight(rightChild.left), getHeight(rightChild.right));

        return rightChild;
    }

    /**
     * 对节点进行平衡操作
     */
    private Node balance(Node node) {
        if (null == node) {
            return null;
        }

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }


    public boolean contains(T t) {
        return contains(root, t);
    }

    public boolean contains(Node node, T t) {
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

    private void preOrder(Node node, List<T> list) {
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

    private void inOrder(Node node, List<T> list) {
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

    private void postOrder(Node node, List<T> list) {
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
        LinkedList<Node> list = new LinkedList<>();

        list.add(root);
        while (!list.isEmpty()) {
            Node node = list.remove();

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

    private Node findMax(Node node) {
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

    public Node findMin(Node node) {
        if (null == node.left) {
            return node;
        }
        return findMin(node.left);
    }


    public T removeMin() {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }

        Node min = findMin(root);
        root = removeMin(root);
        return min.data;
    }

    public Node removeMin(Node node) {

        if (null == node.left) {
            Node rightNode = node.right;
            size--;
            node.right = null;

            return rightNode;
        }

        node.left = removeMin(node.left);

        node = balance(node);
        return node;
    }


    public T removeMax() {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }
        Node max = findMax(root);
        root = removeMax(root);
        return max.data;
    }


    private Node removeMax(Node node) {
        if (null == node.right) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);

        node = balance(node);
        return node;
    }


    public void remove(T t) {
        if (size == 0) {
            throw new IllegalArgumentException("empty tree");
        }

        remove(root, t);
    }

    private Node remove(Node node, T t) {

        if (null == node) {
            return null;
        }

        Node retNode = null;

        if (t.compareTo(node.data) < 0) {
            retNode = remove(node.left, t);
        } else if (t.compareTo(node.data) > 0) {
            retNode = remove(node.right, t);
        } else {
            if (null == node.left) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (null == node.right) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {

                Node min = findMin(node.right);
                min.left = node.left;
                min.right = removeMin(node.right);

                node.left = null;
                node.right = null;
                retNode = min;
            }
        }

        retNode = balance(retNode);

        return retNode;
    }


    /**
     * 计算节点的平衡因子
     */
    private int getBalanceFactor(Node node) {
        return null == node ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 计算节点的高度，null 节点的高度为 0
     */
    private int getHeight(Node node) {
        return null == node ? 0 : node.height;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    private class Node {
        T data;
        Node left;
        Node right;
        int height;

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
            height = 0;
        }
    }


    @Override
    public String toString() {
        return inOrder().toString();
    }
}
