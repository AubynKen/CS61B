package es.datastructur.synthesizer;
//import java.util.ArrayList;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;

    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    private int plusOne(int n) {
        return (n == capacity() - 1) ? 0 : n + 1;
    }

    private int minusOne(int n) {
        return (n == 0) ? capacity() - 1 : n - 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount++;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        first = plusOne(first);
        fillCount--;
        return res;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        int nextIndex;

        ArrayRingBufferIterator() {
            nextIndex = first;
        }

        @Override
        public boolean hasNext() {
            return nextIndex != last;
        }

        @Override
        public T next() {
            T res = rb[nextIndex];
            nextIndex = plusOne(nextIndex);
            return res;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    public String toString() {
        var sb = new StringBuilder();
        if (isEmpty()) {
            return "[]";
        }
        sb.append("[");
        sb.append(rb[first]);
        for (int i = plusOne(first); i != last; i = plusOne(i)) {
            sb.append(" " + rb[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        var other = (ArrayRingBuffer) o;
        if (this.fillCount() != other.fillCount()) {
            return false;
        }
        var myIterator = this.new ArrayRingBufferIterator();
        var otherIterator = other.new ArrayRingBufferIterator();
        while (myIterator.hasNext()) {
            if (!myIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }
        return true;
    }


}
