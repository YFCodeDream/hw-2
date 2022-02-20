@SuppressWarnings("unchecked")
public class CADeque<T> implements SimpleDeque<T> {
    private Object[] elements;

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 8;

    /**
     * 默认扩展容量倍数
     */
    private static final int EXPAND_BASE = 2;

    /**
     * 标记头部
     */
    private int head;

    /**
     * 标记尾部
     */
    private int tail;

    public CADeque() {
        elements = new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void addFirst(T x) {
        // 左移一位
        head = getRealIndex(head - 1);
        elements[head] = x;

        if (head == tail) {
            expandArr();
        }
    }

    @Override
    public T removeFirst() {
        // 取出头部元素
        T element = (T) elements[head];
        // 头部元素置空
        elements[head] = null;
        // 计算当前头部下标重新赋值
        head = getRealIndex(head + 1);
        // 返回删除的首部元素
        return element;
    }

    @Override
    public T peekFirst() {
        return (T) elements[head];
    }

    @Override
    public void addLast(T x) {
        tail = getRealIndex(tail + 1);
        elements[tail] = x;

        if (head == tail) {
            expandArr();
        }
    }

    @Override
    public T removeLast() {
        T element = (T) elements[tail];
        elements[tail] = null;
        tail = getRealIndex(tail - 1);
        return element;
    }

    @Override
    public T peekLast() {
        return (T) elements[getRealIndex(tail - 1)];
    }

    private int getRealIndex(int logicIndex) {
        int currentArrLen = elements.length;

        if (logicIndex < 0) {
            logicIndex += currentArrLen;
        } else if (logicIndex >= currentArrLen) {
            logicIndex -= currentArrLen;
        }

        return logicIndex;
    }

    private void expandArr() {
        
    }
}
