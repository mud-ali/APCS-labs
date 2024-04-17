package priorityQueue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BCAMinPQ<E extends Comparable<E>> implements BCAQueue<E> {

    /* Stores items in heap starting at index 1 */
    private ArrayList<E> heap = new ArrayList<>();

    public BCAMinPQ() {
        // dummy element in index 0, making actual elements start at index 1
        heap.add(null);
    }

    /**
     * @return index of the left child of element at index i.
     * 
     */
    private int leftChildOf(int i) {
        return 2 * i;
    }

    /**
     * @return index of the right child of element at index i.
     */
    private int rightChildOf(int i) {
        return 2 * i + 1;
    }

    /**
     * @return index of the parent of element at index i.
     */
    private int parentOf(int i) {
        return i / 2;
    }

    /**
     * Swap the elements at indexes i1 and i2.
     */
    private void swap(int i1, int i2) {
        E temp = heap.get(i1);
        heap.set(i1, heap.get(i2));
        heap.set(i2, temp);
    }

    /**
     * Push up the element at index i (swapping with bigger parent) until it is
     * at the appropriate level. (parent is smaller) This will fix the heap
     * property if the element of index i is the only element out of place.
     */
    private void pushUp(int i) {
        while (i != 1) {
            // stop if parent is smaller!
            if (heap.get(i).compareTo(heap.get(parentOf(i))) > 0)
                break;
            // push element at i up the tree
            swap(i, parentOf(i));
            i = parentOf(i);
        }
    }

    /** Adds a new element to the the queue. */
    public void enqueue(E o) {
        heap.add(o);
        pushUp(size());
    }

    /**
     * @return the next item from the queue without popping it. If no item,
     *         returns null
     */
    public E peek() {
        if (size() > 0)
            return heap.get(1);
        return null;
    }

    /**
     * Push down the element at index i (swapping with its smallest child) until
     * it is at the appropriate level. (children are both bigger) This will fix
     * the heap property if the element of index i is the only element out of
     * place.
     */
    private void pushDown(int i) {
        while (leftChildOf(i) <= size()) {

            if ((rightChildOf(i) > size()
                    || heap.get(i).compareTo(heap.get(rightChildOf(i))) < 0)
                    && heap.get(i).compareTo(heap.get(leftChildOf(i))) < 0)
                break;

            int smaller = rightChildOf(i) > size() || heap.get(leftChildOf(i))
                    .compareTo(heap.get(rightChildOf(i))) < 0 ? leftChildOf(i)
                            : rightChildOf(i);

            swap(i, smaller);
            i = smaller;
        }
    }

    /**
     * Removes the smallest item from the queue and returns it.
     *
     * @throws NoSuchElementException if the queue is empty.
     */
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("MinPQ is empty");
    
        swap(1, size());
        E removed = heap.remove(size());
        pushDown(1);
        return removed;
    }

    /*
     * Try BCAMinPQTest now! You should pass the remaining tests 1b through 8,
     * and HeapSort
     */

    /**
     * @return whether the queue is empty or not.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return the number of items currently in the queue.
     */
    public int size() {
        // The arraylist has an extra element at position 0, hence the subtraction of 1.
        return heap.size() - 1; 
    }

    /**
     * @return item at index i
     */
    public E get(int i) {
        return heap.get(i);
    }

    /**
     * @debug
     */
    public String toString() {
        return heap.toString();
    }
}
