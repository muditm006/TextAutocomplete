# 🚀 Java Autocomplete Tool

A **high-performance Java-based autocomplete system** that utilizes a **custom-built Trie** and an **optimized HashMap** to efficiently generate word suggestions from a user-inputted word bank. This tool is designed for blazing-fast performance, handling large datasets with ease, and providing accurate and scalable autocomplete suggestions.

---

## ✨ Features

- ⚡ **Fast and Efficient Autocompletion**  
  Combines the power of a custom Trie and HashMap structure to deliver lightning-fast word suggestions.  

- 📚 **Handles Large Datasets**  
  Processes massive datasets with exceptional speed and accuracy, making it ideal for real-world applications.  

- 🔧 **Scalable Performance**  
  Extensively tested on both words and numerical datasets to ensure reliability across various use cases.  

---

## 📊 Performance Benchmarks

- ✅ **Processed 350,000 words** from a dictionary in **under 1/10 of a second**.  
- ✅ **Processed 8,000 phone numbers** in just **450 milliseconds**.  

This demonstrates the tool’s ability to handle large-scale data efficiently while maintaining high accuracy.

---

## 🗂️ File Descriptions

Here’s a breakdown of the repository files:

- **`Autocomplete.java`**  
  Implements the main logic for the autocomplete system, combining the Trie and HashMap structures to provide fast and accurate word suggestions.

- **`AutocompleteTest.java`**  
  Contains unit tests for validating the functionality and performance of the `Autocomplete` class.

- **`HashMap.java`**  
  A custom implementation of a HashMap optimized specifically for use in the autocomplete system.

- **`HashMapTest.java`**  
  Includes test cases to ensure the correctness and efficiency of the custom `HashMap` implementation.

- **`ITerm.java`**  
  Defines an interface for terms used in the autocomplete system, specifying methods like comparison and retrieval of term weights.

- **`Trie.java`**  
  Implements a Trie data structure tailored for efficient word storage and retrieval in the autocomplete system.

- **`TrieTest.java`**  
  Provides unit tests to verify the correctness and performance of the `Trie` implementation.

---

## 🛠️ How to Use

Follow these steps to use this project:

1. Clone this repository to your local machine:  
git clone https://github.com/muditm006/TextAutocomplete.git
cd TextAutocomplete
2. Compile all `.java` files using your preferred Java compiler:  
javac *.java
3. Run the `AutocompleteTest.java`, `HashMapTest.java`, or `TrieTest.java` files to test individual components:
java AutocompleteTest

4. Integrate `Autocomplete.java` into your application by providing it with a dataset (e.g., words or numbers) as input. Use its methods to retrieve autocomplete suggestions based on user queries.

---

## 📝 Notes

- This project is designed to be modular, allowing you to extend or modify components like the Trie or HashMap as needed.
- The performance benchmarks were achieved on a modern machine; results may vary depending on your hardware.
- Ensure that your dataset is properly formatted before feeding it into the system for optimal results.
- Unit tests are included (`AutocompleteTest`, `HashMapTest`, `TrieTest`) to help you validate functionality after making changes.

---

## 🌟 Why Use This Tool?

This project is perfect for developers building applications requiring fast autocomplete functionality, such as:
- Search engines 🔍
- Chatbots 🤖
- Data entry tools 📝
- Predictive text systems 📱

With its hybrid design combining Tries and HashMaps, this tool ensures both speed and accuracy, even with large datasets.

---

## 💡 Future Improvements

Some potential enhancements include:
1. Adding support for fuzzy matching (e.g., handling typos).  
2. Extending support for multilingual datasets.  
3. Providing an API wrapper for easier integration into web or mobile applications.
