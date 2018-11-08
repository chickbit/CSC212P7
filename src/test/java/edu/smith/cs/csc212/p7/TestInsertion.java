package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestInsertion {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * 
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(List<Integer> items) {
		for (int i = 0; i < items.size() - 1; i++) {
			if (items.get(i) >= items.get(i + 1)) {
				System.err.println("items out of order: " + items.get(i) + ", " + items.get(i + 1) + " at index=" + i);
				return false;
			}
		}
		return true;
	}

	/**
	 * Here's some data!
	 */
	private static int[] data = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

	@Test
	public void testInsertionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		System.out.println("sort len = " + sortMe.size());
		Assert.assertFalse(checkSorted(sortMe));

		List<Integer> n = InsertionSort.insertionSort(new ArrayList<>(sortMe));
		Assert.assertTrue(checkSorted(n));

		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		n = InsertionSort.insertionSort(sortMe);
		Assert.assertTrue(checkSorted(n));
	}

}