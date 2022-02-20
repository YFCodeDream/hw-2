/**
 * A <code>SimpleDeque</code> implementation that uses an
 * <code>ArraySimpleList</code>. Observe that all of the operations
 * for a <code>SimpleDeque</code> are specializations of operations
 * for the <code>SimpleList</code> interface. Thus, the implementation
 * here is quite simple.
 */

public class ASLDeque<E> implements SimpleDeque<E> {
    private SimpleList<E> list = new ArraySimpleList<E>();

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public void addFirst(E x) { list.add(0, x); }

    @Override
    public E removeFirst() { return list.remove(0); }

    @Override
    public E peekFirst() { return list.get(0); }

    @Override
    public void addLast(E x) { list.add(list.size(), x); }

    @Override
    public E removeLast() { return list.remove(list.size() - 1); }

    @Override
    public E peekLast() { return list.get(list.size() - 1); }
}
