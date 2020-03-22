package cn.com.lasong.leetcode.match.weekly181;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-22
 * Description:
 * https://leetcode-cn.com/contest/weekly-contest-181/problems/longest-happy-prefix/
 * 5367. 最长快乐前缀 显示英文描述
 * 用户通过次数570
 * 用户尝试次数1219
 * 通过次数593
 * 提交次数2891
 * 题目难度Hard
 * 「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
 *
 * 给你一个字符串 s，请你返回它的 最长快乐前缀。
 *
 * 如果不存在满足题意的前缀，则返回一个空字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "level"
 * 输出："l"
 * 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
 * 示例 2：
 *
 * 输入：s = "ababab"
 * 输出："abab"
 * 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
 * 示例 3：
 *
 * 输入：s = "leetcodeleet"
 * 输出："leet"
 * 示例 4：
 *
 * 输入：s = "a"
 * 输出：""
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 只含有小写英文字母
 */
public class LongestPrefix {

    public static void main(String[] args) {
        LongestPrefix longestPrefix = new LongestPrefix();
        Random random = new Random();
        char[] chars = new char[100000];
        for (int i = 0; i < 100000; i++) {
            chars[i] = (char) ('a' + random.nextInt(26));
        }
        String s = new String(chars);
        String prefix = longestPrefix.longestPrefix(s);
//        String prefix = longestPrefix.longestPrefix("level");
//        String prefix = longestPrefix.longestPrefix("ababab");
//        String prefix = longestPrefix.longestPrefix("leetcodeleet");
        System.out.println(prefix);
    }

    public String longestPrefix2(String s) {
        if (null == s || s.length() <= 1) {
            return "";
        }
        int len = s.length();
        char[] sArray = s.toCharArray();
        char[] sTables = new char[26];
        // O(N)
        Map<Character, Stack<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char item = sArray[i];
            sTables[item] += 1;
            // 记录每一个字符的位置
            Stack<Integer> p = map.get(item);
            if (p == null) {
                p = new Stack<>();
                map.put(item, p);
            }
            p.push(i);
        }
        List<Character> longestPrefix = new ArrayList<>();
        char preChar = 0;
        // O(N)
        for (int i = 0; i < len; i++) {
            char item = sArray[i];
            if(sTables[item] <= 1) {
                break;
            }
            // 第一个字符
//            if (preChar <= 0) {
//                preChar = item;
//            }
            Stack<Integer> stack = map.get(item);
            // 这个字符不存在
            if (null == stack) {

            }



        }
        return "";
    }
    /**
     * 思路正确 但是超时
     * 优化方法 还是考虑ascii
     * @param s
     * @return
     */
    public String longestPrefix(String s) {
        if (null == s || s.length() <= 1) {
            return "";
        }
        int len = s.length();
        char[] sArray = s.toCharArray();
        char firstChar = sArray[0];
        char lastChar = sArray[len - 1];

        int index = s.lastIndexOf(firstChar);
        // 未找到首字母的对应的字符, 表示没有
        if (index <= 0) {
            return "";
        }
        // 找到首字母所在的位置, 先进后出, 从最后一个开始匹配
        Stack<Integer> fcStack = new Stack<>();
        for (int i = 1; i < len; i++) {
            if(sArray[i] == firstChar) {
                fcStack.push(i);
            }
        }

        // "level"      offset
        // l == l          0
        // e ==
        // "ababab"
        // "leetcodeleet"

        // 就是从后往前取出首字符的位置, 然后跟首字符之后的字符一个个对比, 如果出现不一样的就结束
        // 如果到结尾还是一样, 说明是公共前缀和后缀, 但是这只是现在的
        String longestPrefix = "";
        int offset = 0;
        while (!fcStack.isEmpty()) {
//            index = fcStack.poll();
            index = fcStack.pop();
            offset = 0;
            int prefixEndIndex = len - index - 1;
            // 优化: 先比较最后一个字符, 如果不一样, 不用进行比较了
            if (sArray[prefixEndIndex] != lastChar) {
                continue;
            }

            while (index + offset < len && sArray[offset] == sArray[index + offset]) {
                offset++;
            }
            // 表示到结尾了
            // 这里要考虑最长, 可以再次往前找, 然后继续这样比较, 直到找到不同的
            if (index + offset == len) {
                String prefix = s.substring(0, offset);
                if (prefix.length() > longestPrefix.length()) {
                    longestPrefix = prefix;
                }
            }
        }

        // 没有到结尾
        return longestPrefix;
    }
}
