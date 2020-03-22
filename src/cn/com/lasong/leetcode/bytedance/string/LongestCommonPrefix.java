package cn.com.lasong.leetcode.bytedance.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-16
 * Description:
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1014/
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // 1。 空或者只有1个的情况
        if (null == strs || strs.length == 1) {
            return null != strs ? strs[0] : "";
        }

        String minStr = null;
        List<String> others = new ArrayList<>();
        // 找出最短的字符串
        for (String str : strs) {
            // 2。 存在空字符串, 没有公共前缀
            if (null == str) {
                return "";
            }
            int preLen = null != minStr ? minStr.length() : Integer.MAX_VALUE;
            int nowLen = str.length();
            if (nowLen < preLen) {
                minStr = str;
            }
            others.add(str);
        }
        // 移除最短的字符串
        others.remove(minStr);
        // 用最短的字符串取逐个比较, 一旦发现不一样的字符, 表示公共前缀结束, 返回最短前缀
        // 如果最短字符串全都满足, 那最短字符串就是公共前缀
        if (minStr != null) {
            char[] array = minStr.toCharArray();
            for (int i = 0; i < array.length; i++) {
                char c = array[i];
                for (String other : others) {
                    char oc = other.charAt(i);
                    if (c != oc) {
                        return minStr.substring(0, i);
                    }
                }
            }
            return minStr;
        }
        return "";
    }
}
