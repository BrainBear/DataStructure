package me.brainbear.map;

public class LinkedListMap<K, V> implements Map<K, V> {

    private Node<K, V> dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node<>(null, null);
        size = 0;
    }


    private Node<K, V> getNode(K key) {
        Node<K, V> p = dummyHead.next;
        while (null != p) {
            if (p.key.equals(key)) {
                return p;
            }
            p = p.next;
        }

        return null;
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = getNode(key);

        if (null != node) {
            node.value = value;
        } else {
            Node<K, V> newNode = new Node<>(key, value, dummyHead.next);
            dummyHead.next = newNode;
            size++;
        }
    }

    @Override
    public V remove(K key) {
        Node<K, V> p = dummyHead;

        while (null != p.next) {
            if (p.next.key.equals(key)) {
                break;
            }

            p = p.next;
        }

        if (null == p.next) {
            return null;
        }

        Node<K, V> node = p.next;

        p.next = p.next.next;
        size--;

        node.next = null;
        return node.value;
    }

    @Override
    public void set(K key, V value) {
        Node<K, V> node = getNode(key);

        if (null == node) {
            throw new IllegalArgumentException("key " + key + " doesn't exist");
        }

        node.value = value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        if (null == node) {
            return null;
        }

        return node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private class Node<K, V> {
        K key;
        V value;

        Node<K, V> next;

        Node(K key, V value) {
            this(key, value, null);
        }

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


        @Override
        public String toString() {
            return key + ":" + value;
        }
    }
}
