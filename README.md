# Java Autocomplete Tool

A high-performance Java-based autocomplete system utilizing a **custom-built Trie** and an **optimized HashMap** to efficiently generate word suggestions from a user-inputted word bank.

## Features

- **Fast and Efficient Autocompletion** using a hybrid Trie and HashMap structure  
- **Handles Large Datasets** with exceptional speed and accuracy  
- **Scalable Performance** tested on both words and numerical datasets  

## Performance Benchmarks

- **Processed 350,000 words** from a dictionary **in under 1/10 of a second**  
- **Processed 8,000 phone numbers** **in just 450 milliseconds**  

## File Descriptions

- **Autocomplete.java**  
  Implements the main logic for the autocomplete system, combining the Trie and HashMap structures to provide fast and accurate word suggestions.

- **AutocompleteTest.java**  
  Contains unit tests for validating the functionality and performance of the `Autocomplete` class.

- **HashMap.java**  
  A custom implementation of a HashMap optimized for use in the autocomplete system.

- **HashMapTest.java**  
  Includes test cases to ensure the correctness and efficiency of the custom `HashMap` implementation.

- **ITerm.java**  
  Defines an interface for terms used in the autocomplete system, specifying methods like comparison and retrieval of term weights.

- **Trie.java**  
  Implements a Trie data structure tailored for efficient word storage and retrieval in the autocomplete system.

- **TrieTest.java**  
  Provides unit tests to verify the correctness and performance of the `Trie` implementation.
