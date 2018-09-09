package me.brainbear.linked;

public class LinkedList<T> {

    private int size;
    private Node<T> dummyHead;

    public LinkedList() {
        size = 0;
        dummyHead = new Node<T>();
        dummyHead.next = dummyHead;
        dummyHead.prev = dummyHead;
    }


    public void add(T t) {
        addTail(t);
    }

    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        if (index == 0) {
            addHead(t);
            return;
        }

        if (index == size) {
            addTail(t);
            return;
        }

        Node<T> p = dummyHead;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        Node<T> next = p.next;

        Node<T> node = new Node<>(t);
        p.next = node;
        node.prev = p;

        next.prev = node;
        node.next = next;

        size++;
    }

    public void addHead(T t) {
        Node<T> node = new Node<>(t);

        Node<T> first = dummyHead.next;

        dummyHead.next = node;
        node.prev = dummyHead;

        if (dummyHead != first) {
            first.prev = node;
            node.next = first;
        } else {
            dummyHead.prev = node;
            node.next = dummyHead;
        }
        size++;
    }

    public void addTail(T t) {
        Node<T> node = new Node<>(t);

        Node<T> tail = dummyHead.prev;

        dummyHead.prev = node;
        node.next = dummyHead;

        if (dummyHead != tail) {
            tail.next = node;
            node.prev = tail;
        } else {
            dummyHead.next = node;
            node.prev = dummyHead;
        }
        size++;
    }


    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        if (index == 0) {
            return removeHead();
        }

        if (index == size - 1) {
            return removeTail();
        }

        Node<T> p = dummyHead;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        Node<T> target = p.next;

        p.next = target.next;
        target.next.prev = p;

        target.prev = null;
        target.next = null;
        size--;

        return target.data;
    }


    public T removeHead() {
        if (size < 0) {
            throw new IllegalArgumentException("illegal index:" + 0);
        }

        Node<T> first = dummyHead.next;

        if (first.next == dummyHead) {
            dummyHead.next = dummyHead;
            dummyHead.prev = dummyHead;
        } else {
            dummyHead.next = first.next;
            first.next.prev = dummyHead;
        }

        first.next = null;
        first.prev = null;

        size--;

        return first.data;
    }


    public T removeTail() {
        if (size < 0) {
            throw new IllegalArgumentException("illegal index:" + 0);
        }

        Node<T> tail = dummyHead.prev;

        if (tail.prev == dummyHead) {
            dummyHead.next = dummyHead;
            dummyHead.prev = dummyHead;
        } else {
            dummyHead.prev = tail.prev;
            tail.prev.next = dummyHead;
        }

        tail.next = null;
        tail.prev = null;
        size--;

        return tail.data;
    }


    public T set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        Node<T> p = dummyHead;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        T ret = p.next.data;
        p.next.data = t;

        return ret;
    }

    public int index(T t) {
        int index = -1;

        Node<T> p = dummyHead.next;
        for (int i = 0; p != null && i < size; i++) {
            if (p.data == t) {
                index = i;
                break;
            }
            p = p.next;
        }
        return index;
    }


    public int getSize() {
        return size;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkedList: size = %d ", size));

        Node<T> p = dummyHead.next;
        while(p != dummyHead){
            res.append(p.data);
            res.append("->");
            p = p.next;
        }
        res.append("NULL");
        return res.toString();
    }

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;


        Node() {
            data = null;
            next = null;
            prev = null;
        }

        Node(T t) {
            data = t;
            next = null;
            prev = null;
        }

        Node(T t, Node<T> next, Node<T> prev) {
            data = t;
            this.next = next;
            this.prev = prev;
        }

    }

}
