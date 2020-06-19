package lab11;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Demonstration of Java's PriorityQueue class.
 * 
 * @author Erin Parker
 * @version April 3, 2020
 */
public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<String>(25, new ReverseStringComparator());
		pq.add("a");
		pq.add("b");
		pq.add("c");
		pq.add("d");
		pq.add("e");
		pq.add("f");
		pq.add("g");

		System.out.println("Array: " + Arrays.toString(pq.toArray()));

		System.out.println("First item removed: " + pq.remove());
		System.out.println("Second item removed: " + pq.remove());
		System.out.println("Third item removed: " + pq.remove());
		System.out.println("Fourth item removed: " + pq.remove());
		System.out.println("Fifth item removed: " + pq.remove());
		System.out.println("Sixth item removed: " + pq.remove());
		System.out.println("Seventh item removed: " + pq.remove());
	}
}