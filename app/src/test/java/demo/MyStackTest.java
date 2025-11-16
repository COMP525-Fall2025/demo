package demo;

import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void newStackIsEmpty() {
        MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.empty(), "New stack should be empty");
    }

    @Test
    void pushThenPeekDoesNotRemove() {
        MyStack<String> stack = new MyStack<>();
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek(), "peek() should return the top element");
        assertFalse(stack.empty(), "Stack should not be empty after pushes");
        // peek again to ensure it doesn't remove
        assertEquals("B", stack.peek(), "peek() should not remove the top element");
    }

    @Test
    void pushThenPopInLifoOrder() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.empty(), "Stack should be empty after popping all elements");
    }

    @Test
    void popOnEmptyThrowsException() {
        MyStack<String> stack = new MyStack<>();
        assertTrue(stack.empty());

        assertThrows(EmptyStackException.class, stack::pop,
                "pop() on an empty stack should throw EmptyStackException");
    }

    @Test
    void peekOnEmptyThrowsException() {
        MyStack<String> stack = new MyStack<>();
        assertTrue(stack.empty());

        assertThrows(EmptyStackException.class, stack::peek,
                "peek() on an empty stack should throw EmptyStackException");
    }

    @Test
    void searchReturnsCorrectPosition() {
        MyStack<String> stack = new MyStack<>();
        stack.push("A"); // bottom
        stack.push("B");
        stack.push("C"); // top

        assertEquals(1, stack.search("C"), "Top element should be at position 1");
        assertEquals(2, stack.search("B"));
        assertEquals(3, stack.search("A"), "Bottom element should have highest position");
        assertEquals(-1, stack.search("X"), "search() should return -1 for missing elements");
    }

    @Test
    void searchHandlesNull() {
        MyStack<String> stack = new MyStack<>();
        stack.push("A");
        stack.push(null);
        stack.push("B");

        assertEquals(2, stack.search(null), "Null element should be found with correct position");
    }

    @Test
    void clearEmptiesTheStack() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);

        assertFalse(stack.empty());
        stack.clear();
        assertTrue(stack.empty(), "Stack should be empty after clear()");
        assertThrows(EmptyStackException.class, stack::pop,
                "After clear(), pop() should throw EmptyStackException");
    }
}
