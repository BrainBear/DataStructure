package me.brainbear.array;

public class Array<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] data;
    private int size;

    public Array() {
        this(DEFAULT_CAPACITY);
    }


    @SuppressWarnings({"WeakerAccess", "unchecked"})
    public Array(int capacity) {
        this.data = (T[]) new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        return data[index];
    }

    public int index(T t) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] == t) {
                index = i;
                break;
            }
        }
        return index;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        T ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;

        if (size < data.length / 4) {
            resize(data.length / 2);
        }

        return ret;
    }

    public void add(T t) {
        add(size, t);
    }

    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        if (size == data.length) {
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;

        size++;
    }

    public T set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index:" + index);
        }

        T ret = data[index];
        data[index] = t;
        return ret;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d ", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

}
