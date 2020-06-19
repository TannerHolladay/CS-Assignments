package assign09;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a map of keys to values. It cannot contain
 * duplicate keys, and each key can map to at most one value.
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 * @author Tanner Holladay
 * @version March 25, 2020
 */
public class HashTable<K, V> implements Map<K, V> {

    private ArrayList<MapEntry<K, V>> table;
    private int size;

    /**
     * Constructor for the HashTable class.
     * Creates an array list with 10 null items
     */
    public HashTable() {
        table = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            table.add(null);
        }
        size = 0;
    }

    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length)
     */
    public void clear() {
        table = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            table.add(null);
        }
        size = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1)
     *
     * @param key
     * @return true if this map contains the key, false otherwise
     */
    public boolean containsKey(K key) {
        MapEntry<K, V> entry = table.get(quadraticIndex(key));
        return entry != null;
    }

    /**
     * Determines whether this map contains the specified value.
     * <p>
     * O(table length)
     *
     * @param value
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    public boolean containsValue(V value) {
        for (MapEntry v : table) {
            if (v != null && v.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a List view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * <p>
     * O(table length)
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    public List<MapEntry<K, V>> entries() {
        ArrayList<MapEntry<K, V>> entries = new ArrayList<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                entries.add(entry);
            }
        }
        return entries;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1)
     *
     * @param key
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    public V get(K key) {
        MapEntry<K, V> entry = table.get(quadraticIndex(key));
        return entry == null ? null : entry.getValue();
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1)
     *
     * @return true if this map contains no mappings, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * <p>
     * O(1)
     *
     * @param key
     * @param value
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    public V put(K key, V value) {
        int index = quadraticIndex(key);
        MapEntry<K, V> previous = table.get(index);
        table.set(index, new MapEntry<>(key, value));
        if (previous != null) {
            return previous.getValue();
        }
        size++;
        if (size >= table.size() / 2) {
            rehash();
        }
        return null;
    }

    /**
     * Rehashes the array so that more items can be added
     */
    private void rehash() {
        ArrayList<MapEntry<K, V>> oldTable = table;
        int nextSize = nextPrime(oldTable.size() * 2);
        table = new ArrayList<>(nextSize);
        for (int i = 0; i < nextSize; i++) {
            table.add(null);
        }
        size = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Finds a prime that is greater than the given input
     *
     * @param num the number to find a prime from
     * @return the prime number greater than the input
     */
    private int nextPrime(int num) {
        while (num % 2 == 0) {
            num++;
        }
        return num;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * <p>
     * O(1)
     *
     * @param key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    public V remove(K key) {
        MapEntry<K, V> removed = table.set(quadraticIndex(key), null);
        if (removed != null) {
            size--;
            return removed.getValue();
        }
        return null;
    }

    /**
     * Returns the index that the given key is or should be at
     *
     * @param key - key used to find an index
     * @return returns the index that the key is or should be
     */
    private int quadraticIndex(K key) {
        int hash = key.hashCode();
        int index = Math.abs(key.hashCode()) % table.size();
        int count = 0;
        MapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (entry.getKey().hashCode() == hash && key.equals(entry.getKey())) {
                return index;
            }
            count++;
            index += count * count;
            index = index % table.size();
            entry = table.get(index);
        }
        return index;
    }

    /**
     * Determines the number of mappings in this map.
     * <p>
     * O(1)
     *
     * @return the number of mappings in this map
     */
    public int size() {
        return size;
    }
}
