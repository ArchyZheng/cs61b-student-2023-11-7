import deque.MaxArrayDeque;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void testIteratorLinkedListDeque() {
        LinkedListDeque<Integer> LLD = new LinkedListDeque<>();
        LLD.addFirst(3);
        LLD.addFirst(2);
        LLD.addFirst(1);
        for (int element : LLD) {
            System.out.println(element);
        }
    }

    @Test
    public void testIteratorArrayDeque() {
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        AD.addFirst(3);
        AD.addFirst(2);
        AD.addFirst(1);
        for (int element : AD) {
            System.out.println(element);
        }
    }

    @Test
    public void testEqualDeques() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> lld2 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testToList() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
    }

    @Test
    public void testToString() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
    }

    @Test
    public void testMaxArrayDequeTest() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                }
                if (o1.equals(o2)) {
                    return 0;
                }

                return -1;
            }
        };
        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(comparator);
        lld1.addFirst(3);
        lld1.addFirst(9);
        lld1.addFirst(8);
        assertThat(lld1.max()).isEqualTo(9);
    }
}
