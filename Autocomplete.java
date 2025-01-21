import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Autocomplete implements IAutocomplete {
    int numberOfSuggestions;
    private Trie<ITerm> myTrie;

    public Autocomplete() {
        myTrie = new Trie<>();
    }

    /**
     * Adds a new word with its associated weight to the Trie
     *
     * @param word the word to be added to the Trie
     * @param weight the weight of the word
     */
    @Override
    public void addWord(String word, long weight) {
        ITerm term = new Term(word, weight);
        myTrie.put(word, term);
    }

    /**
     * Initializes the Trie
     *
     * @param filename the file to read all the autocomplete data from each line
     *                 contains a word and its weight This method will call the
     *                 addWord method
     * @param k the maximum number of suggestions that should be displayed
     */
    @Override
    public void buildTrie(String filename, int k) {
        numberOfSuggestions = k;

        try {
            BufferedReader inFile = new BufferedReader(new FileReader(filename));
            String currLine = inFile.readLine();
            while ((currLine = inFile.readLine()) != null) {
                String[] line = currLine.trim().split("\t");
                if (line.length == 2) {
                    addWord(line[1].toLowerCase(), Long.parseLong(line[0]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return k the maximum number of suggestions that should be displayed
     */
    @Override
    public int numberSuggestions() {
        return numberOfSuggestions;
    }

    /**
     * @param prefix a string that can contain any character
     * @return the number of words that start with prefix.
     * @throws IllegalArgumentException if the specified prefix is null or if the prefix contains
     * characters that are not lowercase letters
     */
    @Override
    public int countPrefixes(String prefix) {

        return myTrie.countPrefixes(prefix);
    }

    /**
     * @param prefix a string that can contain any character
     * @return a List containing all the ITerm objects with query starting with
     *         prefix. Return an empty list if there are no ITerm object starting
     *         with prefix.
     * @throws IllegalArgumentException if the specified prefix is null or if the prefix contains
     * characters that are not lowercase letters
     */
    @Override
    public List<ITerm> getSuggestions(String prefix) {
        return myTrie.allValuesWithPrefix(prefix);
    }
}
