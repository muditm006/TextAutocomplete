import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void testSize() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertEquals(3, myHashMap.size());
    }

    @Test
    public void testGet() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertEquals((Integer) 1, myHashMap.get("i"));
    }

    @Test
    public void testNullKey() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertNull(myHashMap.get(null));
    }

    @Test
    public void testKeyDoesNotExist() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertNull(myHashMap.get("mudit"));
    }


    private HashMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
    }


    @Test
    public void testContainsKey() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertTrue(myHashMap.containsKey("i"));
    }

    @Test
    public void testContainsNullKey() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertFalse(myHashMap.containsKey(null));
        myHashMap.put(null,null);
        assertTrue(myHashMap.containsKey(null));
    }

    @Test
    public void testContainsKeyNotThere() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i",1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertFalse(myHashMap.containsKey("mudit"));
    }


    @Test
    public void testPutNewKey() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        assertNull(myHashMap.put("i", 1));
        assertTrue(myHashMap.containsKey("i"));
        myHashMap.put("s",2);
        myHashMap.put("a",3);
    }

    @Test
    public void testPutKeyAlreadyPresent() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertEquals((Integer) 3, myHashMap.put("a",4));
    }

    @Test
    public void testPutKeyAndContainsKeyCollision() {
        HashMap<Object, Integer> myHashMap = new HashMap<Object, Integer>();
        Object obj1 = new Object() {
            @Override
            public int hashCode() {
                return 5;
            }
        };

        Object obj2 = new Object() {
            @Override
            public int hashCode() {
                return 5;
            }
        };

        Object obj3 = new Object() {
            @Override
            public int hashCode() {
                return 5;
            }
        };

        myHashMap.put(obj1, 1);
        myHashMap.put(obj2, 2);
        assertTrue(myHashMap.containsKey(obj1));
        assertTrue(myHashMap.containsKey(obj2));
        myHashMap.put(obj3, 1);
        assertTrue(myHashMap.containsKey(obj3));
    }

    @Test
    public void testResize() {
        HashMap<Object, Integer> myHashMap = new HashMap<Object, Integer>();
        for (int keyNum = 0; keyNum < 20; keyNum++) {
            myHashMap.put("Key" + keyNum, keyNum);
        }
        for (int keyNum = 0; keyNum < 20; keyNum++) {
            assertTrue(myHashMap.containsKey("Key" + keyNum));
            assertEquals((Integer) keyNum, myHashMap.get("Key" + keyNum));
        }
        assertEquals(20, myHashMap.size());
    }

    /*
    I couldn't get this test case to finish executing because it (obviously) takes forever,
    but this would theoretically test Max Capacity
    @Test
    public void testResizeAtMaximumCapacity() {
    /    final int MAXIMUM_CAPACITY = 1 << 30;
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();

        for (int i = 0; i < MAXIMUM_CAPACITY * 0.75; i++) {
            myHashMap.put("Key" + i, i);
        }

        myHashMap.put("NewKey", 999);

        assertTrue(myHashMap.containsKey("NewKey"));
        assertEquals((Integer) 999, myHashMap.get("NewKey"));
    }
    */

    @Test
    public void testRemoveSimple() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertEquals((Integer) 1, myHashMap.remove("i"));
    }

    @Test
    public void testRemoveNotPresent() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        assertNull(myHashMap.remove("mudit"));
    }

    @Test
    public void testRemoveCollisions() {
        HashMap<Object, Integer> myHashMap = new HashMap<Object, Integer>();
        Object obj1 = new Object() {
            @Override
            public int hashCode() {
                return 5;
            }
        };

        Object obj2 = new Object() {
            @Override
            public int hashCode() {
                return 5;
            }
        };
        myHashMap.put(obj1, 1);
        myHashMap.put(obj2, 2);
        assertEquals((Integer) 1, myHashMap.remove(obj1));
    }

    @Test
    public void testContainsValue() {
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        myHashMap.put("mudit", "isa");
        myHashMap.put("bro", "bear");
        myHashMap.put("kings", "court");
        assertTrue(myHashMap.containsValue("isa"));
    }

    @Test
    public void testContainsValueFalse() {
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        myHashMap.put("mudit", "isa");
        myHashMap.put("bro", "bear");
        myHashMap.put("kings", "court");
        assertFalse(myHashMap.containsValue("mudit"));
    }

    @Test
    public void testContainsValueNull() {
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        myHashMap.put("mudit", "isa");
        myHashMap.put("bro", "bear");
        myHashMap.put("kings", "court");
        myHashMap.put(null, null);
        assertTrue(myHashMap.containsValue(null));
    }

    @Test
    public void testIsEmpty() {
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        assertTrue(myHashMap.isEmpty());

    }

    @Test
    public void testIsEmptyFalse() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        assertFalse(myHashMap.isEmpty());
    }

    @Test
    public void testClear() {
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        myHashMap.put("mudit", "isa");
        myHashMap.put("bro", "bear");
        myHashMap.put("kings", "court");
        myHashMap.clear();
        assertTrue(myHashMap.isEmpty());
    }

    @Test
    public void testEntryIteratorHasNextTrue() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        assertTrue(myIterator.hasNext());
    }

    @Test
    public void testEntryIteratorHasNextFalse() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        assertFalse(myIterator.hasNext());
    }

    @Test
    public void testEntryIteratorNext() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        Map.Entry<String, Integer> firstEntry = myIterator.next();
        assertEquals("s", firstEntry.getKey());
        assertEquals((Integer) 2, firstEntry.getValue());
    }

    @Test
    public void testEntryIteratorNoHasNext() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        myIterator.next();
        myIterator.next();
        myIterator.next();
        assertFalse(myIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEntryIteratorNoNext() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        myIterator.next();
        myIterator.next();
        myIterator.next();
        myIterator.next();
    }

    @Test
    public void testEntrySet() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        HashSet<Map.Entry<String, Integer>> expectedElements = new HashSet<>();
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        expectedElements.add(myIterator.next());
        expectedElements.add(myIterator.next());
        expectedElements.add(myIterator.next());
        assertEquals(expectedElements, myHashMap.entrySet());
    }

    @Test
    public void testEntryEquals() {
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put("i", 1);
        myHashMap.put("s",2);
        myHashMap.put("a",3);
        Iterator<Map.Entry<String, Integer>> myIterator = myHashMap.entryIterator();
        Map.Entry<String, Integer> firstEntry = myIterator.next();
        Map.Entry<String, Integer> secondEntry = myIterator.next();
        assertNotEquals(secondEntry, firstEntry);
        assertNotEquals(null, secondEntry);
    }
}