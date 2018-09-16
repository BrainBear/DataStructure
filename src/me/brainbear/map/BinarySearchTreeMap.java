package me.brainbear.map;

public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node root;
    private int size;

    public BinarySearchTreeMap() {
        root = null;
        size = 0;
    }


    private Node put(Node node, K key, V value) {
        if (null == node) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }
        return node;
    }

    @Override
    public void put(K key, V value) {
        put(root, key, value);
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (null == node) {
            return null;
        }

        root = remove(root, key);
        return node.value;
    }


    private Node getNode(Node node, K key) {
        if (null == node) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        }

        return node;
    }


    private Node findMax(Node node) {
        if (null == node.right) {
            return node;
        }

        return findMax(node.right);
    }


    private Node removeMax(Node node) {
        if (null == node.right) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    private Node remove(Node node, K key) {
        if (null == node) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return remove(node.right, key);
        } else {
            if (null == node.left) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            } else if (null == node.right) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            } else {
                Node leftMax = findMax(node.left);
                leftMax.left = removeMax(node.left);
                leftMax.right = node.right;

                node.left = null;
                node.right = null;

                return leftMax;
            }
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (null == node) {
            throw new IllegalArgumentException("key " + key + " doesn't exist");
        }

        node.value = value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private class Node {
        K key;
        V value;
        Node left;
        Node right;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }


    }
}
