package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static List<Integer> mergeTwoSorted(List<Integer> A, List<Integer> B) {
		// list to build
		List<Integer> C = new ArrayList<Integer>();
		// loop over the lists until one of them is done
		int i = 0; // emergency iterator
		while (true) {
			/*
			 * System.out.println(); System.out.println("A = " + A.toString()); System.out.println("B = " + B.toString());
			 * System.out.println("C = " + C.toString());
			 */
			// if one of the lists is done, exit the loop
			if (A.size() == 0 || B.size() == 0) {
				break;
			}
			// compare A[0] and B[0]
			Integer smallestElement;
			// if A[0] is larger, remove A[0]
			if (A.get(0).compareTo(B.get(0)) < 1) {
				smallestElement = A.remove(0);
			}
			// else, B[0] is larger. remove B[0]
			else {
				smallestElement = B.remove(0);
			}
			// add the element to the end of our built list
			C.add(smallestElement);

			// if we're going to run into an infinite loop, break.
			if (i > 200) {
				// System.out.println("you're in an infinite loop, pal.");
				break;
			}
			i++;

		}
		// append any extra bits:
		if (A.size() != 0) {
			// append the rest of A
			C.addAll(A);
			A.clear();
		} else if (B.size() != 0) {
			// append the rest of B
			C.addAll(B);
			B.clear();
		}

		/*
		 * System.out.println(); System.out.println("A = " + A.toString()); System.out.println("B = " + B.toString());
		 * System.out.println("C = " + C.toString());
		 */
		// return our sorted list.
		return C;
	}

	/**
	 * I tried to implement StackOverflowMerge and it didn't work :(
	 * 
	 * @param unsorted
	 * @return
	 */
	public static List<Integer> recursiveMergeSort(List<Integer> unsorted) {
		// Base case
		if (unsorted.size() == 1 || unsorted.size() == 0) {
			// if I'm 0 or 1 things, I'm sorted!
			return unsorted;
		}

		// split the lists
		List<Integer> A = new ArrayList<>(unsorted.subList(0, unsorted.size() / 2));
		List<Integer> B = new ArrayList<>(unsorted.subList(unsorted.size() / 2, unsorted.size()));

		// sort A and B, recursively.
		A = recursiveMergeSort(A);
		B = recursiveMergeSort(B);

		// merge the sorted A and B
		return (mergeTwoSorted(A, B));
	}

	// logic from https://www.geeksforgeeks.org/iterative-merge-sort/
	// retrieved nov 8 2018
	/**
	 * I tried to implement StackOverflowSort :(
	 * 
	 * @param unsorted
	 * @return
	 */
	public static List<Integer> iterativeMergeSort(List<Integer> unsorted) {
		int chunkSize;
		int leftStartIndex;
		int n = unsorted.size();
		// for each section in the list to sort
		// split the sublist we're sorting in two
		// send it to mergetwo
		// increase the size of the steps
		// loop taken directly from referenced code
		for (chunkSize = 1; chunkSize <= n - 1; chunkSize = 2 * chunkSize) {
			System.out.println("ChunkSize = " + chunkSize);

			// for each size of chunk
			// sort each chunk we can divide the list into

			// for each chunk, of whatever size we're currently working at
			// split the chunk in two and merge sort it.
			// leftStartIndex <= n-1 prevents us from starting at the very end of the list
			// but does not prevent and end-chunk of size 1

			// for chunks starting at zero, and stepping over each chunk by stepping up the list by chunkSize steps

			for (leftStartIndex = 0; leftStartIndex <= n - 1; leftStartIndex += chunkSize) {
				// overwrite the original chunk with that sorted chunk
				int mid = (leftStartIndex + leftStartIndex + chunkSize) / 2;
				List<Integer> leftChunk = new ArrayList<>(unsorted.subList(leftStartIndex, mid));
				List<Integer> rightChunk = new ArrayList<>(unsorted.subList(mid, leftStartIndex + chunkSize));
				System.out.println("Left: " + leftChunk.toString());
				System.out.println("Right: " + rightChunk.toString());

				List<Integer> tempChunk = mergeTwoSorted(leftChunk, rightChunk);
				System.out.println("Temp: " + tempChunk.toString());

				// overwrite
				for (int i = leftStartIndex; i < leftStartIndex + chunkSize; i++) {
					// remove the old value at that index
					unsorted.remove(i);
					// insert the new value at that index
					unsorted.add(i, tempChunk.get(i - leftStartIndex));
				}

			}
			System.out.println("unsorted: " + unsorted.toString());
		}

		return unsorted;
	}

	// logic from https://www.geeksforgeeks.org/iterative-merge-sort/
	// retrieved nov 8 2018
	public static List<Integer> iterativeMergeSort2(List<Integer> unsorted) {
		int chunkSize;
		int leftStartIndex;
		int n = unsorted.size();

		// our working structures: a list of chunks!
		List<ArrayList<Integer>> chunkList = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> tempChunkList = new ArrayList<ArrayList<Integer>>();

		// split into an ArrayList of ArrayList chunks
		for (int i = 0; i < unsorted.size(); i++) {
			// put each element of the original list into its own tiny ArrayList
			ArrayList<Integer> chunk = new ArrayList<Integer>();
			chunk.add(unsorted.get(i));
			chunkList.add(chunk);
		}

		System.out.println(chunkList.toString());

		// now we can start merging them

		while (chunkList.size() > 1) {
			System.out.println("Called! chunkList.size() = " + chunkList.size());
			for (int i = 0; i < chunkList.size(); i += 2) {
				ArrayList<Integer> chunk;
				// combine chunk and its neighbor into a new list of chunks until we reach the end
				if (chunkList.size() - i > 1) {
					chunk = new ArrayList<>(mergeTwoSorted(chunkList.get(i), chunkList.get(i + 1)));
				} else {
					chunk = chunkList.get(i);
				}
				System.out.println("chunk = " + chunk.toString());
				tempChunkList.add(chunk);
			}

			// reset our lists
			chunkList = tempChunkList;
			tempChunkList = new ArrayList<ArrayList<Integer>>();
			System.out.println(" - chunkList.size() is now " + chunkList.size());
			System.out.println(chunkList.get(0).toString());

		}
		return chunkList.get(0);
	}
}
