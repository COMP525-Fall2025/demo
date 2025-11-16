package demo;
import java.util.EmptyStackException;

public class MyStack<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> top;  // top of stack
    private int size;     

    public MyStack() {
        top = null;
        size = 0;
    }

    /** Pushes an item onto the top of this stack and returns the item. */
    public E push(E item) {
       // you fill in here
       return null; 
    }

    /**
     * Removes the object at the top of this stack and returns that object.
     * Throws EmptyStackException if this stack is empty.
     */
    public E pop() {
       // you fill in here
       return null; 
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     * Throws EmptyStackException if this stack is empty.
     */
    public E peek() {
        // you fill in here
        return null; 
    }

    /** Tests if this stack is empty. */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * The top of the stack is position 1. Returns -1 if not found.
     * This matches the semantics of java.util.Stack.search().
     */
    public int search(Object o) {
        // you fill in here
        return -1;
    }

    /** Removes all elements from this stack. */
    public void clear() {
        top = null;
        size = 0;
    }

    // Simple demo / test
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("peek()  = " + stack.peek());      // C
        System.out.println("empty() = " + stack.empty());     // false
        System.out.println("search(\"B\") = " + stack.search("B")); // 2

        while (!stack.empty()) {
            System.out.println("pop() = " + stack.pop());
        }

        // Uncomment to see the exception:
        // stack.pop(); // throws EmptyStackException
    }
}

