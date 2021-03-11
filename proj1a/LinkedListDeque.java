public class LinkedListDeque<T> {
    /** A nested class that serves as a naked implementation of a DLList
     * the implementation of a T deque uses a sentinel node
     */
    private class Node {
        T value;
        Node previous;
        Node next;

        Node(T value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        /** Helper method of LinkedListDeque.get() method. Uses recursion. */
        T getHelper(int i) {
            if (i == 0) {
                return this.value;
            }

            return next.getHelper(i - 1);
        }
    }

    private Node sentinel;
    private int size;

    /** Instantiates an empty deque */
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        this.size = 0;
    }

    /** returns the size of the deque */
    public int size() {
        return this.size;
    }

    /** Returns true if and only if the deque contains at least one item */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Add @code item to the front of the DLList */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.previous = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.previous = new Node(item, sentinel.previous, sentinel);
        sentinel.previous.previous.next = sentinel.previous;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnValue = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size--;
        return returnValue;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnValue = sentinel.previous.value;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return returnValue;
    }

    /** Prints the elements of the deque, separated by a space */
    public void printDeque() {
        if (size == 0) {
            System.out.println();
        } else if (size == 1) {
            System.out.println(sentinel.next.value);
        } else {
            Node curr = sentinel;
            while (curr.next.next != sentinel) {
                System.out.print(curr.next.value + " ");
                curr = curr.next;
            }
            System.out.print(curr.next.value);
            System.out.println();
        }
    }

    /** Returns the @code index-th item of the deque. If no such item exists, return null */
    public T getRecursive(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }
        return sentinel.next.getHelper(index);
    }

    /** Returns the index-th item of the deque without using recursion */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node curr = sentinel.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }
}