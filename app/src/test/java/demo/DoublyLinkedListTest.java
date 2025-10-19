package demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    // ---------- helpers ----------
    private static DoublyLinkedList<String> listOf(String... vals) {
        DoublyLinkedList<String> dl = new DoublyLinkedList<>();
        for (String v : vals) dl.addLast(v);
        return dl;
    }

    /** Snapshot elements by drain+restore (no iterator API available). */
    private static <T> List<T> snapshot(DoublyLinkedList<T> dl) {
        List<T> out = new ArrayList<>();
        List<T> restore = new ArrayList<>();
        while (!dl.isEmpty()) {
            T v = dl.removeFirst();
            out.add(v);
            restore.add(v);
        }
        for (T v : restore) dl.addLast(v);
        return out;
    }

    private static void assertSize(DoublyLinkedList<?> dl, int expected) {
        assertEquals(expected, dl.size(), "size mismatch");
        assertEquals(expected == 0, dl.isEmpty(), "isEmpty mismatch");
    }

    // ===================== remove(E e) =====================

    @Test
    void remove_onEmpty_returnsFalse() {
        DoublyLinkedList<String> dl = new DoublyLinkedList<>();
        assertFalse(dl.remove("X"));
        assertNull(dl.first());
        assertNull(dl.last());
        assertSize(dl, 0);
    }

    @Test
    void remove_singleton_matchAndNoMatch() {
        DoublyLinkedList<String> dl = listOf("A");
        assertTrue(dl.remove("A"));
        assertTrue(dl.isEmpty());
        assertFalse(dl.remove("A")); // already gone
        assertSize(dl, 0);
    }

    @Test
    void remove_head_middle_tail_firstOnly() {
        DoublyLinkedList<String> dl = listOf("A", "B", "C", "D");
        assertTrue(dl.remove("A"));                    // head
        assertEquals(List.of("B", "C", "D"), snapshot(dl));
        assertTrue(dl.remove("C"));                    // middle
        assertEquals(List.of("B", "D"), snapshot(dl));
        assertTrue(dl.remove("D"));                    // tail
        assertEquals(List.of("B"), snapshot(dl));
        assertSize(dl, 1);
    }

    // ===================== remove(int index) =====================

    @Test
    void removeIndex_oob_doesNotMutate_andUsesFinalAlias() {
        DoublyLinkedList<String> dl = listOf("A", "B", "C");
        List<String> before = snapshot(dl);
        DoublyLinkedList<String> dlCopy = listOf("A", "B", "C");
        final DoublyLinkedList<String> ref = dlCopy;   // final alias for lambdas

        assertThrows(IndexOutOfBoundsException.class, () -> ref.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> ref.remove(3)); // valid indices 0..2

        assertEquals(before, snapshot(ref));
        assertSize(ref, 3);
    }

    @Test
    void removeIndex_head_middle_tail_andSingleton() {
        DoublyLinkedList<String> dl = listOf("A", "B", "C");
        assertEquals("A", dl.remove(0));               // head
        assertEquals(List.of("B", "C"), snapshot(dl));

        dl = listOf("A", "B", "C", "D");
        assertEquals("C", dl.remove(2));               // middle
        assertEquals(List.of("A", "B", "D"), snapshot(dl));

        dl = listOf("A", "B", "C");
        assertEquals("C", dl.remove(2));               // tail
        assertEquals(List.of("A", "B"), snapshot(dl));

        dl = listOf("X");
        assertEquals("X", dl.remove(0));               // singleton → empty
        assertTrue(dl.isEmpty());
        assertNull(dl.first());
        assertNull(dl.last());
    }

    @Test
    void removeIndex_removeAllByTailThenHead() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        for (int i = 0; i < 5; i++) dl.addLast(i);     // [0,1,2,3,4]
        assertEquals(4, dl.remove(4));
        assertEquals(3, dl.remove(3));
        assertEquals(2, dl.remove(2));
        assertEquals(1, dl.remove(1));
        assertEquals(0, dl.remove(0));
        assertTrue(dl.isEmpty());
        assertSize(dl, 0);
    }

    // ===================== removeAll(E e) =====================

    @Test
    void removeAll_noMatches_returnsZero_andNoChange() {
        DoublyLinkedList<String> dl = listOf("A", "B", "C");
        assertEquals(0, dl.removeAll("Z"));
        assertEquals(List.of("A", "B", "C"), snapshot(dl));
        assertSize(dl, 3);
    }

    @Test
    void removeAll_allOccurrences_atEndsAndMiddle() {
        DoublyLinkedList<String> dl = listOf("X", "A", "B", "X", "C", "X");
        assertEquals(3, dl.removeAll("X"));
        assertEquals(List.of("A", "B", "C"), snapshot(dl));
        assertEquals("A", dl.first());
        assertEquals("C", dl.last());
        assertSize(dl, 3);
    }

    @Test
    void removeAll_multipleNulls_supported() {
        DoublyLinkedList<String> dl = listOf(null, null, "A", null);
        int removed = dl.removeAll(null);
        assertEquals(3, removed);
        assertEquals(List.of("A"), snapshot(dl));
        assertSize(dl, 1);
    }

    // ===================== tiny regressions =====================

    @Test
    void singleNullSnapshot_noVarargsWarning() {
        DoublyLinkedList<String> dl = listOf((String) null);
        assertEquals(Collections.singletonList(null), snapshot(dl));
        assertSize(dl, 1);
    }

    @Test
    void mixedSequence_integrity() {
        DoublyLinkedList<String> dl = listOf("A", "X", "B", "X", "C");
        assertEquals(2, dl.removeAll("X"));            // [A,B,C]
        assertEquals("C", dl.remove(2));               // [A,B]
        assertTrue(dl.remove("A"));                    // [B]
        assertEquals(List.of("B"), snapshot(dl));
        assertEquals("B", dl.first());
        assertEquals("B", dl.last());
        assertSize(dl, 1);
    }
}
