import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.lang.Math.*;

/**
 * Created by sune on 12/02/2017.
 */
public class RandomQueue<Item> implements Iterable<Item> {

    private int N = 0;
    private Item[] queue = (Item[]) new Object[1];

    // create an empty random queue
    public RandomQueue() {}

    // is it empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of elements
    public int size() {
        return N;
    }

    // add an item
    public void enqueue(Item item) {
        if (N == queue.length) resize(queue.length*2);
        queue[N++] = item;
    }

    // changing the size of the array
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
          temp[i] = queue[i];
        }
        queue = temp;
    }

    // return (but do not remove) a random item
    public Item sample() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException();
        } else {
            int random = StdRandom.uniform(N);
            return queue[random];
        }
    }

    // remove and return a random item
    public Item dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException();
        } else {
            int random = StdRandom.uniform(N);
            Item temp = queue[random];
            queue[random] = queue[N-1];
            queue[--N] = null;

            if (N > 0 && N == queue.length/4) resize(queue.length/2);

            return temp;
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {

        private int[] randomNumbers = new int[N];
        private int index = 0;

        public RandomQueueIterator(){
            for (int i = 0; i < N; i++) {
                randomNumbers[i] = i;
            }
            StdRandom.shuffle(randomNumbers);
        }

        @Override
        public boolean hasNext() {
            return index < N;
        }

        @Override
        public Item next() {
            return queue[randomNumbers[index++]];
        }
    }

// Mine takes ca. 60 lines, my longest method has 5 lines.
// The main method below tests your implementation. Do not change it.
    public static void main(String args[]) {
// Build a queue containing the Integers 1,2,...,6:
        RandomQueue<Integer> Q = new RandomQueue<Integer>();
        for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!
// Print 30 die rolls to standard output
        StdOut.print("Some die rolls: ");
        for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() + " ");
        StdOut.println();
// Let’s be more serious: do they really behave like die rolls?
        int[] rolls = new int[10000];
        for (int i = 0; i < 10000; ++i)
            rolls[i] = Q.sample(); // autounboxing! Also cool!
        StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
        StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
                StdStats.stddev(rolls));
// Now remove 3 random values
        StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
// Add 7,8,9
        for (int i = 7; i < 10; ++i) Q.enqueue(i);
// Empty the queue in random order
        while (!Q.isEmpty()) StdOut.print(Q.dequeue() + " ");
        StdOut.println();
// Let’s look at the iterator. First, we make a queue of colours:
        RandomQueue<String> C = new RandomQueue<String>();
        C.enqueue("red");
        C.enqueue("blue");
        C.enqueue("green");
        C.enqueue("yellow");
        Iterator I = C.iterator();
        Iterator J = C.iterator();
        StdOut.print("Two colours from first shuffle: " + I.next() + " " + I.next() + " ");
        StdOut.print("\nEntire second shuffle: ");
        while (J.hasNext()) StdOut.print(J.next() + " ");
        StdOut.print("\nRemaining two colours from first shuffle: " + I.next() + " " + I.next());
    }
}
