package cn.com.lasong.leetcode.card.queue_stack;

import java.util.Stack;

class MyQueue {

    Stack<Integer> stack = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {

    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        Stack<Integer> back = new Stack<>();
        while (!stack.isEmpty()) {
            back.push(stack.pop());
        }
        int ret = back.pop();
        while (!back.isEmpty()) {
            stack.push(back.pop());
        }
        return ret;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        Stack<Integer> back = new Stack<>();
        while (!stack.isEmpty()) {
            back.push(stack.pop());
        }
        int ret = back.peek();
        while (!back.isEmpty()) {
            stack.push(back.pop());
        }
        return ret;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.peek();  // 返回 1
        queue.pop();   // 返回 1
        queue.empty(); // 返回 false
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

