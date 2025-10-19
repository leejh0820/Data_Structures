package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Jeonghyun Lee
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        buckets = createTable(initial_Size);
        size = 0;
        keys = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        return keySet().contains(key);
    }

    @Override
    public V get(K key) {
        Node n = getHelper(key);
        if (n == null) {
            return null;
        }
        return n.value;
    }
    private Node getHelper(K key) {
        int i = Helper(key, buckets.length);
        Collection<Node> cNode = buckets[i];
        if (cNode == null) {
            return null;
        }

        for (Node n : cNode) {
            if (n.key.equals(key)) {
                return n;
            }
        }
        return null;
    }
    private int Helper (K key, int numKey) {
        return Math.floorMod(key.hashCode(), numKey);
    }

    @Override
    public int size() {
        return size;
    }
    private void resize(int s) {
        Collection<Node>[] cNode = createTable(s);
        for (K key: keys) {
            int i = Helper(key, cNode.length);
            if (cNode[i] == null) {
                cNode[i] = createBucket();
            }
            cNode[i].add(getHelper(key));
        }
        buckets = cNode;
    }

    @Override
    public void put(K key, V value) {
        Node n = getHelper(key);
        int i = Helper(key, buckets.length);
        Collection<Node> cNode = buckets[i];

        if (n != null) {
            n.value = value;
            return;
        }

        if (cNode == null) {
            cNode = createBucket();
            buckets[i] = cNode;
        }

        cNode.add(createNode(key, value));
        size ++;
        keys.add(key);


        if((double)size/buckets.length > loadFactor) {
            resize(buckets.length*2);
        }

    }

    @Override
    public Set<K> keySet() {
        return keys;
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
        return keys.iterator();
    }
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private static final int initial_Size = 16;
    private static final double load_Factor = 0.75;

    private int size;
    private double loadFactor;
    private HashSet<K> keys;

    /** Constructors */
    public MyHashMap() {
        this(initial_Size, load_Factor);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, load_Factor);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = 0;
        loadFactor = maxLoad;
        buckets = createTable(initialSize);
        keys = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
}
