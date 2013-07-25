import java.util.*;

/*
 * 2) In a relevant language, create an array of 1000 numbers. Initialize all of the values 
 * in the array to zero. Create two threads that run concurrently and which increment each 
 * element of the array one time. When both threads have finished running, all elements in 
 * the array should have the value of two. Verify this.
 */

public class IncrementByTwo {
	
	/*
	 * My array of 1000 ints
	 */
	public int[] array = new int[1000];
	/*
	 * to keep track of how many threads are done
	 */
	public int count = 0;

	/*
	 * ++ is not atomic, instead of using AtomicInteger and it's methods
	 * I am synchronizing the incrementing of each int
	 */
	public static synchronized int increment(int i) {
    		return ++i;
	}

	/*
	 * call back function
	 * once both threads return I print out the array again
	 */
	public synchronized void done() {
		++count;
		if (count == 2) {
			System.out.println(Arrays.toString(array));
		}
	}

	public static void main(String[] args) {

		final IncrementByTwo obj = new IncrementByTwo();

		System.out.println(Arrays.toString(obj.array));

		new Thread() {
			public void run() {
				System.out.println("First thread started");
				for (int i = 0; i < obj.array.length; i++) {
					obj.array[i] = increment(obj.array[i]);
					if (i == 500) {
						/*
						 * I decided to pause the first thread for 10 milliseconds to 
						 * maximize the chance of a collision. 1000 ints for a modern machine is 
						 * trivial.
						 */
						try {
							synchronized(this) {
								wait(10);
							}	
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println("First thread finished");
				obj.done();
			}
		}.start();

		new Thread() {
			public void run() {
				System.out.println("Second thread started");
				for (int i = 0; i < obj.array.length; i++) {
					obj.array[i] = increment(obj.array[i]);
				}
				System.out.println("Second thread finished");
				obj.done();
			}
		}.start();

	}
}
