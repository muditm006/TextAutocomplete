import java.util.*;

/**
 * <p>
 * There are two main variables to keep in mind regarding a running time for a trie implementation:
 * <dl> <dt>{@code N}</dt> <dd>the number of nodes in the trie</dd> <dt>{@code H}</dt> <dd>the
 * height of the trie (length of the longest key)</dd> </dl>
 * <p>
 * Note that N is bounded by H * (# of keys/values in the mapping), but it will typically be
 * significantly smaller due to key overlap.
 * <p>
 * Keys are of type {@link CharSequence}. This allows the implementation to make assumptions as to
 * how data is stored, since we cannot break a key down into individual characters if the key is
 * not made up of characters to begin with. For simplicity, keys must consist entirely of lowercase
 * letters.
 * <p>
 * Null keys are not permitted because keys correspond directly to paths in a trie. Null values are
 * not permitted because rather than using a sentinel node, the implementation uses null to
 * indicate that a node does not have an associated value.
 *
 * @param <V> the type of mapped values
 */
public class Trie<V> implements ITrie<V> {

    /**
     * The size of our key alphabet or character set. Here, we use 26 for the standard lowercase
     * alphabet. We might like to be more flexible and support full alphanumeric or even full ASCII
     * but that would increase our overhead. Since we know something about our use case, we can
     * stick to the lowercase alphabet and keep our overhead down.
     */
    private static final int BRANCH_FACTOR = 26;

    /**
     * The root node of the trie.
     */
    private Node<V> root;

    /**
     * Returns the root node that represents the Trie
     * Provides the visualizer access to the root
     */
    public Node<V> getRoot() {
        return root;
    }

    /**
     * The size of the trie.
     */
    private int size;

    /**
     * Constructs an empty Trie.
     */
    public Trie() {
        root = new Node<>(null);
    }

