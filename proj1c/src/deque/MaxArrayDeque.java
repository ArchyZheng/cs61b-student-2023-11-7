package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> myComparator;

    public MaxArrayDeque(Comparator<T> c) {
        myComparator = c;
    }

    /**
     * @return returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        T currMax = null;
        for (T element : this) {
            if (currMax == null && element != null) {
                currMax = element;
            }
            if (myComparator.compare(element, currMax) > 0) {
                currMax = element;
            }
        }
        return currMax;
    }

    public T max(Comparator<T> c) {
        T currMax = null;
        for (T element : this) {
            if (currMax == null && element != null) {
                currMax = element;
            }
            if (c.compare(element, currMax) > 0) {
                currMax = element;
            }
        }
        return currMax;
    }
}
