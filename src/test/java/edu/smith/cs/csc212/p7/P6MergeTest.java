package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class P6MergeTest {
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
		P6DoublyLinkedList<Integer> sortMe = new P6DoublyLinkedList<Integer>();
		P6DoublyLinkedList<Integer> sortMe2 = new P6DoublyLinkedList<Integer>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		for (int y : data2) {
			sortMe2.addBack(y);
		}

		List<Integer> n = P6MergeSort.mergeTwoSorted(sortMe.copy(), sortMe2.copy()).copyToList();
		Assert.assertTrue(n.size() == (data.length + data2.length));
		Assert.assertTrue(checkSorted(n));
	}

	@Test
	public void testRecursiveMergeSort() {
		// See if the data can be reversed:
		P6DoublyLinkedList<Integer> sortMe = new P6DoublyLinkedList<Integer>();
		P6DoublyLinkedList<Integer> sortMe2 = new P6DoublyLinkedList<Integer>();
		P6DoublyLinkedList<Integer> sortMe3 = new P6DoublyLinkedList<Integer>();
		for (int y : data) {
			sortMe.addBack(y);
			sortMe3.addBack(y);
		}
		for (int y : data2) {
			sortMe2.addBack(y);
			sortMe3.addBack(y);
		}

		ArrayList<Integer> temp = (ArrayList<Integer>) sortMe3.copyToList();
		Collections.shuffle(temp);

		for (int y : temp) {
			sortMe3.removeFront();
			sortMe3.addBack(y);
		}
		P6DoublyLinkedList<Integer> ntemp = P6MergeSort.recursiveMergeSort(sortMe3);
		List<Integer> n = ntemp.copyToList();
		System.out.println(ntemp.toString());
		System.out.println(n.toString());
		Assert.assertTrue(n.size() == sortMe3.size());
		Assert.assertTrue(checkSorted(n));
	}

}
