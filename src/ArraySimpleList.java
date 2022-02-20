/**
 * An array based implementation of the <code>SimpleList</code>
 * interface. This implementation uses an array to store the elements
 * in the list. An initial capacity can be specified as an argument to
 * the constructor; otherwise <code>DEFAULT_CAPACITY</code> is
 * used. The capacity is dynamically increased as needed.
 *
 * @see SimpleList
 */


public class ArraySimpleList<E> implements SimpleList<E> {

    /**
     * The default capacity of the list.
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * The current capacity of the list.
     */
    private int capacity;

    /**
     * The current size (i.e., number of elements contained in) the
     * list.
     */
    private int size = 0;

    /**
     * The contents of the list. The index of each element in
     * <code>contents</code> coincides with its index in the
     * <code>SimpleList</code>. That is, <code>get(i)</code> should
     * return the element at <code>contents[i]</code> (if any).
     */
    private Object[] contents;

    /**
     * Create an <code>ArraySimpleList</code> with an initial capacity
     * of <code>DEFAULT_CAPACITY</code>.
     */
    public ArraySimpleList() {
	this(DEFAULT_CAPACITY);
    }

    /**
     * Create an <code>ArraySimpleList</code> with an initial capacity
     * of <code>initialCapacity</code>.
     *
     * @param initialCapacity the desired initial capacity of the list
     */
    public ArraySimpleList(int initialCapacity) {
	this.capacity = initialCapacity;
	contents = new Object[capacity];
    }


    @Override
    public int size() {
	return this.size;
    }

    @Override
    public boolean isEmpty() {
	return this.size == 0;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public E get(int i) {
	if (i >= size || i < 0) {
	    throw new IndexOutOfBoundsException();
	}
	
	return (E) contents[i];
    }

    @Override
    public void set(int i, E x) {
	// check if there is already an element at index i
	if (i >= size || i < 0) {
	    throw new IndexOutOfBoundsException();
	}
	
	contents[i] = x;
    }

    @Override
    public void add(int i, E x) {
	// i is a valid index if it is between 0 and size
	if (i > size || i < 0) {
	    throw new IndexOutOfBoundsException();
	}

	// check if we need to increase the capacity before inserting
	// the element
	if (size == capacity) {
	    increaseCapacity();
	}

	++size;

	// insert x by setting contents[i] to x and moving each
	// element previously at index j >= i to index j + 1.
	Object cur = x;
	for (int j = i; j < size; ++j) {
	    Object next = contents[j];
	    contents[j] = cur;
	    cur = next;
	}
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int i) {
	
	// check if <code>i</code> is a valid index to remove
	if (i >= size || i < 0) {
	    throw new IndexOutOfBoundsException();
	}

	--size;

	// get the item currently at index i
	E item = (E) contents[i];

	// move each item at index j > i to index j - 1
	for (int j = i; j < size; ++j) {
	    contents[j] = contents[j+1];
	}

	return item;
    }

    /**
     * Increase the capacity of the <code>ArraySimpleList</code> by
     * copying the elements in <code>contents</code> into a larger
     * array and updating <code>capacity</code> to the size of the new
     * array.
     */
    private void increaseCapacity() {
	
	// create a new array with larger capacity
	Object[] bigContents = new Object[2 * capacity];

	// copy contents to bigContents
	for (int i = 0; i < capacity; ++i) {
	    bigContents[i] = contents[i];
	}

	// set contents to refer to the new array
	contents = bigContents;

	// update this.capacity accordingly
	capacity = 2 * capacity;
    }
}
