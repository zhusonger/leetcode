package cn.com.lasong.leetcode.bytedance.string;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-16
 * Description:
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1016/
 * 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class CheckInclusion {
    public boolean checkInclusion(String s1/*short*/, String s2/*long*/) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1Table = new int[128];
        char[] s1Array = s1.toCharArray();
        for (int i = 0; i < s1Array.length; i++) {
            s1Table[s1Array[i]/*-'a'*/] += 1;
        }
        char[] s2Array = s2.toCharArray();
        int[] s2Table = new int[128];
        for (int i = 0; i < s2Array.length; i++) {
            s2Table[s2Array[i]/*-'a'*/] += 1;
        }
        // 先进行最简单的判断, 如果目标字符的个数都不够的话, 肯定不包含子串
        for (int i = 0; i < s1Table.length; i++) {
            if (s1Table[i] > s2Table[i]) {
                return false;
            }
        }

        // 用于重置s2Table
        int[] sMatchTable = new int[128];
        int s1Len = s1Array.length;
        int s2Len = s2Array.length;
        for (int i = 0; i < s2Len; i++) {
            // 当s2剩余长度不够s1的长度时, 直接返回false
            if (s1Len + i > s2Len) {
                return false;
            }
            System.arraycopy(sMatchTable, 0, s2Table, 0, sMatchTable.length);
            boolean isCompare = true;
            for (int j = 0; j < s1Len; j++) {
                char s2Char = s2Array[i + j];
                s2Table[s2Char] += 1;
                if (s2Table[s2Char] > s1Table[s2Char]) {
                    isCompare = false;
                    break;
                }
            }

            if (isCompare) {
                boolean isEqual = true;
                for (int j = 0; j < 26; j++) {
                    if(s1Table[j + 'a'] != s2Table[j + 'a']) {
                        isEqual = false;
                        break;
                    }
                }

                if (isEqual) {
                    return true;
                }
            }
        }
        return false;
    }
}
