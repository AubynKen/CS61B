import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            size = 1;
        }
    }

    @Override
    public void clear() {
        this.root = null;
    }

    private boolean containsKey(Node x, K key) {
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return containsKey(x.right, key);
        } else if (cmp < 0) {
            return containsKey(x.left, key);
        }
        return true;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        }
        return x.val;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    @Override
    public int size() {
        return size(root);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
            x.size = size(x.left) + size(x.right) + 1;
            return x;
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
            x.size = size(x.left) + size(x.right) + 1;
            return x;
        }
        return x;
    }

    @Override
    public void put(K key, V value) {
        this.root = put(root, key, value);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private void printInOrder(Node x) {
        if (x == null) {
            return;
        } else {
            printInOrder(x.left);
            System.out.print(x.val + " ");
            printInOrder(x.right);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }
}
