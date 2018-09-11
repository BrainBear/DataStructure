package me.brainbear.queue;

import me.brainbear.linked.LinkedList;

public class LinkedListQueue<T> implements Queue<T> {

    private final LinkedList<T> linkedList;

    public LinkedListQueue() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void enqueue(T t) {
        linkedList.addTail(t);
    }

    @Override
    public T dequeue() {
        return linkedList.removeHead();
    }

    @Override
    public T getFront() {
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
        sb.append("LinkedListQueue: Front [");
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
