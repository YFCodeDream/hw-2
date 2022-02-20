/**
 * A linked list based implementation of the <code>SimpleList</code>
 * interface. This implementation stores the list elements in a singly
 * linked list of nodes. The head node stores the element with index
 * 0, the head's next node stores the element with index 1 and so on.
 *
 * @see SimpleList
 */


public class LinkedSimpleList<E> implements SimpleList<E> {

    /**
     * The current size of the list (i.e,, number of elements
     * currently stored in the list).
     */
    private int size = 0;

    /**
     * The first node in the list (or null if the list is empty).
     */
    private Node<E> head = null;

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public boolean isEmpty() {
	return head == null;
    }

    /**
     * Return the <code>i</code>-th successor of the <code>head</code>
     * node in the linked list, or <code>null</code> if not such node
     * exists.
     *
     * @param i and integer between <code>0</code> and <code>size -
     * 1</code>
     * @return the node that is the <code>i</code>-th successor of the
     * <code>head</code>
     */
    private Node<E> getNode(int i) {
		// check if i is a valid index
		if (i < 0 || i >= size) return null;

		Node<E> cur = head;

		// find the i-th successor of the head
		for (int j = 0; j < i; ++j) {
			cur = cur.next;
		}

		return cur;
    }

    @Override
    public E get(int i) {
		// check if the index is in bounds
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}

		return getNode(i).value;
    }

    @Override
    public void set(int i, E x) {
	
	// check if the index is in bounds	
	if (i < 0 || i >= size) {
	    throw new IndexOutOfBoundsException();	    
	}

	
	Node<E> nd = getNode(i);
	nd.value = x;
    }

    @Override
    public void add(int i, E x) {
	if (i < 0 || i > size) {
	    throw new IndexOutOfBoundsException();	    
	}

	// make a new Node to store x
	Node<E> nd = new Node<E>();
	nd.value = x;

	if (i == 0) {
	    // if we are adding at index 0, the newly added item is
	    // stored at the head
	    nd.next = this.head;
	    this.head = nd;
	    
	} else {
	    // insert the node storing x between the node at index i-1
	    // and the node previously at index i
	    Node<E> pred = getNode(i - 1);
	    Node<E> succ = pred.next;
	    pred.next = nd;
	    nd.next = succ;
	    
	}

	++size;
    }

    public E remove(int i) {
	// check if the index is valid
	if (i < 0 || i >= size) {
	    throw new IndexOutOfBoundsException();	    
	}

	if (i == 0) {
	    // upon removing the head node, update the head
	    E value = head.value;
	    head = head.next;
	    --size;
	    return value;
	}

	// remove the i-th node by updating the (i-1)st node's next
	// field
	Node<E> pred = getNode(i - 1);
	E value = pred.next.value;
	pred.next = pred.next.next;

	--size;
	
	return value;
    }
	

    /**
     * An inner class representing a node in the linked list. Each
     * node stores a reference to an element (the node's
     * <code>value</code>), as well as a reference to the next node in
     * the list.
     */
    private class Node<E> {
	Node<E> next;
	E value;
    }
}
