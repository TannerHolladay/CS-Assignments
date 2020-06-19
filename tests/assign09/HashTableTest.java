package assign09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {

    HashTable<Integer, Integer> table1;
    Random rnd = new Random();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        table1 = new HashTable<>();
        ArrayList<Integer> randlist = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            randlist.add(i);
        }
        Collections.shuffle(randlist);
        for (Integer number : randlist) {
            table1.put(rnd.nextInt(100), number);
        }
    }

    @org.junit.jupiter.api.Test
    void testclear() {
        table1.put(10, 123);
        table1.put(13, 123);
        table1.clear();
        assertEquals(0, table1.size());
        assertEquals(0, table1.entries().size());
        assertEquals(null, table1.get(10));
        assertEquals(null, table1.remove(13));
        assertTrue(table1.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testcontainsKey() {
        for (int i = 0; i < 1000; i++) {
            int randomkey = rnd.nextInt();
            table1.put(randomkey, rnd.nextInt());
            assertTrue(table1.containsKey(randomkey));
        }
        table1.clear();
        for (int i = 0; i < 1000; i++) {
            assertFalse(table1.containsKey(i));
        }
    }

    @org.junit.jupiter.api.Test
    void testcontainsValue() {
        for (int i = 0; i < 1000; i++) {
            int randomValue = rnd.nextInt();
            table1.put(rnd.nextInt(), randomValue);
            assertTrue(table1.containsValue(randomValue));
        }
        table1.clear();
        for (int i = 0; i < 1000; i++) {
            assertFalse(table1.containsValue(i));
        }
    }

    @org.junit.jupiter.api.Test
    void testentries() {
        HashTable<Integer, String> testTable = new HashTable<>();
        ArrayList<MapEntry<Integer, String>> entryArray = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            testTable.put(i, "asdf");
            entryArray.add(new MapEntry<>(i, "asdf"));
        }
        assertEquals(entryArray, testTable.entries());
    }

    @org.junit.jupiter.api.Test
    void testget() {
        for (int i = 0; i < 1000; i++) {
            int randint = rnd.nextInt();
            int randval = rnd.nextInt();
            table1.put(randint, randval);
            assertEquals(randval, table1.get(randint));
        }
    }

    @org.junit.jupiter.api.Test
    void testisEmpty() {
        table1.clear();
        assertTrue(table1.isEmpty());
        table1.put(100, 1234123);
        assertFalse(table1.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testput() {
        for (int i = 0; i < 1000; i++) {
            int randint = rnd.nextInt();
            int randval = rnd.nextInt();
            table1.put(randint, randval);
            assertEquals(randval, table1.get(randint));
            assertEquals(randval, table1.put(randint, 123));
            assertEquals(123, table1.get(randint));
        }
    }

    @org.junit.jupiter.api.Test
    void testremove() {
        table1.clear();
        for (int i = 0; i < 1000; i++) {
            int randint = rnd.nextInt();
            int randval = rnd.nextInt();
            table1.put(randint, randval);
            assertEquals(randval, table1.get(randint));
            assertEquals(randval, table1.remove(randint));
            assertEquals(null, table1.remove(randint));
            assertEquals(null, table1.get(randint));
        }
    }

    @org.junit.jupiter.api.Test
    void testsize() {
        table1.clear();
        assertEquals(0, table1.size());
        table1.put(10, 100);
        assertEquals(1, table1.size());
        table1.put(10, 101230);
        assertEquals(1, table1.size());
        table1.put(11, 1234123);
        table1.put(123, 1234123);
        table1.remove(123);
        assertEquals(2, table1.size());
    }
}