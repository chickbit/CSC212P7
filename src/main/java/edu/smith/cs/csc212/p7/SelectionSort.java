package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort {

	/**
	 * Trying this the way Piazza puts it. This is a swap sort.
	 * 
	 * @param input - the list to be sorted.
	 * @return a sorted ArrayList<Integer>
	 */
	public static List<Integer> selectionSort2(List<Integer> input) {
		List<Integer> output = new ArrayList<Integer>();
		// copy input to ArrayList
		for (Integer i : input) {
			output.add(i);
		}
		// now we loop over the whole output list and swap things
		for (int i = 0; i < output.size() - 1; i++) {
			int minDex = findMinIndexAfter(output, i);
			System.out.println(output.toString());
			BubbleSort.swap(output, i, minDex);
			System.out.println(output.toString());
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
		List<Integer> work = set.subList(i, set.size());
		int mindex = 0;
		for (int j = 0; j < set.size(); j++) {
			// System.out.println("set[mindex] = " + set.get(mindex) + " | set[j] = " + set.get(j));
			// if the thing at j is smaller than the thing at i,
			if ((int) set.get(j) < (int) set.get(0)) {
				// set mindex to j
				mindex = j;
			}

		}
		return mindex + i;
	}

}
