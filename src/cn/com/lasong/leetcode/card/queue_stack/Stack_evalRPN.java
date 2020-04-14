package cn.com.lasong.leetcode.card.queue_stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/14
 * Description:
 * https://leetcode-cn.com/explore/learn/card/queue-stack/218/stack-last-in-first-out-data-structure/880/
 * 逆波兰表达式求值
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class Stack_evalRPN {

    public static void main(String[] args) {
        Stack_evalRPN evalRPN = new Stack_evalRPN();
        int ans = evalRPN.evalRPN(new String[]{"2", "1", "+", "3", "*"});
        System.out.println(ans);
    }

    /**
     * 题意其实跟虚拟机栈的计算相同
     * 入栈出栈进行计算
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {

        int len = tokens.length;
        Set<String> operator = new HashSet<>();
        operator.add("*");
        operator.add("+");
        operator.add("-");
        operator.add("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            String ch = tokens[i];
            // 碰到运算符出栈栈顶的2个元素
            if (operator.contains(ch)) {
                String num1 = stack.pop();
                String num2 = stack.pop();
                stack.push(String.valueOf(calculate(num2, num1, ch)));
                continue;
            }
            stack.push(ch);
        }

        String ans = stack.pop();
        return Integer.parseInt(ans);
    }

    private int calculate(String num1, String num2, String op) {
        int num_1 = Integer.parseInt(num1);
        int num_2 = Integer.parseInt(num2);
        if (op.equals("*")) {
            return num_1 * num_2;
        } else if (op.equals("/")) {
            return num_1 / num_2;
        } else if (op.equals("+")) {
            return num_1 + num_2;
        } else if (op.equals("-")) {
            return num_1 - num_2;
        }
        return 0;
    }
}
