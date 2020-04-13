package cn.com.lasong.leetcode.card.queue_stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/13
 * Description:
 * <p>
 * https://leetcode-cn.com/explore/learn/card/queue-stack/216/queue-first-in-first-out-data-structure/865/
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * <p>
 * circularQueue.enQueue(1);  // 返回 true
 * <p>
 * circularQueue.enQueue(2);  // 返回 true
 * <p>
 * circularQueue.enQueue(3);  // 返回 true
 * <p>
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * <p>
 * circularQueue.Rear();  // 返回 3
 * <p>
 * circularQueue.isFull();  // 返回 true
 * <p>
 * circularQueue.deQueue();  // 返回 true
 * <p>
 * circularQueue.enQueue(4);  // 返回 true
 * <p>
 * circularQueue.Rear();  // 返回 4
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 */
public class MyCircularQueue {

    private int[] array;
    private int head = -1;
    private int tail = -1;
    private int capacity;
    private MyCircularQueue(){}
    public MyCircularQueue(int k) {
        capacity = k;
        array = new int[capacity];
    }

    public int Front() {
        return head >= 0 ? array[head] : -1;
    }

    public int Rear() {
        return tail >= 0 ? array[tail] : -1;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    public boolean enQueue(int value) {
        int ptr = -1;
        if (tail < 0) {
            ptr = 0;
            head = ptr;
            tail = ptr;
        } else if (!isFull()){
            ptr = (tail + 1) % capacity;
            tail = ptr;
        }
        boolean ret = ptr >= 0;
        if (ret) {
            array[ptr] = value;
        }

        return ret;
    }

    public boolean deQueue() {
        if (head >= 0) {
            int ptr = (head + 1) % capacity;
            ptr = head == tail ? -1 : ptr;

            array[head] = -1; // 置空

            head = ptr;
            tail = ptr < 0 ? -1 : tail;
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        boolean ret = circularQueue.enQueue(1);  // 返回 true
        System.out.println(ret);

        ret = circularQueue.enQueue(2);  // 返回 true
        System.out.println(ret);

        ret = circularQueue.enQueue(3);  // 返回 true
        System.out.println(ret);

        ret = circularQueue.enQueue(4);  // 返回 false，队列已满
        System.out.println(ret);

        int value = circularQueue.Rear();  // 返回 3
        System.out.println(value);

        ret = circularQueue.isFull();  // 返回 true
        System.out.println(ret);

        ret = circularQueue.deQueue();  // 返回 true
        System.out.println(ret);

        ret = circularQueue.enQueue(4);  // 返回 true
        System.out.println(ret);

        value = circularQueue.Rear();  // 返回 4
        System.out.println(value);
    }

}
