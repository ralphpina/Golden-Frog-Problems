import java.util.*;

/*
 * 1) “Write a subroutine, function, or method that takes an array of characters and returns 
 * an array of the same characters in reversed order with every consonant between 'a' and 'z' 
 * lower cased and every vowel between 'a' and 'z' upper cased. Prove your implementation works."
 *
 *
 * From the description above it sounds like the only valid inputs are ASCII 97 - 122 ('a' to 'z')
 * meaning all the characters being passed would be lower case. Thus I just need to flip the vowels,
 * the consonants will already be in lower case.
 */

public class ReverseArray {
	
	public static void main(String[] args) {
		char[] firstArray = {'a','b','c','d','e','f','g'};
		char[] secondArray = {'l','d','r','y'};
		char[] thirdArray = {'a', 'e', 'i', 'o', 'u'};
		char[] random = createRandomArray();

		System.out.println("firstArray = " + Arrays.toString(firstArray));
		System.out.println("firstArray reversed = " + Arrays.toString(reverse(firstArray)));

		System.out.println("secondArray = " + Arrays.toString(secondArray));
		System.out.println("secondArray reversed = " + Arrays.toString(reverse(secondArray)));

		System.out.println("thirdArray = " + Arrays.toString(thirdArray));
		System.out.println("thirdArray reversed = " + Arrays.toString(reverse(thirdArray)));

		System.out.println("random = " + Arrays.toString(random));
		System.out.println("random reversed = " + Arrays.toString(reverse(random)));
	}

	public static char[] reverse(char[] array) {
		/*
		 * Keeping an index for the back of the array, walking it 
		 * back as I reverse the order
		 */
		int endIndex = array.length - 1;
		/*
		 * If odd I need to make sure to check the middle value
		 */
		boolean odd = (array.length % 2 == 1 ? true : false);
		char temp;

		/*
		 * This function is O(n/2), so we never actually traverse the whole array.
		 * This should save a lot of time if we are dealing with a massive array 
		 * loaded into main memory
		 */
		for (int i = 0; i < (array.length / 2); i++) {
			temp = process(array[i]);
			array[i] = process(array[endIndex]);
			array[endIndex] = temp;
			--endIndex;
		}

		/*
		 * If the array is odd, just test the middle character
		 */
		if (odd) {
			array[(array.length / 2) + 1] = process(array[(array.length / 2) + 1]);
		}

		return array;
	}

	/*
	 * Convinience method to check the vowels
	 */
	public static char process(char c) {
		/* 
		 * If they are vowels subtract 32 to make them upper case
		 */
		if (c == 97 || c == 101 || c == 105 || c == 111 || c == 117) {
			c -= 32;
		}
		return c;
	}

	/* 
	 * Creating a random 10 variable array for testing
	 */
	public static char[] createRandomArray() {
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 97; i < 122; i++)
			list.add(i);
		
		List<Integer> combination = new ArrayList<Integer>(10);
		
		Collections.shuffle(list);
	    combination.addAll(list.subList(0, 10));
	    
	    char[] returnArray = new char[10];
	    for (int i = 0; i < 10; i++) {

	        returnArray[i] = (char) combination.get(i).intValue();
	    }

	   return returnArray;
	}
}