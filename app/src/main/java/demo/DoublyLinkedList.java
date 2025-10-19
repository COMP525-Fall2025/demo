package demo;

import java.util.Objects;
/**
 * A basic doubly linked list implementation with header/trailer sentinels.
 */
public class DoublyLinkedList<E> {

    // ---------------- nested Node class ----------------
    private static class Node<E> {
        private E element; // element stored at this node
        private Node<E> prev; // previous node
        private Node<E> next; // next node

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
    // ------------- end of nested Node class -------------

    // instance variables
    private Node<E> header; // header sentinel (no element)
    private Node<E> trailer; // trailer sentinel (no element)
    private int size = 0; // number of real elements in the list

    /** Constructs a new empty list. */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /** Returns the number of elements in the linked list. */
    public int size() {
        return size;
    }

    /** Tests whether the linked list is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the first element of the list, or null if
     * empty.
     */
    public E first() {
        if (isEmpty())
            return null;
        return header.getNext().getElement(); // first element is beyond header
    }

    /**
     * Returns (but does not remove) the last element of the list, or null if empty.
     */
    public E last() {
        if (isEmpty())
            return null;
        return trailer.getPrev().getElement(); // last element is before trailer
    }

    // -------- public update methods --------

    /** Adds element e to the front of the list. */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext()); // just after header
    }

    /** Adds element e to the end of the list. */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer); // just before trailer
    }

    /** Removes and returns the first element of the list, or null if empty. */
    public E removeFirst() {
        if (isEmpty())
            return null; // nothing to remove
        return removeNode(header.getNext()); // real first node
    }

    /** Removes and returns the last element of the list, or null if empty. */
    public E removeLast() {
        if (isEmpty())
            return null; // nothing to remove
        return removeNode(trailer.getPrev()); // real last node
    }

      // Remove the FIRST occurrence of e
    public boolean remove(E e) {
        // you fill in here
        return false;
    }

    // Remove and return the element at the given index [0..size-1]. O(n)
    public E remove(int index) {
        // you fill in here
        return null;
    }

    // Remove ALL occurrences of e; return the total number of removed elements. 
    public int removeAll(E e) {
         // you fill in here
        return 0;
    }

    // -------- private helpers --------

    /** Adds element e between given predecessor and successor nodes. */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /** Unlinks the given node (assumed internal) and returns its element. */
    private E removeNode(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}