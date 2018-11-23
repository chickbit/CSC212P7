package edu.smith.cs.csc212.p7;
//I used an extension day on this project. Days used: 5. Days remaining: 0.

import java.util.ArrayList;
import java.util.List;

import edu.smith.cs.csc212.p7.errors.BadIndexError;
import edu.smith.cs.csc212.p7.errors.EmptyListError;

public class P6DoublyLinkedList<T> implements P6List<T> {
	private Node<T> start;
	private Node<T> end;

	// O(1)
	/**
	 * A doubly-linked list starts empty.
	 */
	public P6DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}

	// O(1)
	@Override
	public T removeFront() {
		// if 0
		checkNotEmpty();
		// if 1
		if (start == end) {
			T val = start.value;
			start = null;
			end = null;
			return val;
		}
		// if >1
		else {
			T val = start.value;
			start = start.after;
			start.before = null;
			return val;
		}
	}

	// O(1)
	@Override
	public T removeBack() {
		// if 0
		checkNotEmpty();
		// if 1
		if (start == end) {
			T val = end.value;
			start = null;
			end = null;
			return val;
		}
		// if >1
		else {
			T val = end.value;
			end = end.before;
			end.after = null;
			return val;
		}
	}

	// O(n)
	@Override
	public T removeIndex(int index) {
		System.out.println("hello world");
		// if 0
		checkNotEmpty();
		// if >0, go thru list
		int i = 0;
		for (Node<T> current = start; current != null; current = current.after) {
			T val = null;
			if (index == i) {
				// grab the value at that index
				val = current.value;

				// then do the necessary bookkeeping:

				// if we are at the beginning, update start
				if (index == 0) {
					// if our list is length 1, update the end
					if (current == end) {
						end = null;
						start = null;
					} else {
						start = start.after;
						start.before = null;
					}

				}
				// if we are at the end of a list >1
				else if (current == end) {
					end = current.before;
					end.after = null;
				}
				// if we are in the middle of a list >1
				else {
					// tell current's neighbors to make friends
					current.before.after = current.after;
					current.after.before = current.before;
				}
				return val;
			}
			i++;
		}
		throw new BadIndexError();
	}

	// O(1)
	@Override
	public void addFront(T item) {
		// if we're empty, make a node with item and set it equal to start and end.
		if (this.isEmpty()) {
			this.start = new Node<T>(item);
			this.end = this.start;
		} else {
			// otherwise, grab start
			Node<T> tmp = this.start;
			// make a new node and set it equal to start
			this.start = new Node<T>(item);
			// start's next is old start
			this.start.after = tmp;
			// old start's previous is new start
			tmp.before = start;
		}
	}

	// O(1)
	@Override
	public void addBack(T item) {
		// if we're empty, make a node with item and set it equal to start and end.
		if (isEmpty()) {
			this.start = new Node<T>(item);
			this.end = this.start;
		} else {
			// otherwise, grab end
			Node<T> tmp = this.end;
			// make a new node and set it equal to end
			this.end = new Node<T>(item);
			// end's previous is old end
			this.end.before = tmp;
			// old end's next is new end
			tmp.after = end;
		}
	}

	// O(n)
	@Override
	public void addIndex(T item, int index) {
		if (index != 0) {
			checkNotEmpty();
		}

		int i = 0;
		// node<t> current = new node -> make a new node and store
		// node<t> current= end -> point at the end node. it's a reference.
		for (Node<T> current = this.start; current != this.end; current = current.after) {
			// if we're at the index we're searching for
			if (index == i) {
				// Make the node to insert
				Node<T> tmp = new Node<T>(item);

				// If we're at the beginning, update start
				if (this.start == current) {
					tmp.after = this.start;
					this.start.before = tmp;
					this.start = tmp;
				}
				// If we're adding after the last element, add to back
				else if (current == end && index == i + 1) {
					// add to back
					// tell End and TMP to make friends
					current.after = tmp;
					tmp.before = current;
					// update the List to know the location of the end
					end = tmp;
				}
				// otherwise, add normally
				else {
					// give tmp its new neighbors
					tmp.before = current.before;
					tmp.after = current;
					// tell tmp's neighbors about tmp
					current.before.after = tmp;
					current.before = tmp;
				}
				return;
			}
			// if none of this happens, increment and try again.
			i++;
		}
		// if we're here, the index we were looking for wasn't in the list. :(
		throw new BadIndexError();
	}

	// O(1)
	@Override
	public T getFront() {
		checkNotEmpty();
		// if we're here, we're not empty
		return start.value;
	}

	// O(1)
	@Override
	public T getBack() {
		checkNotEmpty();
		// if we're here, we're not empty
		return end.value;
	}

	// O(n)
	@Override
	public T getIndex(int index) { // he means get the item at that index
		checkNotEmpty();
		int i = 0;
		T val = null;
		for (Node<T> current = start; current != null; current = current.after) {
			if (i == index) {
				val = current.value;
			}
			i++;
		}
		return val;
	}

	// O(n)
	@Override
	public int size() {
		int i = 0;
		// run from start to end of list and count the nodes
		for (Node<T> current = start; current != null; current = current.after) {
			i++;
		}
		return i;
	}

	// O(1)
	@Override
	public boolean isEmpty() {
		if (start == null || end == null) {
			return true;
		}
		return false;
	}

	// O(1)
	/**
	 * Helper method that throws an error if the list is empty.
	 */
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}

	// For unit tests, List<T> supports equals, but P6List<T> does not.
	public List<T> copyToList() {
		ArrayList<T> output = new ArrayList<T>();
		for (Node<T> n = this.start; n != null; n = n.after) {
			output.add(n.value);
		}
		return output;
	}

	// If you treat your DoublyLinkedList<T> like a queue with pop() as removeFront(), you are destroying the lists.
	// This may also be helpful for unit-testing.
	public P6DoublyLinkedList<T> copy() {
		P6DoublyLinkedList<T> output = new P6DoublyLinkedList<T>();
		for (Node<T> n = this.start; n != null; n = n.after) {
			output.addBack(n.value);
		}
		return output;
	}

	/**
	 * returns a string that encompasses the entire DoublyLinkedList
	 */
	public String toString() {
		String s = "[";
		for (Node<T> n = this.start; n.after != null; n = n.after) {
			s = s + n.value + ", ";
		}
		s = s + this.getBack() + "]";
		return s;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
