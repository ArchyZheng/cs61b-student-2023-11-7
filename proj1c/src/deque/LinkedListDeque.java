package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T> {
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {
        private Node<T> currP;
        private int currSize;

        public LinkedListDequeIterator() {
            this.currP = (Node<T>) sentinel;
            this.currSize = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currSize < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            currP = currP.next;
            currSize += 1;
            return currP.item;
        }
    }

    private final Iterator<T> iterator = new LinkedListDequeIterator<>();


    static public class Node<T> {
        public Node<T> prev;
        public Node<T> next;
        public T item;

        public Node(Node<T> prev, Node<T> next, T item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    Node<T> sentinel = new Node<>(null, null, null);
    int size;

    public LinkedListDeque() {
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node<T> currentNode = new Node<>(null, null, x);
        currentNode.prev = this.sentinel;
        currentNode.next = this.sentinel.next;
        this.sentinel.next.prev = currentNode;
        this.sentinel.next = currentNode;
        this.size += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node<T> currentNode = new Node<>(null, null, x);
        currentNode.next = this.sentinel;
        currentNode.prev = this.sentinel.prev;
        this.sentinel.prev.next = currentNode;
        this.sentinel.prev = currentNode;
        this.size += 1;

    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> output = new ArrayList<>();
        Node<T> currentNode = this.sentinel;
        for (int i = 0; i < this.size; i++) {
            currentNode = currentNode.next;
            output.add(currentNode.item);
        }
        return output;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        Node<T> currentNode = this.sentinel.next;
        if (this.size > 0) {
            this.sentinel.next = currentNode.next;
            currentNode.next.prev = this.sentinel;
            this.size -= 1;
            return currentNode.item;
        }
        return null;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        Node<T> currentNode = this.sentinel.prev;
        if (this.size > 0) {
            this.sentinel.prev = currentNode.prev;
            currentNode.prev.next = this.sentinel;
            this.size -= 1;
            return currentNode.item;
        }
        return null;
    }

    /**
     * The Deque abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        Node<T> currentNode = this.sentinel;
        if (index < 0 || index >= this.size) {
            return null;
        }
        for (int count = -1; count < index; count++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return getR(this.sentinel.next, index);
    }

    public T getR(Node<T> l, int i) {
        if (i == 0) {
            return l.item;
        }
        return getR(l.next, i - 1);
    }
}
