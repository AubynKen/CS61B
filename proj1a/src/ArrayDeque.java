
/**
 * Implementation of the deque structure using a circular array.
 */
public class ArrayDeque<T> {
    private static final int FACTOR = 2; // Expansion factor.
    private static final int INITIAL_CAPACITY = 8; // Initial length of @code items.
    private static final int SHRINK_TEST_RATIO = 4; /* The minimum usage ratio of the items array should be
    greater than 1/SHRINK_TEST_RATIO */
    private T[] items;
    private int frontPos; // Index of the deque's first item.
    private int backPos; // Index of the last item.
    private int size;
    private int capacity; // Length of @code items.

    /**
     * Creates an empty array
     */
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        frontPos = 0;
        backPos = 7;
        capacity = 8;
    }

    /**
     * Returns true if and only if the deque doesn't contain any item.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items that the deque currently contains.
     */
    public int size() {
        return this.size;
    }

    /**
     * Given @code currIndex the current index, returns the next index in the circular array.
     * Notably, if the given index corresponds to the end of the array, returns 0.
     */
    private int indexPlusOne(int currIndex) {
        if (currIndex == items.length - 1) {
            return 0;
        }
        return currIndex + 1;
    }

    /**
     * Given @code currIndex the current index, returns the previous index in the circular array.
     * Notably, if the given index is 0, returns the last index of the array.
     */
    private int indexMinusOne(int currIndex) {
        if (currIndex == 0) {
            return items.length - 1;
        }
        return currIndex - 1;
    }

    /**
     * Expands @code items array by @code FACTOR the Expansion factor.
     */
    private void expand() {
        capacity *= FACTOR;
        T[] newItems = (T[]) new Object[capacity];
        newItems[0] = items[frontPos];
        int currIndex = 1;
        for (int i = indexPlusOne(frontPos); i != indexPlusOne(backPos); i = indexPlusOne(i)) {
            newItems[currIndex] = items[i];
            currIndex++;
        }
        items = newItems;
        frontPos = 0;
        backPos = size - 1;
    }

    /**
     * Adds @code item to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == capacity) {
            expand();
        }
        frontPos = indexMinusOne(frontPos);
        items[frontPos] = item;
        size++;
    }

    /**
     * Adds @code item to the end of the deque.
     */
    public void addLast(T item) {
        if (size == capacity) {
            expand();
        }
        ;
        backPos = indexPlusOne(backPos);
        items[backPos] = item;
        size++;
    }

    /**
     * Shrinks the @code items array by @code FACTOR.
     * Called when the usage ratio is smaller than 1/SHRINK_TEST_RATIO after item removal.
     */
    private void shrink() {
        int newCapacity = capacity/FACTOR;
        T[] newItems = (T[]) new Object[newCapacity];
        for(int i = frontPos, j = 0; i != indexPlusOne(backPos); i = indexPlusOne(i), j++) {
            newItems[j] = items[i];
        }
        items = newItems;
        capacity = newCapacity;
        frontPos = 0;
        backPos = size - 1;
    }

    /**
     * Removes the first item from the deque and de-reference the object for memory saving.
     * If the usage ratio is lower than the minimum usage ratio defined by @code SHRINK_TEST_RATIO,
     * call shrink() to decrease the length of the @code items array.
     */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        T res = items[frontPos];
        items[frontPos] = null;
        frontPos = indexPlusOne(frontPos);
        size--;
        if (size * SHRINK_TEST_RATIO <= capacity && capacity >= 8) {
            shrink();
        }
        return res;
    }

    /**
     * Removes the last item from the deque and de-reference the object for memory saving.
     * If the usage ratio is lower than the minimum usage ratio defined by @code SHRINK_TEST_RATIO,
     * call shrink() to decrease the length of the @code items array.
     */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        T res = items[backPos];
        items[backPos] = null;
        backPos = indexMinusOne(backPos);
        size--;
        if (size * SHRINK_TEST_RATIO <= capacity && capacity >= 8) {
            shrink();
        }
        return res;
    }

    /**
     * Prints out the items stored in the deque from front to back, separated by a space.
     */
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }

        System.out.print(items[frontPos]);
        for (int i = indexPlusOne(frontPos); i != indexPlusOne(backPos); i = indexPlusOne(i)) {
            System.out.print(" " + items[i]);
        }
        System.out.println();
    }

    /**
     * Returns the element corresponding to @code index.
     * eg. The first item corresponds to index 0.
     */
    public T get (int index) {
        if (this.isEmpty() || index <0 || index >= size) {
            return null;
        }
        int indexInItems = index + frontPos;
        indexInItems = indexInItems % capacity;
        return items[indexInItems];
    }

    public static void main(String[] args) {
//        var testDeque = new ArrayDeque<Integer>();
//        for (int i = 0; i < testDeque.capacity; i++) {
//           System.out.println("indexMinusOne(" + i + ")" + " -> " + testDeque.indexMinusOne(i));
//            System.out.println("indexPlusOne(" + i + ")" + " -> " + testDeque.indexPlusOne(i));
//        }
//
//        for (int i = 0; i < 10; i++) {
//            testDeque.addFirst(i);
//            testDeque.addLast(i);
//        }
//
//        for (int i = 0; i < testDeque.size(); i++) {
//            System.out.print(testDeque.get(i));
//            System.out.print(" ");
//        }
    }
}
