package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.p7.P6DoublyLinkedList;

public class P6MergeSort {
	/**
	 * Merges two sorted DoublyLinkedLists into one.
	 * 
	 * @param A - a sorted DoublyLinkedList
	 * @param B - a sorted DoublyLinkedList
	 * @return a sorted DoublyLinkedList that combines both A and B.
	 */
	public static P6DoublyLinkedList<Integer> mergeTwoSorted(P6DoublyLinkedList<Integer> A,
			P6DoublyLinkedList<Integer> B) {

		// list to build
		P6DoublyLinkedList<Integer> C = new P6DoublyLinkedList<Integer>();
		int i = 0;

		// loop over the lists until one is done.
		while (true) {
			if (A.isEmpty() || B.isEmpty()) {
				break;
			}

			// compare A[0] and B[0]
			Integer smEl;

			// if A[0] is smaller, remove it
			if (A.getFront() < B.getFront()) {
				smEl = A.removeFront();
			}
			// Else, remove B[0]
			else {
				smEl = B.removeFront();
			}

			// add the captured element to C
			C.addBack(smEl);

			// break if we start to loop forever.
			i++;
			if (i > 200) {
				break;
			}
		}

		// append any extra bits
		if (!A.isEmpty()) {
			// add the rest of A to C.
			while (true) {
				C.addBack(A.removeFront());
				if (A.isEmpty()) {
					break;
				}
			}

		} else if (!B.isEmpty()) {
			// add the rest of B to C
			while (true) {
				C.addBack(B.removeFront());
				if (B.isEmpty()) {
					break;
				}
			}
		}
		// return our sorted list
		return C;
	}

	/**
	 * Recursive merge sort for P6DoublyLinkedList
	 * 
	 * @param unsorted - the list to be sorted
	 * @return - unsorted, but sorted.
	 */
	public static P6DoublyLinkedList<Integer> recursiveMergeSort(P6DoublyLinkedList<Integer> unsorted) {
		// Store size so we can split the list.
		// DoublyLinkedLists are inefficient at finding size, so I only want to call this once per method call
		int n = unsorted.size();

		// Base case - if true, stop recursing
		if (n == 1 || unsorted.isEmpty()) {
			return unsorted;
		}

		// split the list in half
		P6DoublyLinkedList<Integer> A = frontSubList(unsorted.copy(), n / 2);
		P6DoublyLinkedList<Integer> B = backSubList(unsorted.copy(), n / 2, n);

		// sort the half-lists
		A = recursiveMergeSort(A);
		B = recursiveMergeSort(B);

		// merge the sorted half-lists
		return (mergeTwoSorted(A, B));
	}

	/**
	 * A helper method. Makes a sublist from the front of a given P6DoublyLinkedList
	 * 
	 * @param list a P6DoublyLinkedList
	 * @param fin  - the index to end the sublist (exclusive)
	 * @return a P6DoublyLinkedList that's a sublist of list
	 */
	public static P6DoublyLinkedList<Integer> frontSubList(P6DoublyLinkedList<Integer> list, int fin) {
		// our working list
		P6DoublyLinkedList<Integer> C = new P6DoublyLinkedList<Integer>();

		// loop over the back of the given list and copy it to C
		for (int i = 0; i < fin; i++) {
			C.addBack(list.removeFront());
		}

		// return the working list
		return C;
	}

	/**
	 * A helper method. Makes a sublist from the back of a given P6DoublyLinkedList
	 * 
	 * @param list a P6DoublyLinkedList
	 * @param init - the index to begin the list (inclusive)
	 * @param fin  - the index to end the sublist (exclusive)
	 * @return a P6DoublyLinkedList that's a sublist of list
	 */
	public static P6DoublyLinkedList<Integer> backSubList(P6DoublyLinkedList<Integer> list, int start, int end) {
		// our working list
		P6DoublyLinkedList<Integer> C = new P6DoublyLinkedList<Integer>();

		// loop over the back of the given list and copy it to C
		for (int i = start; i < end; i++) {
			C.addFront(list.removeBack());
		}
		// return the working list
		return C;
	}

}
