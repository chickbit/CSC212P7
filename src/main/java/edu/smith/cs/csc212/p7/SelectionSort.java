package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
	// TODO test SelectionSort
	// TODO comment SelectionSort
	/**
	 * Selection Sort works by finding the minimum value in the list and bringing it to the front, over and over. (From rubric
	 * https://github.com/jjfiv/CSC212P7) ]This is not an in-place sort.
	 * 
	 * @param input - the list to be sorted.
	 */
	public static void selectionSort1(List<Integer> input) {
		while (true) {
			boolean sorted = true;
			int N = 0;
			Integer smIndex = null;

			// Find index of smallest item
			for (int i = N; i < input.size(); i++) {
				if (smIndex == null || input.get(i) < input.get(smIndex)) {
					// if we have a smallest value, then we aren't sorted
					smIndex = i;
					sorted = false;
				}
			}

			if (sorted) {
				break;
			} else {
				// we now have the index of the smallest item.

				// add the thing at that index to the front.
				input.add(input.get(smIndex), 0);

				// remove it from its old place
				input.remove(smIndex);
			}
			N = N + 1;
		}
	}

	/**
	 * Trying this the way Piazza puts it. This is a swap sort.
	 * 
	 * @param input - the list to be sorted.
	 * @return a sorted ArrayList<Integer>
	 */
	public static List<Integer> selectionSort2(List<Integer> input) {
		List<Integer> output = new ArrayList<Integer>(input.size());
		// copy input to ArrayList
		for (Integer i : input) {
			output.add(input.get(i));
		}
		// now we loop over the whole output list and swap things
		for (int i = 0; i < output.size() - 1; i++) {
			int minDex = findMinIndexAfter(output, i);
			BubbleSort.swap(output, i, minDex);
		}
		return output;
	}

	/**
	 * Finds the index in a List of Integers of the lowest Integer after the Integer at index i.
	 * 
	 * @param set - a List<Integer>
	 * @param i   - the index after which we must find the minimum
	 * @return - the index of the element in set between i and the end that is the lowest.
	 */
	private static int findMinIndexAfter(List<Integer> set, int i) {
		int iterator = 0;
		int min = i;
		// loop over set
		for (Integer k : set) {
			if (iterator > i) {
				// compare set at min and set at current
				// if current is less than min
				if (set.get(min).compareTo(set.get(iterator)) > 1) {
					// set min equal to current index
					min = iterator;
				}
			}
			// increase the count!
			iterator++;
		}
		return min;
	}

}
