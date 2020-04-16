package cn.com.lasong.leetcode.card.queue_stack;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> queue = new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        Queue<Integer> back = new LinkedList<>();
        int top = -1;
        while (!queue.isEmpty()) {
            top = queue.poll();
            back.offer(top);
        }
        int size = back.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(back.poll());
        }
        return top;
    }
    
    /** Get the top element. */
    public int top() {
        Queue<Integer> back = new LinkedList<>();
        int top = -1;
        while (!queue.isEmpty()) {
            top = queue.poll();
            back.offer(top);
        }
        int size = back.size();
        for (int i = 0; i < size; i++) {
            queue.offer(back.poll());
        }
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */