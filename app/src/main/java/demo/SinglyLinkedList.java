package demo;

/**
 * A generic singly linked list.
 */

public class SinglyLinkedList<E> {

    /** Node class representing one element in the list. */
    private static class Node<E> {
        private E element;
        private Node<E> next;

        // Constructor as specified
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /** Default constructor (creates an empty list). */
    public SinglyLinkedList() {
    }

    /** Prints elements as a chain, e.g., a -> b -> c -> null */
    public void printChain() {
        Node<E> cur = head;
        while (cur != null) {
            System.out.print(cur.getElement() + " -> ");
            cur = cur.getNext();
     }
        System.out.println("null");
    }

    /** Returns the number of elements in the list. */
    public int size() {
        return size;
    }

    /** Compute size by traversal */
    public int size2() {
        int count = 0;
        Node<E> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /** Returns true if the list is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isEmpty2() {
        return (head==null && tail==null);
    }

    /**
     * Returns (but does not remove) the first element in the list, or null if empty.
     */
    public E first() {
        return isEmpty() ? null : head.getElement();
    }

    /**
     * Returns (but does not remove) the last element in the list, or null if empty.
     */
    public E last() {
        return isEmpty() ? null : tail.getElement();
    }

    /** Adds a new element to the front of the list. */
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0)
            tail = head;
        size++;
    }

    /** Adds a new element to the end of the list. */
    public void addLast(E e) {
        // you fill in here
    }

    /** Removes and returns the first element of the list, or null if empty. */
    public E removeFirst() {
        // you fill in here
        E answer = null;
        return answer;
    }

    /** Removes the first occurrence of o (by equals); returns true if removed. */
    public boolean remove(Object o) {
        // you fill in here 
        return false;
    }

    /** Removes and returns the element at index within [0..size-1]. */
    public E remove(int index) {
        // you fill in here 
        E removed = null; 
        return removed;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // Optional demo
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        System.out.println("=== Start: Empty list ===");
        System.out.println("isEmpty(): " + list.isEmpty()); // true
        System.out.println("first(): " + list.first()); // null
        System.out.println("last(): " + list.last()); // null
        System.out.println("removeFirst(): " + list.removeFirst()); // null
        list.printChain();
        System.out.println("size(): " + list.size()); // 0
        System.out.println();

        System.out.println("=== Add elements with addFirst ===");
        list.addFirst("C");
        list.addFirst("B");
        list.addFirst("A"); // [A, B, C]
        System.out.println("List after addFirst: first=" + list.first() + ", last=" + list.last());
        list.printChain();
        System.out.println("size(): " + list.size());
        System.out.println();

        System.out.println("=== Add elements with addLast ===");
        list.addLast("D");
        list.addLast("E"); // [A, B, C, D, E]
        list.printChain();
        System.out.println("size(): " + list.size());
        System.out.println();

        System.out.println("=== Remove elements with removeFirst ===");
        System.out.println("Removed: " + list.removeFirst()); // removes A
        System.out.println("Now first(): " + list.first()); // B
        System.out.println("Now last(): " + list.last()); // E
        list.printChain();
        System.out.println("size(): " + list.size()); // 4
        System.out.println();

        System.out.println("=== Remove all elements ===");
        while (!list.isEmpty()) {
            System.out.println("Removed: " + list.removeFirst());
        }
        System.out.println("List empty again? " + list.isEmpty());
        System.out.println("first(): " + list.first());
        System.out.println("last(): " + list.last());
        System.out.println();

        System.out.println("=== Add after emptying ===");
        list.addLast("X");
        System.out.println("first(): " + list.first());
        System.out.println("last(): " + list.last());
        list.printChain();
        System.out.println("size(): " + list.size());
    }

}
