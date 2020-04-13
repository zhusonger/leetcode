package cn.com.lasong.leetcode.card.queue_stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/13
 * Description:
 * https://leetcode-cn.com/explore/learn/card/queue-stack/218/stack-last-in-first-out-data-structure/878/
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class StringIsValid {

    /**
     * 通过栈来实现一一对应
     * 如果是左边符号就入栈， 如果是右侧符号就跟栈顶元素比较是否是配对符号
     *
     * 如果是都匹配, 栈最后一定是空的 同时空的也不一定是有效的, 就再用个标志标记是否有效
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (null == s || s.isEmpty()) {
            return true;
        }
        char[] array = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        boolean isValid = true;
        for (int i = 0; i < array.length; i++) {
            char item = array[i];
            if (map.containsKey(item)) {
                stack.push(item);
                continue;
            }
            if (stack.isEmpty()) {
                isValid = false;
                break;
            }
            char top = stack.peek();
            if (item == map.get(top)) {
                stack.pop();
                continue;
            }
            isValid = false;
            break;
        }

        return isValid && stack.isEmpty();
    }

    public static void main(String[] args) {
        StringIsValid isValid = new StringIsValid();
        boolean ans = isValid.isValid("()[]{}");
        System.out.println(ans);
        ans = isValid.isValid("(]");
        System.out.println(ans);
        ans = isValid.isValid("{[]}");
        System.out.println(ans);
        ans = isValid.isValid("]");
        System.out.println(ans);
        ans = isValid.isValid("[");
        System.out.println(ans);
    }

}
