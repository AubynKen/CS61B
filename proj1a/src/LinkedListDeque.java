public class LinkedListDeque<T> {
    private class Node<TT> {
        public TT value;
        public Node<TT> previous;
        public Node<TT> next;

        public Node(TT value, Node<TT> previous, Node<TT> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public Node(TT value) {
            this.value = value;
            this.previous = this;
            this.next = this;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node<T>(null);
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T x) {
        Node<T> newFirstNode = new Node<T>(x, sentinel, sentinel.next);
        sentinel.next.previous = newFirstNode;
        sentinel.next = newFirstNode;
        size++;
    }

    public void addLast(T x) {
        Node<T> newLastNode = new Node<T>(x, sentinel.previous, sentinel);
        sentinel.previous.next = newLastNode;
        sentinel.previous = newLastNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node<T> curr = this.sentinel;
        while (curr.next != sentinel) {
            System.out.print(curr.next.value);
            curr = curr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }
        T oldFirstValue = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size--;
        return oldFirstValue;
    }

    public T removeLast() {
        if (this.size() == 0) return null;
        T oldLastValue = sentinel.previous.value;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return oldLastValue;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= size) return null;
        Node<T> curr = sentinel;
        for (int i = 0; i < pos; i++) {
            curr = curr.next;
        }
        return curr.value;
    }
}
