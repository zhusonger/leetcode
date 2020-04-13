package cn.com.lasong.leetcode.card.queue_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/13
 * Description:
 * https://leetcode-cn.com/explore/learn/card/queue-stack/218/stack-last-in-first-out-data-structure/877/
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        int min = minStack.getMin();   //--> 返回 -3.
        System.out.println(min);
        minStack.pop();
        min = minStack.getMin();      //--> 返回 0.
        System.out.println(min);
        minStack.pop();
        min = minStack.getMin();  // --> 返回 -2.
        System.out.println(min);
        minStack.pop();
        min = minStack.getMin();  // --> 返回 -2.
        System.out.println(min);
    }
    List<Integer> stack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayList<>();
    }

    private int min = Integer.MAX_VALUE;
    public void push(int x) {
        stack.add(x);
        if (x < min) {
            min = x;
        }
    }

    public void pop() {
        int size = stack.size();
        if (size > 0) {
            int value = stack.remove(size - 1);
            // 如果弹出的不是最小值, 直接结束
            if (min != value) {
                return;
            }
            // 如果弹出的是最小值, 重新计算最小值
            min = Integer.MAX_VALUE;
        }
        // 更新最小值
        int left = 0;
        int right = stack.size() - 1;
        while (left <= right) {
            int leftV = stack.get(left);
            int rightV = stack.get(right);

            if (leftV < min) {
                min = leftV;
            }
            if (rightV < min) {
                min = rightV;
            }
            left++;
            right--;
        }
    }

    public int top() {
        int size = stack.size();
        if (size > 0) {
            return stack.get(size - 1);
        }
        throw new RuntimeException("Empty Stack");
    }

    public int getMin() {
        return min;
    }
}
