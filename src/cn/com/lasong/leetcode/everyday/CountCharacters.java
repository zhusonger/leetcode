package cn.com.lasong.leetcode.everyday;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 *
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 *  
 *
 * 提示：
 *
 * 1. 1 <= words.length <= 1000
 * 2. 1 <= words[i].length, chars.length <= 100
 * 3. 所有字符串中都仅包含小写英文字母
 */
public class CountCharacters {

    public static void main(String[] args) {
        CountCharacters countCharacters = new CountCharacters();
        int count = countCharacters.countCharacters(new String[]{"cat","bt","hat","tree"},"atach");
        System.out.println(count);
    }

    /**
     * 之前移动窗口上看到的关于ASCII的思路
     * 就是比较『词汇表』（字符串数组） words每个字符串的字母个数与『字母表』（字符串） chars的字母个数进行比较
     * 如果字符串 的字符个数都小于 字母表提供的单词个数, 就通过, 并累加
     * 否则不通过, 忽略进行下一个单词
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        if (null == words || words.length == 0 || null == chars || chars.length() == 0) {
            return 0;
        }

        int totalWordSum = 0;
        int[] wordTable = new int[26];
        int[] charsTable = new int[26];
        // 用于重置wordTable的数组
        int[] DEFAULT = new int[26];
        char[] charsArray = chars.toCharArray();
        for (char charsVal : charsArray) {
            int index = charsVal - 'a';
            charsTable[index] += 1;
        }
        for (String item : words) {
            if (null == item) {
                continue;
            }
            System.arraycopy(DEFAULT, 0, wordTable, 0, DEFAULT.length);
            char[] array = item.toCharArray();
            for (char wordChar : array) {
                int index = wordChar - 'a';
                wordTable[index] += 1;
            }

            boolean isOwn = true;
            for (int i = 0; i < 26; i++) {
                if(wordTable[i] > charsTable[i]) {
                    isOwn = false;
                    break;
                }
            }

            if (isOwn) {
                totalWordSum += array.length;
            }
        }
        return totalWordSum;
    }
}
