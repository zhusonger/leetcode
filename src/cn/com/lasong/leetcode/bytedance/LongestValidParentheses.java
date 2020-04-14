package cn.com.lasong.leetcode.bytedance;

import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/14
 * Description:
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        int ans;
        ans = longestValidParentheses.longestValidParentheses("(()"); // 2
        System.out.println(ans);
        ans = longestValidParentheses.longestValidParentheses(")()())"); // 4
        System.out.println(ans);
        ans = longestValidParentheses.longestValidParentheses("()(()"); // 2
        System.out.println(ans);
        ans = longestValidParentheses.longestValidParentheses(")()())"); // 4
        System.out.println(ans);
        ans = longestValidParentheses.longestValidParentheses("()(())"); // 6
        System.out.println(ans);

    }

    public int longestValidParentheses(String s) {
        char[] array = s.toCharArray();
        int ans = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < array.length; i++) {
            char item = array[i];
            if (item == '(') {
                stack.push(i);
            } else if (item == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}
