package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestMerge {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * 
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(List<Integer> items) {
		for (int i = 0; i < items.size() - 1; i++) {
			if (items.get(i) > items.get(i + 1)) {
				System.err.println("items out of order: " + items.get(i) + ", " + items.get(i + 1) + " at index=" + i);
				return false;
			}
		}
		return true;
	}

	/**
	 * Here's some data!
	 */
	private static int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static int[] data2 = { 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 6, 6, 10, 10, 10, 10, 10 };

	@Test
	public void testMerge2() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		ArrayList<Integer> sortMe2 = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		for (int y : data2) {
			sortMe2.add(y);
		}

		List<Integer> n = MergeSort.mergeTwoSorted(new ArrayList<>(sortMe), new ArrayList<>(sortMe2));
		Assert.assertTrue(n.size() == (data.length + data2.length));
		Assert.assertTrue(checkSorted(n));
	}

	@Test
	public void testRecursiveMergeSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		ArrayList<Integer> sortMe2 = new ArrayList<>();
		ArrayList<Integer> sortMe3 = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
			sortMe3.add(y);
		}
		for (int y : data2) {
			sortMe2.add(y);
			sortMe3.add(y);
		}
		Collections.shuffle(sortMe3);

		List<Integer> n = MergeSort.recursiveMergeSort(new ArrayList<>(sortMe3));
		Assert.assertTrue(n.size() == sortMe3.size());
		Assert.assertTrue(checkSorted(n));
	}

	@Test
	public void testIterativeMergeSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		ArrayList<Integer> sortMe2 = new ArrayList<>();
		ArrayList<Integer> sortMe3 = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
			sortMe3.add(y);
		}
		for (int y : data2) {
			sortMe2.add(y);
			sortMe3.add(y);
		}
		Collections.shuffle(sortMe3);

		List<Integer> n = MergeSort.iterativeMergeSort(new ArrayList<>(sortMe3));
		Assert.assertTrue(n.size() == sortMe3.size());
		Assert.assertTrue(checkSorted(n));
	}

}
