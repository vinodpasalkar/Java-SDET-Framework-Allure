package com.nageshg.examples; // Java interview examples package.
import java.util.*; // Imports collection and array utilities.

public class JavaCodingExamples { // Contains common coding interview methods.
    public static String reverse(String text) { // Reverses a string manually.
        StringBuilder out = new StringBuilder(); // Creates a mutable result buffer.
        for (int i = text.length() - 1; i >= 0; i--) out.append(text.charAt(i)); // Appends characters from end to start.
        return out.toString(); // Returns the reversed text.
    } // Ends reverse.
    public static boolean palindrome(String text) { // Checks whether text reads the same backwards.
        String clean = text.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); // Removes punctuation and normalizes case.
        return clean.equals(reverse(clean)); // Compares normalized text with its reverse.
    } // Ends palindrome.
    public static Set<Character> duplicates(String text) { // Finds repeated characters.
        Set<Character> seen = new HashSet<>(); // Tracks characters already visited.
        Set<Character> repeated = new HashSet<>(); // Stores duplicate characters.
        for (char c : text.toCharArray()) if (!seen.add(c)) repeated.add(c); // Records characters already encountered.
        return repeated; // Returns unique duplicates.
    } // Ends duplicates.
    public static int secondLargest(int[] values) { // Finds the second-largest distinct number.
        return Arrays.stream(values).distinct().boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElseThrow(); // Sorts distinct numbers descending and selects the second.
    } // Ends secondLargest.
    public static Character firstUnique(String text) { // Finds the first non-repeating character.
        Map<Character, Integer> counts = new LinkedHashMap<>(); // Preserves first-seen order.
        for (char c : text.toCharArray()) counts.put(c, counts.getOrDefault(c, 0) + 1); // Counts each character.
        return counts.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).findFirst().orElse(null); // Returns first count-one character or null.
    } // Ends firstUnique.
} // Ends JavaCodingExamples.
