package demo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class Lab6Test {

    /* -------------------------- Helpers -------------------------- */
    private static Set<Integer> toSet(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr) s.add(x);
        return s;
    }

    /* ===================== 1) missingNumber ====================== */
    @Test void missingInMiddle() { assertEquals(3, Lab6.missingNumber(new int[]{1,2,4,5}, 5)); }
    @Test void missingFirst() { assertEquals(1, Lab6.missingNumber(new int[]{2,3,4,5}, 5)); }
    @Test void missingLast() { assertEquals(5, Lab6.missingNumber(new int[]{1,2,3,4}, 5)); }
    @Test void noMissingReturnMinusOne() { assertEquals(-1, Lab6.missingNumber(new int[]{1,2,3,4,5}, 5)); }
    @Test void emptyNumsN1() { assertEquals(1, Lab6.missingNumber(new int[]{}, 1)); }
    @Test void nZeroReturnsMinusOne() { assertEquals(-1, Lab6.missingNumber(new int[]{}, 0)); }
    @Test void unsortedArray() { assertEquals(3, Lab6.missingNumber(new int[]{5,1,4,2}, 5)); }
    @Test void duplicatesStillFindMissing() { assertEquals(2, Lab6.missingNumber(new int[]{1,1,3}, 3)); }

    /* ===================== 2) mostFrequentCount ================== */
    @Test void emptyArrayReturnsZero() { assertEquals(0, Lab6.mostFrequentCount(new String[]{})); }
    @Test void allUniqueReturnsOne() { assertEquals(1, Lab6.mostFrequentCount(new String[]{"a","b","c"})); }
    @Test void oneWordMostFrequent() {
        String[] w = {"apple","banana","apple","orange","banana","apple"};
        assertEquals(3, Lab6.mostFrequentCount(w));
    }
    @Test void tieGivesMaxCount() { assertEquals(2, Lab6.mostFrequentCount(new String[]{"x","y","x","y"})); }
    @Test void caseSensitive() { assertEquals(2, Lab6.mostFrequentCount(new String[]{"Apple","apple","APPLE","apple"})); }
    @Test void includesEmptyString() { assertEquals(2, Lab6.mostFrequentCount(new String[]{"","","a"})); }
    @Test void unicodeWords() { assertEquals(3, Lab6.mostFrequentCount(new String[]{"你好","こんにちは","你好","안녕","你好"})); }
    @Test void largeRepeats() {
        String[] w = new String[10]; Arrays.fill(w,"z");
        assertEquals(10, Lab6.mostFrequentCount(w));
    }

    /* ================= 3) intersectionUniqueCount ================ */
    @Test void basicIntersection() { assertEquals(2, Lab6.intersectionUniqueCount(new int[]{1,2,3,4}, new int[]{3,4,5,6})); }
    @Test void duplicatesInAOnlyOnce() { assertEquals(2, Lab6.intersectionUniqueCount(new int[]{1,1,2,2,3}, new int[]{2,3,7})); }
    @Test void duplicatesInBOnlyOnce() { assertEquals(2, Lab6.intersectionUniqueCount(new int[]{1,2,3}, new int[]{2,2,2,3,3})); }
    @Test void noIntersection() { assertEquals(0, Lab6.intersectionUniqueCount(new int[]{1,2}, new int[]{3,4})); }
    @Test void allCommonUnique() { assertEquals(3, Lab6.intersectionUniqueCount(new int[]{5,6,7}, new int[]{7,6,5})); }
    @Test void negativesAndZero() { assertEquals(2, Lab6.intersectionUniqueCount(new int[]{-1,0,1,2}, new int[]{0,2,4})); }
    @Test void oneEmptyArray() { assertEquals(0, Lab6.intersectionUniqueCount(new int[]{}, new int[]{1,2,3})); }
    @Test void bothEmpty() { assertEquals(0, Lab6.intersectionUniqueCount(new int[]{}, new int[]{})); }

    /* ====================== 4) findDuplicates ==================== */
    @Test void emptyInputGivesEmpty() { assertEquals(Set.of(), toSet(Lab6.findDuplicates(new int[]{}))); }
    @Test void noDuplicates() { assertEquals(Set.of(), toSet(Lab6.findDuplicates(new int[]{1,2,3,4}))); }
    @Test void simpleDuplicates() {
        Set<Integer> exp = Set.of(2,5);
        assertEquals(exp, toSet(Lab6.findDuplicates(new int[]{1,2,3,2,4,5,5})));
    }
    @Test void allElementsDuplicated() {
        Set<Integer> exp = Set.of(7,8,9);
        assertEquals(exp, toSet(Lab6.findDuplicates(new int[]{7,7,8,8,9,9})));
    }
    @Test void moreThanTwoOccurrences() {
        Set<Integer> exp = Set.of(1);
        assertEquals(exp, toSet(Lab6.findDuplicates(new int[]{1,1,1,2,3})));
    }
    @Test void negativesAndZeros() {
        Set<Integer> exp = Set.of(0,-1);
        assertEquals(exp, toSet(Lab6.findDuplicates(new int[]{0,-1,-1,-2,3,0})));
    }
    @Test void singleElementArray() {
        assertEquals(Set.of(), toSet(Lab6.findDuplicates(new int[]{42})));
    }
    @Test void multipleDifferentDuplicates() {
        Set<Integer> exp = Set.of(2,6,7);
        assertEquals(exp, toSet(Lab6.findDuplicates(new int[]{2,3,2,4,5,6,6,6,7,7})));
    }
}
