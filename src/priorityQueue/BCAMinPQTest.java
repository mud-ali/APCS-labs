package priorityQueue;

import java.util.Random;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class BCAMinPQTest {
    public static void main(String args[]) {
        BCAMinPQ<Integer> pq = new BCAMinPQ<>();

        pq.enqueue(15);
        pq.enqueue(18);
        pq.enqueue(1);
        pq.enqueue(41);
        pq.enqueue(98);
        pq.enqueue(-16);

        int[] initialHeap = { 0, -16, 18, 1, 41, 98, 15 };
        for (int i = 1; i <= 6; i++) {
            try {
                if (pq.get(i) == initialHeap[i])
                    System.out.println("Passed 0." + i);
                else
                    System.out.println("Failed 0." + i);
            } catch (Exception e) {
                System.out.println("Failed 0." + i + " (Exception)");
            }
        }

        if (pq.peek().equals(-16))
            System.out.println("Passed 1a (peek)");
        else
            System.out.println("Failed 1a (peek)");

        if (pq.dequeue().equals(-16))
            System.out.println("Passed 1b (dequeue)");
        else
            System.out.println("Failed 1b (dequeue)");

        if (pq.dequeue().equals(1))
            System.out.println("Passed 2");
        else
            System.out.println("Failed 2");

        pq.enqueue(102);
        pq.enqueue(87);
        pq.enqueue(42);
        pq.enqueue(1234);

        if (pq.dequeue().equals(15))
            System.out.println("Passed 3");
        else
            System.out.println("Failed 3");

        if (pq.size() == 7)
            System.out.println("Passed 4 (size)");
        else
            System.out.println("Failed 4 (size)");

        if (!pq.isEmpty())
            System.out.println("Passed 5 (isEmpty)");
        else
            System.out.println("Failed 5 (isEmpty)");

        int[] remaining = { 18, 41, 42, 87, 98, 102, 1234 };
        for (int i = 0; i < 7; i++) {
            try {
                if (pq.dequeue() == remaining[i])
                    System.out.println("Passed 6." + i);
                else
                    System.out.println("Failed 6." + i);
            } catch (NoSuchElementException e) {
                System.out
                        .println("Failed 6." + i + " (NoSuchElementException)");
            }
        }

        if (pq.isEmpty())
            System.out.println("Passed 7 (isEmpty)");
        else
            System.out.println("Failed 7 (isEmpty)");

        try {
            pq.dequeue();
            System.out.println("Failed 8 (dequeue Exception)");
        } catch (NoSuchElementException ex) {
            System.out.println("Passed 8 (dequeue Exception)");
        }

        // Stress Test - HeapSort
        Random rand = new Random(1000);
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = rand.nextInt();
            pq.enqueue(arr[i]);
        }
        Arrays.sort(arr);
        int i;
        for (i = 0; i < 1000; i++) {
            if (arr[i] != pq.dequeue())
                break;
        }
        if (i == 1000)
            System.out.println("Passed HeapSort");
        else
            System.out.println("Failed HeapSort after " + i + " elements.");

    }
}
