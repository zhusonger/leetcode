package cn.com.lasong.leetcode.kuaishou;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/14
 * Description:
 *
 * 题目要求
 * 找出一个数学表达式的配对括号 落单左括号 落单右括号
 * 数学表达式不要求规范
 *
 * 比如
 *
 * 1*2+3+(3+3)))(((
 *
 * 配对 1 落单左括号3 落单右括号2
 */
public class B_Brackets {

    public static void main(String[] args) {
        B_Brackets brackets = new B_Brackets();
        int[] ans = brackets.getBrackets("1*2+3+(3+3)))(((");
        ListHelper.print(ans);
    }

    /**
     * 用栈进行记录左括号, 遇到左括号入栈, 表示开始
     * 匹配到右括号
     *      栈非空表示有左括号, 就弹出, 匹配计数+1
     *      栈空表示没有左括号, 右括号计数+1
     *
     * 结束获取栈大小就是没有匹配到的左括号数
     * @param str
     * @return
     */
    public int[] getBrackets(String str) {
        char[] array = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        int[] ans = new int[3];
        for (int i = 0; i < array.length; i++) {
            char character = array[i];
            if (character == '(') {
                stack.push(character);
            } else if (character == ')') {
                if (stack.isEmpty()) {
                    ans[2] += 1;
                } else {
                    ans[0] += 1;
                    stack.pop();
                }
            }
        }
        ans[1] = stack.size();
        return ans;
    }
}
