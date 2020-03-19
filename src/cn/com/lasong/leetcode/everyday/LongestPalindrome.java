package cn.com.lasong.leetcode.everyday;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-19
 * Description:
 * https://leetcode-cn.com/problems/longest-palindrome/
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 * “回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        longestPalindrome.longestPalindrome("abccccdd");
    }
    public int longestPalindrome(String s) {
        if (null == s || s.length() <= 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int len = 0;
        for (int i = 0; i < chars.length; i++) {
            char item = chars[i];
            int count = 0;
            if (map.containsKey(item)) {
                count += map.get(item);
            }
            ++count;
            // 如果有偶数位, 表示可以在左右各放置一个, 作为回文数的成员
            // 当然还需要重新计数
            if (count % 2 == 0) {
                len += count;
                count = 0;
            }
            map.put(chars[i], count);
        }
        // 到这里应该都是1或者0
        Set<Character> keys = map.keySet();
        Iterator<Character> iterator = keys.iterator();
        while (iterator.hasNext()) {
            char key = iterator.next();
            int count = map.get(key);
            if (count > 0) {
                len += 1;
                break;
            }
        }
        return len;

    }
}
