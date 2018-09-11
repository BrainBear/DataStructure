package me.brainbear.stack;

import me.brainbear.linked.LinkedList;

public class LinkedListStack<T> implements Stack<T> {


    private final LinkedList<T> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(T t) {
        linkedList.addHead(t);
    }

    @Override
    public T pop() {
        return linkedList.removeHead();
    }

    @Override
    public T peek() {
        return linkedList.get(0);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.getSize() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkListStack: Head [");
        for(int i = 0; i < linkedList.getSize(); i++){
            sb.append(linkedList.get(i));
            if(i != linkedList.getSize() - 1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
