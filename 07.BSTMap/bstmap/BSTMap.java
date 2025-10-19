package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V> {
    private BST root;
    private int size;


    public void clear() {
        size = 0;
        root = null;

    }


    public boolean containsKey(K key) {
        return getValue(root, key) != null;
    }


    public V get(K key) {
        BST b = getValue(root, key);
        if (b == null) {
            return null;
        }
        return b.value;
    }
    private BST getValue(BST b, K key) {
        if (b == null) {
            return null;
        }
        int i = key.compareTo(b.key);
        if (i == 0) {
            return b;
        }
        if (i < 0) {
            return getValue(b.left, key);
        }
        return getValue(b.right, key);
    }


    public int size() {
        return size;
    }

//     if (ik ≺ T.key)
//    T.left = insert(T.left, ik);
//  else if (ik ≻ T.key)
//    T.right = insert(T.right, ik);
//  return T;

    public void put(K key, V value) {
        root = insert(key, value, root);
    }
    private BST insert(K key, V value, BST b) {
        if (b  == null) {
            size ++;
            return new BST(key, null, null, value);
        }
        int i = key.compareTo(b.key);
        if (i == 0) {
            b.value = value;
        }
        if (i < 0) {
            b.left = insert(key, value, b.left);
        }
        if (i > 0) {
            b.right = insert(key, value, b.right);
        }
        return b;
    }

    private class BST {
        private final K key;
        private V value;
        private BST left;
        private BST right;

        public BST(K key, BST left, BST right, V value) {
            this.value = value;
            this.key = key;
            this.left = left;
            this.right = right;
        }

    }


    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }


    public V remove(K key) {
        throw new UnsupportedOperationException();
    }


    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {

    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
