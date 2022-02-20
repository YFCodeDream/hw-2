/**
 * <p>A deque is a container that stores elements, where elements can
 * be added to and removed from both the "back" (or "tail") and the
 * "front" (or "head") of the deque. Access to the front of the deque
 * is provided by the methods <code>addFront</code>,
 * <code>removeFront</code>, and <code>peekFront</code>, while access
 * to the back is provided by <code>addBack</code>,
 * <code>removeBack</code>, and <code>peekBack</code>.</p>
 *
 * <p>The state of a deque can be represented by the sequence of
 * elements contained in the deque. For example, the state of a queue
 * containing <code>n</code> elements can be written as sequence
 * <code>x_1 x_2 ... x_{n-1} x_n</code>, where <code>x_n</code> is at
 * the front of the deque, and <code>x_1</code> is at the back. From
 * such a state, the methods <code>addBack</code> and
 * <code>addFront</code> have the effect of adding pre-appending an
 * element in the sequence before <code>x_1</code> and appending an
 * element after <code>x_n</code>, respectively. The methods
 * <code>removeBack</code> and <code>removeFront</code> will have the
 * effect of removing and returning <code>x_1</code> and
 * <code>x_n</code>, respectively.</p>
 *
 * <p>Observe that a deque generalizes both a stack and a queue. The
 * methods <code>addFront</code>, <code>removeFront</code>, and
 * <code>peekFront</code> are analogous to a
 * <code>SimpleStack</code>'s <code>push</code>, <code>pop</code>, and
 * <code>peek</code> methods. Similarly, <code>addBack</code>,
 * <code>removeFront</code>, and <code>peekFront</code> are analogous
 * to a <code>SimpleQueue</code>'s <code>add</code>,
 * <code>remove</code>, and <code>peek</code>, respectively.
 * 
 * @see SimpleQueue
 * @see SimpleStack
 */

public interface SimpleDeque<E> {
    /**
     * Get the current size of the deque (i.e., the number of elements
     * currently stored in the deque).
     *
     * @return the current size
     */
    int size();

    /**
     * Determine if the deque is currently empty (i.e., currently
     * contains no elements).
     *
     * @return true if and only if the deque is empty
     */        
    boolean isEmpty();

    /**
     *
     * <p>Add a new element <code>x</code> to the front of the
     * deque. If the state of the deque was previously <code>x1 x2
     * ... xn</code>, then after this operation the new state will be
     * <code>x1 x2 ... xn x</code>.</p>
     *
     * @param x the element to be added to the front of the deque
     */    
    void addFirst(E x);

    /**
     * <p>Remove and return the element at the front of the deque, if the
     * deque is not empty. If the deque was previously in the state
     * <code>x1 x2 ... x{n-1} xn</code> with <code>xn</code> at
     * the front, the resulting state will be <code>x1 x2
     * ... x{n-1}</code> and the element <code>xn</code> will be
     * returned. If the deque is empty, an
     * <code>EmptyStackException</code> is thrown.</p>
     *
     * @return the first element in the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */    
    E removeFirst();

    /**
     * <p>Return the element currently at the front of the deque, or
     * <code>null</code> if the deque is empty. The state of the deque
     * is unchanged after this operation.</p>
     *
     * @return the front element of the deque, or if empty,
     * <code>null</code>
     */    
    E peekFirst();

    /**
     * <p>Add a new element <code>x</code> to back of the deque. If
     * the state of the deque was previously <code>x1 x2
     * ... xn</code>, then after this operation the new state will be
     * <code>x x1 x2 ... xn</code>.</p>
     *
     * @param x the element to be added to the back of the deque
     */        
    void addLast(E x);

    /**
     * <p>Remove and return the element at the back of the deque, if the
     * deque is not empty. If the deque was previously in the state
     * <code>x1 x2 ... x{n-1} xn</code> with <code>x1</code> at
     * the back, the resulting state will be <code>x2
     * ... x{n-1} xn</code> and the element <code>x1</code> will be
     * returned. If the deque is empty, an
     * <code>EmptyStackException</code> is thrown.</p>
     *
     * @return the last element in the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */        
    E removeLast();

    /**
     * <p>Return the element currently at the back of the deque, or
     * <code>null</code> if the deque is empty. The state of the deque
     * is unchanged after this operation.</p>
     *
     * @return the back element of the deque, or if empty,
     * <code>null</code>
     */    
    E peekLast();
}
