import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class AutocompleteTest {


    @Test
    public void testAutocompleteCountPrefixes() {
        Autocomplete myAutocomplete = new Autocomplete();
        myAutocomplete.addWord("charizard", 100);
        myAutocomplete.addWord("charmander", 50);
        myAutocomplete.addWord("charmeleon", 25);
        myAutocomplete.addWord("blastoise", 50);
        myAutocomplete.addWord("squirtle", 25);
        myAutocomplete.addWord("bulbasaur", 5);
        assertEquals(3, myAutocomplete.countPrefixes("char"));
    }

    @Test
    public void testAutocompleteCountPrefixesNotPresent() {
        Autocomplete myAutocomplete = new Autocomplete();
        myAutocomplete.addWord("charizard", 100);
        myAutocomplete.addWord("charmander", 50);
        myAutocomplete.addWord("charmeleon", 25);
        myAutocomplete.addWord("blastoise", 50);
        myAutocomplete.addWord("squirtle", 25);
        myAutocomplete.addWord("bulbasaur", 5);
        assertEquals(0, myAutocomplete.countPrefixes("bhar"));
    }

    @Test
    public void testAutocompleteGetSuggestions() {
        Autocomplete myAutocomplete = new Autocomplete();
        myAutocomplete.addWord("charizard", 100);
        myAutocomplete.addWord("charmander", 50);
        myAutocomplete.addWord("charmeleon", 25);
        myAutocomplete.addWord("blastoise", 50);
        myAutocomplete.addWord("squirtle", 25);
        myAutocomplete.addWord("bulbasaur", 5);
        List<ITerm> results = myAutocomplete.getSuggestions("char");
        assertTrue(results.toString().contains("charizard"));
        assertTrue(results.toString().contains("charmeleon"));
        assertTrue(results.toString().contains("charmander"));
    }

    @Test
    public void testAutocompleteAddWord() {
        Autocomplete myAutocomplete = new Autocomplete();
        myAutocomplete.addWord("charizard", 100);
        myAutocomplete.addWord("charmander", 50);
        myAutocomplete.addWord("charmeleon", 25);
        myAutocomplete.addWord("blastoise", 50);
        myAutocomplete.addWord("squirtle", 25);
        myAutocomplete.addWord("bulbasaur", 5);
        myAutocomplete.addWord("bharizard", 20);
        assertEquals(1, myAutocomplete.countPrefixes("bhar"));
        List<ITerm> results = myAutocomplete.getSuggestions("bhar");
        assertTrue(results.toString().contains("bharizard"));

    }


    @Test
    public void testITermByReverseWeightOrder() {
        ITerm myTermOne = new Term("charizard", 100);
        ITerm myTermTwo = new Term("charmander", 50);
        ITerm myTermThree = new Term("charmeleon", 25);
        List<ITerm> myTerms = new ArrayList<>();
        myTerms.add(myTermOne);
        myTerms.add(myTermTwo);
        myTerms.add(myTermThree);
        myTerms.sort(ITerm.byReverseWeightOrder());
        assertEquals("100\tcharizard", myTerms.get(0).toString());
        assertEquals("50\tcharmander", myTerms.get(1).toString());
        assertEquals("25\tcharmeleon", myTerms.get(2).toString());
    }

}
