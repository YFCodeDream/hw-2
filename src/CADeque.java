/**
 * 基于数组实现的双端队列
 * @param <T> 传入的泛型类型
 */
@SuppressWarnings("unchecked")
public class CADeque<T> implements SimpleDeque<T> {
    /**
     * 底层数组
     */
    private Object[] elements;

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 8;

    /**
     * 默认扩展容量倍率
     */
    private static final int EXPAND_MULTIPLIER = 2;

    /**
     * 标记头部，存放真实下标
     */
    private int head;

    /**
     * 标记尾部，存放真实下标
     */
    private int tail;

    /**
     * 默认构造方法
     * 创建底层数组为默认容量的数组
     * 初始化头部下标为0
     * 初始化尾部下标为0
     */
    public CADeque() {
        elements = new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
    }

    /**
     * 获取当前双端队列的长度
     * 因为head和tail均为真实下标，可直接将其相减折算为长度
     * @return 当前双端队列的长度
     */
    @Override
    public int size() {
        return getRealIndex(tail - head);
    }

    /**
     * 当且仅当head与tail重合时为空
     * 因为在入队操作时，只要head == tail即触发扩容操作
     * @return 当前队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 头部入队
     * <h1>注意：此时实现的双端队列，head为下标的位置存放了对应的元素</h1>
     * 所以，先左移head，再进行赋值
     * @param x the element to be added to the front of the deque
     */
    @Override
    public void addFirst(T x) {
        // 左移一位
        head = getRealIndex(head - 1);
        elements[head] = x;

        if (head == tail) {
            expandArr();
        }
    }

    /**
     * 因为此时实现的双端队列，head为下标的位置存放了对应的元素
     * 所以先取head所在的元素，再将head位置的内存释放
     * 再将head右移一位，重新赋值
     * <h1>一定要注意操作顺序！</h1>
     * @return 已删除的头部元素
     */
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

    /**
     * 同理，取头部元素的时候，直接以head为下标取元素即可
     * @return 头部元素
     */
    @Override
    public T peekFirst() {
        return (T) elements[head];
    }

    /**
     * 尾部入队
     * <h1>注意：此时实现的双端队列，tail左移一位才存放了对应的元素
     * 也就是说，tail指向的位置比真实存放元素的位置提前了一位</h1>
     * 所以先在当前tail赋值，再右移
     * 正好补上addFirst先左移再赋值的空缺
     * @param x the element to be added to the back of the deque
     */
    @Override
    public void addLast(T x) {
        elements[tail] = x;
        tail = getRealIndex(tail + 1);

        if (head == tail) {
            expandArr();
        }
    }

    /**
     * 因为此时实现的双端队列，tail指向的位置比真实存放元素的位置提前了一位
     * 所以先左移tail一位，找到真实存放元素的下标，再进行取值，释放的操作
     * @return 已删除的尾部元素
     */
    @Override
    public T removeLast() {
        tail = getRealIndex(tail - 1);
        T element = (T) elements[tail];
        elements[tail] = null;
        return element;
    }

    /**
     * 同理，取尾部元素的时候，先对tail进行左移操作
     * @return 尾部元素
     */
    @Override
    public T peekLast() {
        return (T) elements[getRealIndex(tail - 1)];
    }

    /**
     * 返回真实的下标位置
     * 当左移/右移计算的下标越界时，利用此方法计算真实下标
     * @param logicIndex 逻辑下标
     * @return 真实下标
     */
    private int getRealIndex(int logicIndex) {
        int currentArrLen = elements.length;

        // 如果逻辑下标已经小于0
        // 则加上当前数组长度，使之指向数组中元素
        if (logicIndex < 0) {
            logicIndex += currentArrLen;
        } else if (logicIndex >= currentArrLen) {
            // 如果逻辑下标已经大于数组长度
            // 则减去数组长度，将其归位
            logicIndex -= currentArrLen;
        }

        return logicIndex;
    }

    /**
     * <h1>这是最为重要的扩容操作</h1>
     * 先计算得到旧数组的长度和新数组的长度
     * 再进行如下拷贝
     * <h1>
     *     自头部开始的元素：
     *     head ~ (oldLen - 1) 为下标的元素，拷贝至 0 ~ (oldLen - head - 1)处
     *     自尾部开始的元素：
     *     0 ~ (tail - 1)为下标的元素，拷贝至 (oldLen - tail) ~ (oldLen - 1)处
     * </h1>
     * 经过这样的拷贝操作后，重新赋值head为0，tail为oldLen
     * 别忘了将底层数组指向新数组
     */
    private void expandArr() {
        int oldArrLen = elements.length;
        int newArrLen = elements.length * EXPAND_MULTIPLIER;

        Object[] newElements = new Object[newArrLen];

        System.arraycopy(elements, head, newElements, 0, oldArrLen - head);

        System.arraycopy(elements, 0, newElements, oldArrLen - tail, tail);

        head = 0;
        tail = oldArrLen;
        this.elements = newElements;
    }
}
