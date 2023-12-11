import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

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
}
