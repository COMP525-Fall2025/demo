package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Lab6 {
    /**
     * 1) Finds the missing number in the range 1..N given an array that contains
     * all numbers in that range except one.
     * Returns -1 if no missing numbers.
     *
     * Example:
     * int[] nums = {1, 2, 4, 5}; int N = 5;
     * missingNumber(nums, N) -> 3
     */
    public static int missingNumber(int[] nums, int N) {
        // you fill in here
        return -1;
    }

    /**
     * 2) Most Frequent Count (String[] -> int)
     * Returns the highest number of occurrences of any word in the array.
     *
     * Example:
     * String[] words = {"apple","banana","apple","orange","banana","apple"};
     * mostFrequentCount(words) -> 3 // "apple" appears 3 times
     *
     * Notes:
     * - Returns 0 for an empty input array. Case-sensitive match
     */
    public static int mostFrequentCount(String[] words) {
        // you fill in here
        return 0;
    }

    /**
     * 3) Common Elements (UNIQUE COUNT)
     * Return the number of distinct values that appear in BOTH arrays.
     * Example:
     * a = {1,2,3,4}, b = {3,4,5,6} -> intersectionUniqueCount(a,b) == 2 // {3,4}
     * a = {1,1,2,2}, b = {2,2,2} -> intersectionUniqueCount(a,b) == 1 // {2}
     */
    public static int intersectionUniqueCount(int[] a, int[] b) {
        // you fill in here
        return 0;
    }

    /**
     * 4) Find Duplicates (int[]) -> int[] of values occurring > 1 (order arbitrary)
     * First counts each number's frequency, then collects keys whose count > 1.
     * Example:
     * int[] nums = {1,2,3,2,4,5,5};
     * findDuplicates(nums) -> [2, 5] // order may vary
     */
    public static int[] findDuplicates(int[] nums) {
        // you fill in here
        return null;
    }

    // --- helpers for demo output ---
    private static void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        // ========== 1) Missing Number ==========
        System.out.println("Missing number: " + missingNumber(new int[] { 1, 2, 4, 5 }, 5)); // 3
        System.out.println("Missing number: " + missingNumber(new int[] { 2, 3, 4, 5, 6 }, 6)); // 1

        // ========== 2) Word Counting ==========
        String[] words = { "apple", "banana", "apple", "orange", "banana", "apple" };
        int wordFreq = mostFrequentCount(words);
        System.out.println("most frequent count: " + wordFreq); // 3


        // ========== 3) Intersection UNIQUE COUNT ==========
        System.out.println("Unique intersection count ( {1,2,3,4} ∩ {3,4,5,6} ): " +
                intersectionUniqueCount(new int[] { 1, 2, 3, 4 }, new int[] { 3, 4, 5, 6 })); // 2
        System.out.println("Unique intersection count ( {1,1,2,2} ∩ {2,2,2} ): " +
                intersectionUniqueCount(new int[] { 1, 1, 2, 2 }, new int[] { 2, 2, 2 })); // 1
        System.out.println("Unique intersection count ( {1,2} ∩ {3,4} ): " +
                intersectionUniqueCount(new int[] { 1, 2 }, new int[] { 3, 4 })); // 0

        // ========== 4) Find Duplicates ==========
        int[] dups = findDuplicates(new int[] { 1, 2, 3, 2, 4, 5, 5 });
        System.out.print("Duplicates: ");
        printArray(dups); // e.g., [2, 5] (order arbitrary)

    }
}
