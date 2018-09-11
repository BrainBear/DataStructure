package me.brainbear.stack;

public interface Stack<T> {

    void push(T t);

    T pop();

    T peek();

    int getSize();

    boolean isEmpty();
}
