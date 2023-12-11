package deque;

import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T> {
    private T[] myArray;
    private int nextFirst;
    private int nextLast;
    private int capacity;
    private int size;

    public ArrayDeque() {
        myArray = (T[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
        capacity = 8;
        size = 0;

    }


    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (size >= capacity) {
            resize();
        }
        myArray[nextFirst] = x;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = capacity - 1;
        }
        size += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if (size >= capacity) {
            resize();
        }
        myArray[nextLast] = x;
        nextLast += 1;
        if (nextLast == this.capacity) {
            nextLast = 0;
        }
        size += 1;
    }

    /**
     * Double the capacity of myArray.
     */
    private void resize() {
        capacity *= 2;
        T[] myArrayNew = (T[]) new Object[capacity];
        // Head to Last
        System.arraycopy(myArray, 0, myArrayNew, 0, nextLast);
        // Rear to First
        int lengthForCopyFirst = capacity / 2 - nextLast;
        System.arraycopy(myArray, nextFirst + 1, myArrayNew, capacity - lengthForCopyFirst, lengthForCopyFirst);
        nextFirst = capacity - lengthForCopyFirst - 1;
        myArray = myArrayNew;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> output = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            output.add(get(i));
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
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        if (nextFirst == capacity - 1) {
            nextFirst = -1; // for next line `nextFirst` will become 0
        }
        nextFirst += 1;
        size -= 1;
        T output = myArray[nextFirst];
        if (size < capacity / 2 && capacity > 8) {
            resizeDown();
        }
        return output;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = capacity; // for next line `nextLast` will become `capacity - 1`
        }
        nextLast -= 1;
        size -= 1;
        T output = myArray[nextLast];
        if (size <= capacity / 2 && capacity > 8) {
            resizeDown();
        }
        return output;
    }

    private void resizeDown() {
        T[] arrayNew = (T[]) new Object[capacity / 2];
        for (int i = 0; i < size; i++) {
            T element = get(i);
            arrayNew[i] = element;
        }
        capacity = capacity / 2;
        myArray = arrayNew;
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
        if (index >= size || index < 0) {
            return null;
        }
        int indexNew = (nextFirst + index + 1) % (capacity);
        return myArray[indexNew];
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
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {
        private int currPosition;

        public ArrayDequeIterator() {
            this.currPosition = 0;
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
            return this.currPosition < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            currPosition += 1;
            return (T) get(currPosition - 1);
        }
    }
}
