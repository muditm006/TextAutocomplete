import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class TrieTest {

    @Test
    public void testPut() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", "isa");
        assertTrue(myTrie.containsKey("mudit"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put(null, "isa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullValue() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", null);
    }

    @Test
    public void testPutValueExisted() {
        Trie<String> myTrie = new Trie<>();
        assertNull(myTrie.put("isa", "mudit"));
        assertEquals("mudit", myTrie.put("isa", "bro bear"));
    }

    @Test
    public void testGetEmptyTrie() {
        Trie<String> myTrie = new Trie<>();
        assertNull(myTrie.get("isa"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetNullKey() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", "isa");
        myTrie.get(null);
    }

    @Test
    public void testGetValueNotExistent() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", "isa");
        assertNull(myTrie.get("isa"));
    }

    @Test
    public void testGetValue() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", "isa");
        assertEquals("isa", myTrie.get("mudit"));
    }

    @Test
    public void testContainsKey() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", "isa");
        myTrie.put("matthew", "scott");
        myTrie.put("mudit", "marwaha");
        assertTrue(myTrie.containsKey("mudit"));
        assertFalse(myTrie.containsKey("arvind"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsValueNull() {
        Trie<String> myTrie = new Trie<>();
        myTrie.put("mudit", "isa");
        myTrie.containsValue(null);
    }

    @Test
    public void testContainsValue() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        assertTrue(myTrie.containsValue(1));
        assertFalse(myTrie.containsValue(1600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        myTrie.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalidKey() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        myTrie.remove("Hello");
    }

    @Test
    public void testRemoveNotInTrie() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        myTrie.remove("goat");
    }

    @Test
    public void testRemove() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        assertEquals((Integer) 1, myTrie.remove("mudit"));
    }

    @Test
    public void testRemoveNotSuffixes() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("mudi", 2);
        myTrie.put("mud" , 3);
        myTrie.put("arvind", 1210);
        assertEquals((Integer) 1, myTrie.remove("mudit"));
        assertTrue(myTrie.containsKey("mudi"));
        assertTrue(myTrie.containsKey("mud"));
    }

    @Test
    public void testClear() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        assertEquals(3, myTrie.size());
        myTrie.clear();
        assertEquals(0, myTrie.size());
    }

    @Test
    public void testIsEmpty() {
        Trie<Integer> myTrie = new Trie<>();
        assertTrue(myTrie.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullCountPrefix() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        myTrie.countPrefixes(null);
    }

    @Test
    public void testCountPrefixSimple() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("ap", 1);
        assertEquals(1, myTrie.countPrefixes("a"));
    }

    @Test
    public void testCountPrefixNotPresent() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("banana", 1);
        myTrie.put("banan", 1);
        myTrie.put("ban", 1);
        myTrie.put("apple",2);
        myTrie.put("app", 3);
        myTrie.put("oatmeal", 4);
        myTrie.put("arvind", 1210);
        assertEquals(0, myTrie.countPrefixes("canada"));
    }

    @Test
    public void testCountPrefixComplex() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("banana", 1);
        myTrie.put("banan", 1);
        myTrie.put("ban", 1);
        myTrie.put("apple",2);
        myTrie.put("app", 3);
        myTrie.put("oatmeal", 4);
        myTrie.put("arvind", 1210);
        assertEquals(3, myTrie.countPrefixes("ba"));
        assertEquals(3, myTrie.countPrefixes("a"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAllValuesWithPrefix() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("mudit", 1);
        myTrie.put("isa", 2);
        myTrie.put("arvind", 1210);
        myTrie.allValuesWithPrefix(null);
    }

    @Test
    public void testAllValuesWithPrefixNotPresent() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("banana", 1);
        myTrie.put("banan", 1);
        myTrie.put("ban", 1);
        myTrie.put("apple",2);
        myTrie.put("app", 3);
        myTrie.put("oatmeal", 4);
        myTrie.put("arvind", 1210);
        assertEquals(Collections.emptyList(), myTrie.allValuesWithPrefix("canada"));
    }

    @Test
    public void testAllValuesWithPrefix() {
        Trie<Integer> myTrie = new Trie<>();
        myTrie.put("banana", 1);
        myTrie.put("banan", 9);
        myTrie.put("ban", 7);
        myTrie.put("apple",2);
        myTrie.put("app", 3);
        myTrie.put("oatmeal", 4);
        myTrie.put("arvind", 1210);
        myTrie.put("antarctica", 1210);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(7);
        expected.add(9);
        expected.add(1);
        assertEquals(expected, myTrie.allValuesWithPrefix("ba"));
        ArrayList<Integer> expectedTwo = new ArrayList<Integer>();
        expectedTwo.add(1210);
        expectedTwo.add(3);
        expectedTwo.add(2);
        expectedTwo.add(1210);
        assertEquals(expectedTwo, myTrie.allValuesWithPrefix("a"));
    }
}
