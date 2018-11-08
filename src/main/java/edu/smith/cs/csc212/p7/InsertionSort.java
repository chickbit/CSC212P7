package edu.smith.cs.csc212.p7;
//https://piazza.com/class/jlmq9jjkmz53xv?cid=184

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {

	/**
	 * Inserts Integer j into a sorted list of integers.
	 * 
	 * @param list - the list to be sorted
	 * @param j    - the value to be inserted into the list
	 */
	public static void insertSorted(List<Integer> list, Integer j) {
		boolean added = false;
		// run over the list
		int k = 0;
		for (Integer i : list) {
			if (i > j) {
				list.add(k, j);
				added = true;
				break;
			}
			k++;
		}
		// we've run over the entire list, so if we're here and we haven't added j
		// then just add it to the back.
		if (!added) {
			list.add(j);
		}
		System.out.println(list.toString());
	}

	public static List<Integer> insertionSort(List<Integer> unsorted) {
		// copy to a new list
		System.out.println("here we go " + unsorted.size());

		List<Integer> sorted = new ArrayList<Integer>();
		System.out.println("here " + sorted.size());

		System.out.println(sorted.toString());
		for (Integer j : unsorted) {
			insertSorted(sorted, j);
		}
		return sorted;

	}

}
