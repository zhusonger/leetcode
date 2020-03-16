package cn.com.lasong.leetcode.bytedance.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-16
 * Description:
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1012/
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        char[] array = s.toCharArray();
        int len = array.length;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            char item = array[i];
            if (map.containsKey(item)) {
                // 更新起始位置为相同字符的下一个
                // 这里的max很精髓, 就是更新start, 但是没去去清除start之前的字符
                // 导致可能会有本该忽略进入条件的, 进入到了这个里面
                // max省略掉了remove start之前字符串的工作
                start = Math.max(map.get(item) + 1, start);
            }
            // end - start 是表示在本字符串添加之前的长度, 这次再新增1个
            maxLen = Math.max(maxLen, (end - start) + 1);
            map.put(item, i);
            // 每次右移1位
            end++;
        }
        return maxLen;
    }

}
