package queue;

import java.util.StringJoiner;

public class MyQueue<T> {

    private static final int CAPACITY = 10;
    private T[] elements;
    private int head;
    private int tail;
    private int size;

    public MyQueue() {
        elements = (T[]) new Object[CAPACITY];
    }

    public void add(T element) {
        if (size == elements.length) {
            T[] tmpArr = (T[]) new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                tmpArr[i] = elements[(head + i) % elements.length];
            }
            head = 0;
            tail = size;
            elements = tmpArr;
        }
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public void clear() {
        elements = (T[]) new Object[CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return elements[head];
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        T firstElement = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return firstElement;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            T element = elements[(head + i) % elements.length];
            sj.add(element == null ? "null" : element.toString());
        }
        return sj.toString();
    }
}
