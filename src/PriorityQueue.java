import java.util.ArrayList;

/**
 * @author David Patterson
 *
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<T>> extends ArrayList<Integer> {

	private ArrayList<Integer> q;
	private int size;

	/**
	 * ProrityQueue constructor, creates a new ArrayList and initializes size
	 */
	public PriorityQueue() {
		this.q = new ArrayList<Integer>();
		this.size = 0;
	}

	/** 
	 * Uses the inherited ArrayList 
	 * method to check if the ArrayList is empty
	 */
	@Override
	public boolean isEmpty() {
		return q.isEmpty();
	}

	/** Uses the inherited ArrayList
	 * method to clear the ArrayList
	 * 
	 */
	@Override
	public void clear() {
		q.clear();
	}

	/**
	 * Functions the same way as the add method
	 * Adds a new element to the ArrayList, keeping 
	 * the BinaryHeap
	 * 
	 * @param obj
	 * @return boolean
	 */
	public boolean offer(Integer obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		if (this.isEmpty()) {
			q.add(obj);
			this.size++;
			return true;
		}

		int check = 0;
		int spot = this.size / 2;
		q.add(obj);
		int temp = q.get(spot);

		while (obj < temp) {
			spot = spot / 2;
			temp = q.get(spot);
			if (spot == 0) {
				check = 1;
				break;
			}
		}
		if (check == 1) {
			if (q.get(spot) > obj) {
				q.set(spot, obj);
				q.set(size, temp);
				this.size++;
				return true;
			} else {
				q.set(size, q.get(spot + 1));
				q.set(spot + 1, obj);
			}
		}
		this.size++;
		return true;
	}

	/**
	 * Functions the same way as the offer method
	 * Adds a new element to the ArrayList, keeping 
	 * the BinaryHeap
	 * 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean add(Integer obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		if (this.isEmpty()) {
			q.add(obj);
			this.size++;
			return true;
		}

		int check = 0;
		int spot = this.size / 2;
		q.add(obj);
		int temp = q.get(spot);

		while (obj < temp) {
			spot = spot / 2;
			temp = q.get(spot);
			if (spot == 0) {
				check = 1;
				break;
			}
		}
		if (check == 1) {
			if (q.get(spot) > obj) {
				q.set(spot, obj);
				q.set(size, temp);
				this.size++;
				return true;
			} else {
				q.set(size, q.get(spot + 1));
				q.set(spot + 1, obj);
			}
		}
		this.size++;
		return true;
	}

	/**
	 * Removes element from the arrayList keeping
	 * the BinaryHeap
	 * 
	 * @param obj
	 * @return boolean
	 */
	public boolean remove(Integer obj) {
		int spot = 0;
		int temp = q.get(spot);

		//Removes the given element from the arrayList and replaces it with the last element
		//within the arrayList
		
		while (obj != temp) {
			spot++;
			if (spot == this.size) {
				return false;
			}
			temp = q.get(spot);
		}
		int endNode = q.get(this.size - 1);

		q.set(spot, endNode);
		q.remove(this.size - 1);
		this.size--;

		//Checks if the node needs to move up or down the ArrayList, if up moves to the correct
		//position and returns else checks down the ArrayList
		
		int parentNode = q.get(spot / 2);
		if (endNode < parentNode) {
			while (endNode < parentNode) {
				int holderNode = parentNode;
				q.set(spot / 2, endNode);
				q.set(spot, holderNode);
				spot = spot / 2;
				parentNode = q.get(spot / 2);
			}
		} else {

			//Get the starting indexes for the node moving down and its current left and 
			//right children
			
			int leftIndex;
			int rightIndex;
			if (spot == 0) {
				leftIndex = 1;
				rightIndex = 2;
			} else if (spot == 1) {
				leftIndex = 3;
				rightIndex = 4;
			} else {
				leftIndex = (spot * 2) + 1;
				rightIndex = (spot * 2) + 2;
			}

			if (leftIndex >= this.size) {
				return true;
			}

			int leftNode = q.get(leftIndex);

			if (rightIndex >= this.size) {
				int holderNode = q.get(leftIndex);
				if (endNode > leftNode) {
					holderNode = leftNode;
					q.set(leftIndex, endNode);
					q.set(spot, holderNode);
				}
				return true;
			}

			int rightNode = q.get(rightIndex);

			//Moves the node down the Heap, swapping with the smaller value of its left and 
			//right children
			
			while ((endNode > rightNode) && (endNode > leftNode)) {
				int holderNode;
				if (leftNode > rightNode) {
					holderNode = rightNode;
					q.set(rightIndex, endNode);
					q.set(spot, holderNode);
					spot = rightIndex;
				} else {
					holderNode = leftNode;
					q.set(leftIndex, endNode);
					q.set(spot, holderNode);
					spot = leftIndex;
				}

				if (spot == 0) {
					leftIndex = 1;
					rightIndex = 2;
				} else if (spot == 1) {
					leftIndex = 3;
					rightIndex = 4;
				} else {
					leftIndex = (spot * 2) + 1;
					rightIndex = (spot * 2) + 2;
				}

				if (leftIndex >= this.size) {
					break;
				}

				endNode = q.get(spot);
				leftNode = q.get(leftIndex);
				if (rightIndex >= this.size) {
					if (endNode > leftNode) {
						holderNode = leftNode;
						q.set(leftIndex, endNode);
						q.set(spot, holderNode);
					}
					break;
				} else {
					rightNode = q.get(rightIndex);
				}
			}
		}
		return true;
	}

	/**
	 * Removes the first element from the
	 * arrayList keeping the BinaryHeap
	 * 
	 * Returns the integer removed or null if the 
	 * ArrayList is empty.
	 * 
	 * @return Object
	 */
	public Object poll() {
		if (isEmpty()) {
			return null;
		}
		if (this.size == 1) {
			int temp = this.peek();
			q.remove(0);
			this.size--;
			return temp;
		}
		Integer temp = this.peek();
		this.remove(temp);
		return temp;
	}

	/**
	 * Uses the inherited toArray to
	 * return a new array of objects
	 * 
	 */
	@Override
	public Object[] toArray() {
		return q.toArray();
	}

	/**
	 * Uses the inherited toArray to
	 * return a new array of integers
	 * 
	 * @param a
	 * @return Integer[]
	 */
	public Integer[] toArray(Integer[] a) {
		if(a.length < this.size){
			a =  new Integer[this.size];
		}
		for(int i = 0; i < this.size; i++){
			a[i] =  q.get(i);
		}
		return a;
	}
	
	public Number[] toArray(Number[] a) {
		if(a.length < this.size){
			a =  new Number[this.size];
		}
		for(int i = 0; i < this.size; i++){
			a[i] =  q.get(i);
		}
		return a;
	}

	/**
	 * Returns the first element in the arrayList
	 * without removing it
	 * 
	 * @return Integer
	 */
	public Integer peek() {
		if (q.isEmpty()) {
			return null;
		}
		return q.get(0);
	}

	/**
	 * Returns the current number of elements with the 
	 * ArrayList
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if the element is in the 
	 * ArrayList
	 * 
	 * @param obj
	 * @return boolean
	 */
	public boolean contains(int obj) {
		return q.contains(obj);
	}
}
