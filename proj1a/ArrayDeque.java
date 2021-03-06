public class ArrayDeque<T> {
    private T[] arr;
    private int size;
    private int firstPos;
    private int lastPos;
    private final int FACTOR = 2; // The resizing factor

    /** creates empty array */
    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        firstPos = 3;
        lastPos = 2;
    }

    /** checks if the array is full, and if that is the case, resize the array */
    public void checkExpand() {
        if (size < arr.length) return;
        T[] newArr = (T[]) new Object[arr.length * FACTOR];
        if (lastPos > firstPos) {
            System.arraycopy(arr, 0, newArr, 0, arr.length);
        } else {
            System.arraycopy(arr, 0, newArr, 0, lastPos + 1);
            System.arraycopy(arr, firstPos, newArr, firstPos + arr.length, arr.length - firstPos);
        }
        firstPos += arr.length;
        arr = newArr;
    }

    /** checks if the deque.size < arr.length/4, and if yes, shrink the array */
    public void checkShrink() {
        if (size > arr.length/4) return;
        T[] newArr = (T[]) new Object[arr.length/2];
        if (lastPos > firstPos) {
            System.arraycopy(arr, firstPos, newArr, 0, size);
            firstPos = 0;
            lastPos = firstPos + size - 1;
        } else {
            System.arraycopy(arr, firstPos, newArr, firstPos - newArr.length, arr.length - firstPos);
            System.arraycopy(arr, 0, newArr, 0, lastPos + 1);
            firstPos = firstPos - newArr.length;
        }
        arr = newArr;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addLast(T item) {
        checkExpand();
        lastPos++;
        if (lastPos >= arr.length) lastPos -= arr.length;
        arr[lastPos] = item;
        size++;
    }

    public void addFirst(T item) {
        checkExpand();
        firstPos--;
        if (firstPos < 0) firstPos += arr.length;
        arr[firstPos] = item;
        size ++;
    }

    public T get(int pos) {
        int targetIndexInArray = firstPos + pos;
        if (targetIndexInArray >= arr.length) {
            targetIndexInArray -= arr.length;
        }
        return arr[targetIndexInArray];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        T res = arr[firstPos];
        arr[firstPos] = null;
        firstPos ++;
        if (firstPos >= arr.length) {
            firstPos -= arr.length;
        }
        size--;
        checkShrink();
        return res;
    }

    public T removeLast() {
        T res = arr[lastPos];
        arr[lastPos] = null;
        lastPos--;
        if (lastPos < 0) {
            lastPos += arr.length;
        }
        size--;
        checkShrink();
        return res;
    }



}
