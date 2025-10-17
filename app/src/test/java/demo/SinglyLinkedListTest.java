package demo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    // ---------- helpers ----------
    private static SinglyLinkedList<String> listOf(String... vals) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        for (String v : vals) list.addLast(v);
        return list;
    }

    /** Snapshot elements into a Java List without permanently mutating the SLL. */
    private static <T> List<T> snapshot(SinglyLinkedList<T> list) {
        List<T> out = new ArrayList<>();
        List<T> tmp = new ArrayList<>();
        // drain
        while (!list.isEmpty()) {
            T val = list.removeFirst();
            out.add(val);
            tmp.add(val);
        }
        // restore
        for (T v : tmp) list.addLast(v);
        return out;
    }

    private static void assertSizeBoth(SinglyLinkedList<?> list, int expected) {
        assertEquals(expected, list.size(), "size() mismatch");
        assertEquals(expected, list.size2(), "size2() mismatch");
    }

    // ============ remove(Object o) ============

    @Test
    void removeObject_empty_returnsFalse() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        assertFalse(list.remove("X"));
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeObject_singleton_match_becomesEmpty_updatesTail() {
        SinglyLinkedList<String> list = listOf("A");
        assertTrue(list.remove("A"));
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeObject_singleton_noMatch_noChange() {
        SinglyLinkedList<String> list = listOf("A");
        assertFalse(list.remove("B"));
        assertEquals("A", list.first());
        assertEquals("A", list.last());
        assertSizeBoth(list, 1);
    }

    @Test
    void removeObject_head_middle_tail() {
        SinglyLinkedList<String> list = listOf("A", "B", "C", "D");
        assertTrue(list.remove("A")); // head
        assertEquals(List.of("B", "C", "D"), snapshot(list));
        assertTrue(list.remove("C")); // middle
        assertEquals(List.of("B", "D"), snapshot(list));
        assertTrue(list.remove("D")); // tail
        assertEquals(List.of("B"), snapshot(list));
        assertSizeBoth(list, 1);
        assertEquals("B", list.first());
        assertEquals("B", list.last());
    }

    @Test
    void removeObject_twoElements_removeTail_thenHead() {
        SinglyLinkedList<String> list = listOf("X", "Y");
        assertTrue(list.remove("Y")); // remove tail
        assertEquals("X", list.first());
        assertEquals("X", list.last());
        assertTrue(list.remove("X")); // now remove head
        assertTrue(list.isEmpty());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeObject_duplicates_removeFirstOnly_eachTime() {
        SinglyLinkedList<String> list = listOf("A", "B", "A", "A", "C");
        assertTrue(list.remove("A")); // first A
        assertEquals(List.of("B", "A", "A", "C"), snapshot(list));
        assertTrue(list.remove("A")); // next first A
        assertEquals(List.of("B", "A", "C"), snapshot(list));
        assertTrue(list.remove("A")); // last A
        assertEquals(List.of("B", "C"), snapshot(list));
        assertFalse(list.remove("A")); // none left
        assertEquals(List.of("B", "C"), snapshot(list));
        assertSizeBoth(list, 2);
    }

    @Test
    void removeObject_allSameValues() {
        SinglyLinkedList<String> list = listOf("K", "K", "K", "K");
        assertTrue(list.remove("K"));
        assertEquals(List.of("K", "K", "K"), snapshot(list));
        assertTrue(list.remove("K"));
        assertEquals(List.of("K", "K"), snapshot(list));
        assertTrue(list.remove("K"));
        assertEquals(List.of("K"), snapshot(list));
        assertTrue(list.remove("K"));
        assertTrue(list.isEmpty());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeObject_mixedSequence_maintainsTailCorrectly() {
        SinglyLinkedList<String> list = listOf("A", "B", "C", "D", "E");
        assertTrue(list.remove("E")); // tail
        assertEquals("D", list.last());
        assertTrue(list.remove("A")); // head
        assertEquals("B", list.first());
        assertTrue(list.remove("C")); // middle
        assertEquals(List.of("B", "D"), snapshot(list));
        list.addLast("Z");
        assertEquals("Z", list.last()); // tail updated
        assertSizeBoth(list, 3);
    }

    // ============ remove(int index) ============

    @Test
    void removeIndex_onEmpty_throws() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void removeIndex_bounds_lowHigh_throw() {
        SinglyLinkedList<String> list = listOf("A", "B");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2)); // valid 0..1
        assertEquals(List.of("A", "B"), snapshot(list));
        assertSizeBoth(list, 2);
    }

    @Test
    void removeIndex_head_zero() {
        SinglyLinkedList<String> list = listOf("A", "B", "C");
        assertEquals("A", list.remove(0));
        assertEquals(List.of("B", "C"), snapshot(list));
        assertEquals("B", list.first());
        assertEquals("C", list.last());
        assertSizeBoth(list, 2);
    }

    @Test
    void removeIndex_middle_updatesLinks() {
        SinglyLinkedList<String> list = listOf("A", "B", "C", "D");
        assertEquals("C", list.remove(2));
        assertEquals(List.of("A", "B", "D"), snapshot(list));
        // Now removing index 1 should remove B, leaving [A, D]
        assertEquals("B", list.remove(1));
        assertEquals(List.of("A", "D"), snapshot(list));
        assertEquals("A", list.first());
        assertEquals("D", list.last());
        assertSizeBoth(list, 2);
    }

    @Test
    void removeIndex_tail_lastIndex_updatesTail() {
        SinglyLinkedList<String> list = listOf("A", "B", "C");
        assertEquals("C", list.remove(2));
        assertEquals("B", list.last());
        assertEquals(List.of("A", "B"), snapshot(list));
        assertSizeBoth(list, 2);
    }

    @Test
    void removeIndex_singleton_toEmpty_updatesTail() {
        SinglyLinkedList<String> list = listOf("X");
        assertEquals("X", list.remove(0));
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeIndex_removeAllFromFront() {
        SinglyLinkedList<String> list = listOf("A", "B", "C", "D");
        assertEquals("A", list.remove(0));
        assertEquals("B", list.remove(0));
        assertEquals("C", list.remove(0));
        assertEquals("D", list.remove(0));
        assertTrue(list.isEmpty());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeIndex_removeAllFromBack() {
        SinglyLinkedList<String> list = listOf("A", "B", "C", "D");
        assertEquals("D", list.remove(3));
        assertEquals("C", list.remove(2));
        assertEquals("B", list.remove(1));
        assertEquals("A", list.remove(0));
        assertTrue(list.isEmpty());
        assertSizeBoth(list, 0);
    }

    @Test
    void removeIndex_interleavedWithAdds_tailIntegrity() {
        SinglyLinkedList<String> list = listOf("A", "B", "C");
        assertEquals("B", list.remove(1));        // [A, C]
        list.addLast("D");                        // [A, C, D]
        assertEquals("D", list.last());
        assertEquals("C", list.remove(1));        // [A, D]
        assertEquals("D", list.last());
        list.addLast("E");                        // [A, D, E]
        assertEquals("E", list.last());
        assertEquals(List.of("A", "D", "E"), snapshot(list));
        assertSizeBoth(list, 3);
    }

    @Test
    void removeIndex_manySequentialOperations() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        // build 0..9
        for (int i = 0; i < 10; i++) list.addLast(i);
        assertSizeBoth(list, 10);
        int idx = 0; // next even is always at this moving index
        for (int i = 0; i < 5; i++) {
            Integer removed = list.remove(idx); // removes 0,2,4,6,8
            assertEquals(i * 2, removed);
            idx++; // after removing one, the next even shifts into this index
        }
        assertEquals(List.of(1, 3, 5, 7, 9), snapshot(list));
        // remove last repeatedly
        assertEquals(9, list.remove(4));
        assertEquals(7, list.remove(3));
        assertEquals(5, list.remove(2));
        assertEquals(3, list.remove(1));
        assertEquals(1, list.remove(0));
        assertTrue(list.isEmpty());
        assertSizeBoth(list, 0);
    }

    // ============ combined behaviors / regression checks ============

    @Test
    void removeObject_thenIndex_thenObject_untilEmpty() {
        SinglyLinkedList<String> list = listOf("A", "B", "C", "D", "E");
        assertTrue(list.remove("A"));        // [B, C, D, E]
        assertEquals("D", list.remove(2));   // [B, C, E]
        assertTrue(list.remove("E"));        // [B, C]
        assertEquals("B", list.remove(0));   // [C]
        assertTrue(list.remove("C"));        // []
        assertTrue(list.isEmpty());
        assertSizeBoth(list, 0);
        // add again to ensure links reset correctly
        list.addLast("X");
        list.addLast("Y");
        assertEquals(List.of("X", "Y"), snapshot(list));
        assertEquals("X", list.first());
        assertEquals("Y", list.last());
        assertSizeBoth(list, 2);
    }

    @Test
    void removeObject_valueNotPresent_doesNotAffectIndices() {
        SinglyLinkedList<String> list = listOf("P", "Q", "R");
        assertFalse(list.remove("Z"));
        assertEquals("Q", list.remove(1));  // still removes original index 1
        assertEquals(List.of("P", "R"), snapshot(list));
        assertSizeBoth(list, 2);
    }
}