    /**
     * Converts a {@code char} into an array index.
     * <p>
     * Effectively maps {@code a -> 0, b -> 1, ..., z -> 25}.
     *
     * @param c the character
     * @return the array index corresponding to the specified character
     * @throws IllegalArgumentException if the specified character is not valid as an index
     */
    private static int convertToIndex(char c) {
        // we don't test for this, but I'm leaving it here
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Character must be in the range [a..z]");
        }
        return c - 'a';
    }

    /**
     * Converts an array index into a {@code char} in the key.
     * <p>
     * Effectively maps {@code 0 -> a, b -> 1, ..., 25 -> z}.
     *
     * @param i the index
     * @return the character corresponding to the specified array index
     * @throws IllegalArgumentException if the specified index is out of bounds
     */
    private static char convertToChar(int i) {
        if (i < 0 || i >= BRANCH_FACTOR) {
            throw new IllegalArgumentException("Index must be in the range [0..BRANCH_FACTOR]");
        }
        return (char) (i + 'a');
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     * @implSpec This method should run in O(1) time.
     */
    public int size() {
        return size;
    }


    /**
     * Returns true is Trie is empty, false otherwise
     *
     * @return true if trie is empty, false otherwise
     * @implSpec This method should run in O(1) time.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /* NOTE: Please do not modify anything above this line. */

    /**
     * @throws IllegalArgumentException if either the specified key or value is null
     * or if the key contains characters that are not lowercase letters
     * @implSpec This method should run in O(H) time.
     * @implSpec This method should use O(1) space.
     */
    public V put(CharSequence key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            throw new IllegalArgumentException();
        }

        Node currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            Node childNode = currentNode.getChild(currentChar);
            if (childNode == null) {
                childNode = new Node(null);
                currentNode.setChild(currentChar, childNode);
            }
            currentNode = childNode;
        }
        V previousValue = (V) currentNode.getValue();

        if (!currentNode.hasValue()) {
            size = size + 1;
        }
        currentNode.setValue(value);
        return previousValue;
    }

    /**
     * @throws IllegalArgumentException if the specified key is null or if the key contains
     * characters that are not lowercase letters
     * @implSpec This method should run in O(H) time.
     */
    public V get(CharSequence key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            Node childNode = currentNode.getChild(currentChar);
            if (childNode == null) {
                return null;
            }
            currentNode = childNode;
        }
        return (V) currentNode.getValue();
    }

    /**
     * @throws IllegalArgumentException if the specified key is null or if the key contains
     * characters that are not lowercase letters
     * @implSpec This method should run in O(H) time.
     */
    public boolean containsKey(CharSequence key) {
        return get(key) != null;
    }

    /**
     * @throws IllegalArgumentException if the specified value is null
     * @implSpec This method should run in O(N) time.
     */
    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (curr != null) {
                if (curr.hasValue() && curr.getValue().equals(value)) {
                    return true;
                }
                if (curr.hasChildren()) {
                    for (Node child : curr.getChildren()) {
                        if (child != null) {
                            stack.push(child);
                        }
                    }
                }
            }
        }
        return false;
    }




    /**
     * @throws IllegalArgumentException if the specified key is null or if the key contains
     * characters that are not lowercase letters
     * @implSpec This method should run in O(H) time.
     * @implSpec This method should use O(1) space.
     */
    @Override
    public V remove(CharSequence key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        return removeHelper(root, key, 0);
    }

    private V removeHelper(Node<V> currentNode, CharSequence key, int depth) {
        if (currentNode == null) {
            return null;
        }
        if (depth == key.length()) {
            if (!currentNode.hasValue()) {
                return null;
            }
            V removedValue = currentNode.getValue();
            currentNode.setValue(null);
            size--;
            if (!currentNode.hasChildren()) {
                return removedValue;
            }
            return removedValue;
        }
        char currentChar = key.charAt(depth);
        Node<V> childNode = currentNode.getChild(currentChar);
        V removedValue = removeHelper(childNode, key, depth + 1);
        if (childNode != null && !childNode.hasValue() && !childNode.hasChildren()) {
            currentNode.setChild(currentChar, null);
        }
        return removedValue;
    }


    /**
     * @implSpec This method should run in O(1) time.
     */
    public void clear() {
        root = new Node(null);
        size = 0;
    }

    /**
     * The next two methods are for the autocomplete section of the assignment
     */

    /**
     * Returns the number of values whose keys have prefix as a prefix
     * Duplicate values should be double counted (see write up)
     *
     * @param prefix possible prefix of some keys in the trie
     * @return the number of entries whose key has prefix as a prefix
     * @throws IllegalArgumentException if the specified prefix is null or if the prefix contains
     * characters that are not lowercase letters
     * @implSpec This method should run in O(H) time.
     * @implSpec This method should use O(1) space.
     */

    public int countPrefixes(CharSequence prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        Node<V> currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            currentNode = currentNode.getChild(currentChar);
            if (currentNode == null) {
                return 0;
            }
        }
        return countValuesInSubtree(currentNode);
    }

    private int countValuesInSubtree(Node<V> node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.hasValue()) {
            count = 1;
        }
        if (node.hasChildren()) {
            for (Node<V> child : node.getChildren()) {
                if (child != null) {
                    count += countValuesInSubtree(child);
                }
            }
        }
        return count;
    }

    /**
     * Returns a list of values of all entries whose key has prefix as a prefix
     * Duplicate values should not be removed (see write up)
     *
     * @param prefix possible prefix of some keys in the trie
     * @return the value of all entries whose key has prefix as a prefix
     * @throws IllegalArgumentException if the specified prefix is null or if the prefix contains
     * characters that are not lowercase letters
     * @implSpec This method should run in O(N) time.
     * @implSpec This method should use O(N) space.
     */

    @Override
    public List<V> allValuesWithPrefix(CharSequence prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        Node<V> currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            currentNode = currentNode.getChild(currentChar);
            if (currentNode == null) {
                return Collections.emptyList();
            }
        }
        List<V> values = new ArrayList<>();
        collectValues(currentNode, values);
        return values;
    }

    private void collectValues(Node<V> node, List<V> values) {
        if (node == null) {
            return;
        }
        if (node.hasValue()) {
            values.add(node.getValue());
        }
        if (node.hasChildren()) {
            for (Node<V> child : node.getChildren()) {
                collectValues(child, values);
            }
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@link CharSequence} keys of the {@link Map.Entry} instances in the resulting iteration
     * are mutable and should not be referenced directly. Instead, one should call
     * {@link CharSequence#toString()} on the key to get an immutable reference.
     * <p>
     * The iterator must produce entries in lexicographic order. For example, {@code ("party", 99),
     * ("pen", 24), ("penguin", 2), ("q", 17)}
     * <p>
     * This method is for kudos, which is optional. To receive full kudos, your
     * implementation must satisfy the asymptotic complexities for running time and space. An
     * optimal implementation will use space proportional to at most the height of the trie.
     *
     * @implSpec This method should run in O(N) time.
     * @implSpec This method should use O(H) space.
     * @implNote For partial kudos, the space usage of this method can be relaxed to O(V).
     * @implNote You will receive fake kudos if you dump all of the elements of the trie into a
     * collection and return an iterator over that collection.
     */
    public Iterator<Map.Entry<CharSequence, V>> entryIterator() {
        throw new UnsupportedOperationException("TODO: implement");
    }



    /** 
     * Node class for the Trie
     * You may modify this class (see write up)
    */
    
    static class Node<V> {
        
        private V value;
        private Node<V>[] children;


        public Node(V value) {
            this.value = value;
        }

        /**
         * Converts a {@code char} into an array index.
         * <p>
         * Effectively maps {@code a -> 0, b -> 1, ..., z -> 25}.
         *
         * @param c the character
         * @return the array index corresponding to the specified character
         * @throws IllegalArgumentException if the specified character is not valid as an index
         */

        /**
         * Carrier for a value and an array of children.
         */

        @SuppressWarnings("unchecked")
        public void initChildren() {
            this.children = new Node[26];
        }

        /**
         * Returns the children array of the node
         * Used by the visualizer to draw out the trie
         */
        public Node<V>[] getChildren() {
            return children;
        }

        /**
         * Returns {@code true} if this node has child nodes.
         *
         * @return {@code true} if this node has child nodes
         */
        public boolean hasChildren() {
            return children != null && Arrays.stream(children).anyMatch(Objects::nonNull);
        }

        /**
         * Retrieves the child node associated with the specified character, or {@code null} if
         * there is no such child.
         *
         * @param c the character
         * @return the child node for the specified character, or {@code null} if there is no such
         * child
         */
        public Node<V> getChild(char c) {
            if (children == null) {
                return null;
            }
            return children[convertToIndex(c)];
        }

        /**
         * Sets the child node corresponding to the specified character to the specified node.
         *
         * @param c    the character corresponding to the child to set
         * @param node the node to add as a child
         */
        public void setChild(char c, Node<V> node) {
            if (children == null) {
                initChildren();
            }
            children[convertToIndex(c)] = node;
        }

        /**
         * Returns {@code true} if this node has a value.
         *
         * @return {@code true} if this node has a value
         */
        public boolean hasValue() {
            return value != null;
        }

        /**
         * Returns the value at this node.
         *
         * @return the value at this node
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value at this node
         */
        public void setValue(V value) {
            this.value = value;
        }
    }
}
