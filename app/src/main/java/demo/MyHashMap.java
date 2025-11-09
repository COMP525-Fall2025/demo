package demo;

import java.math.BigInteger;
import java.util.*;

// Hash-table map implementations. Null keys are not allowed.
public class MyHashMap<Key, Data> {
  static final int INIT_SIZE = 17;
  static final float LOAD_FACTOR = 0.75f;

  public static class Entry<K, D> {
    private final K key;
    private D value;
    private Entry<K, D> next;

    Entry(K key, D value, Entry<K, D> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public K getKey() {
      return key;
    }

    public D getValue() {
      return value;
    }

    public D setValue(D value) {
      var val = this.value;
      this.value = value;
      return val;
    }
  }

  private Entry<Key, Data>[] table; // each table index has a linked list of entries
  private int size; // size of the map, which is the total number of entries

  public MyHashMap(int tableSize) {
    table = makeTable(tableSize);
  }

  // Constructs an empty map with a default size of 17.
  public MyHashMap() {
    this(INIT_SIZE);
  }

  public void clear() {
    Arrays.fill(table, null);
    size = 0;
  }

  private int indexOf(Key key) {
    return Math.floorMod(key.hashCode(), table.length);
  }

  // Searches the table for an element with the given key and returns the
  // associated entry,otherwise returns null.
  private Entry<Key, Data> getEntry(Key key) {
    var entry = table[indexOf(Objects.requireNonNull(key))];
    while (entry != null) {
      if (entry.key.equals(key))
        return entry;
      entry = entry.next;
    }
    return null;
  }

  public Data putIfAbsent(Key key, Data value) {
    Objects.requireNonNull(key, "key");
    Entry<Key, Data> entry = getEntry(key);
    if (entry != null) {
      // Key already present: do NOT overwrite; return existing value
      return entry.getValue();
    }
    // Otherwise, reuse put() to insert; put returns null when newly inserted
    return put(key, value);
  }

  public Data put(Key key, Data value) {
    Objects.requireNonNull(key, "key");
    Entry<Key, Data> entry = getEntry(key);
    if (entry != null) {
      return entry.setValue(value);
    }

    // new key insert at bucket head
    int index = indexOf(key);

    // you fill here here the single line of code to insert a new node. 
    size++;

    if (size > LOAD_FACTOR * table.length) {
      rehash();
    }
    return null; // no previous mapping
  }

  public boolean remove(Key key) {
    int index = indexOf(Objects.requireNonNull(key));
    Entry<Key, Data> entry = table[index], prev = null;
    while (entry != null) {
      if (entry.key.equals(key)) {
        if (prev == null) // first in bucket
          table[index] = entry.next;
        else
          prev.next = entry.next;
        size--;
        return true;
      }
      prev = entry;
      entry = entry.next;
    }
    return false;
  }

  public Data getOrDefault(Key key, Data defaultValue) {
    var entry = getEntry(key);
    return entry == null ? defaultValue : entry.value;
  }

  public Data get(Key key) {
    return getOrDefault(key, null);
  }

  public boolean containsKey(Key key) {
    return getEntry(key) != null;
  }

  public Data replace(Key key, Data val) {
    var entry = getEntry(key);
    return entry == null ? null : entry.setValue(val);
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void rehash() {
    var oldTable = this.table;
    this.table = makeTable(nextPrime(oldTable.length));

    // find and reinsert all elements from old table
    // you fill in here the code to rehash all values
  }

  // Returns the next prime number >= 2 * n (capped at Integer.MAX_VALUE).
  // Uses long for doubling to avoid int overflow before creating the BigInteger.
  private static int nextPrime(int n) {
    long doubled = 2L * n; // safe 64-bit arithmetic
    long p = BigInteger.valueOf(doubled).nextProbablePrime().longValue();
    return (int) Math.min(p, (long) Integer.MAX_VALUE);
  }

  @SuppressWarnings("unchecked")
  private Entry<Key, Data>[] makeTable(int size) {
    return (Entry<Key, Data>[]) new Entry<?, ?>[size];
  }

  public static void main(String[] args) {
    MyHashMap<String, Integer> map = new MyHashMap<>();

    // Test 1: Basic put and get
    System.out.println("== Test 1: Basic put and get ==");
    map.put("a", 1);
    map.put("b", 2);
    System.out.println(map.get("a")); // Expected: 1
    System.out.println(map.get("b")); // Expected: 2
    System.out.println("size = " + map.size()); // Expected: 2

    // Test 2: Overwrite existing key with put()
    System.out.println("\n== Test 2: Overwrite with put() ==");
    System.out.println(map.put("a", 100)); // Expected: 1 (old value)
    System.out.println(map.get("a")); // Expected: 100

    // Test 3: putIfAbsent() functionality
    System.out.println("\n== Test 3: putIfAbsent() ==");
    System.out.println(map.putIfAbsent("a", 200)); // Expected: 100 (existing)
    System.out.println(map.putIfAbsent("c", 300)); // Expected: null (new)
    System.out.println(map.get("c")); // Expected: 300

    // Test 4: containsKey() and size()
    System.out.println("\n== Test 4: containsKey() and size() ==");
    System.out.println(map.containsKey("a")); // Expected: true
    System.out.println(map.containsKey("x")); // Expected: false
    System.out.println("size = " + map.size()); // Expected: 3

    // Test 5: remove()
    System.out.println("\n== Test 5: remove() ==");
    System.out.println(map.remove("b")); // Expected: true
    System.out.println(map.remove("z")); // Expected: false
    System.out.println(map.get("b")); // Expected: null
    System.out.println("size = " + map.size()); // Expected: 2

    // Test 6: replace()
    System.out.println("\n== Test 6: replace() ==");
    System.out.println(map.replace("a", 999)); // Expected: 100
    System.out.println(map.get("a")); // Expected: 999
    System.out.println(map.replace("x", 1)); // Expected: null

    // Test 7: getOrDefault()
    System.out.println("\n== Test 7: getOrDefault() ==");
    System.out.println(map.getOrDefault("c", -1)); // Expected: 300
    System.out.println(map.getOrDefault("d", -1)); // Expected: -1

    // Test 8: isEmpty() and clear()
    System.out.println("\n== Test 8: isEmpty() and clear() ==");
    System.out.println(map.isEmpty()); // Expected: false
    map.clear();
    System.out.println(map.isEmpty()); // Expected: true
    System.out.println(map.size()); // Expected: 0

    // Test 9: Rehash triggered by many inserts
    System.out.println("\n== Test 9: Rehash behavior ==");
    for (int i = 0; i < 100; i++) {
      map.put("key" + i, i);
    }
    System.out.println("size = " + map.size()); // Expected: 100
    System.out.println(map.get("key50")); // Expected: 50
    System.out.println(map.get("key99")); // Expected: 99

    // Test 10: Null key insertion (should throw)
    System.out.println("\n== Test 10: Null key test ==");
    try {
      map.put(null, 123);
    } catch (NullPointerException e) {
      System.out.println("Passed: Null key not allowed");
    }
  }
}
