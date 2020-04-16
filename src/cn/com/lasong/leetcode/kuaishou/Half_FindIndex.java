package cn.com.lasong.leetcode.kuaishou;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/16
 * Description:
 * 给定一个有序数组
 *
 * 每个数字都是成对出现的, 找出没有成对的数字, 并返回下标
 *
 * 要求时间复杂度O(logN)
 *
 * Input:
 * 11 2 33
 */
public class Half_FindIndex {

    public static void main(String[] args) {
        Half_FindIndex findIndex = new Half_FindIndex();
        int index = findIndex.findIndex(new int[]{1, 1, 2, 3, 3});
        System.out.println(index);
    }

    public int findIndex(int[] num) {
        // 时间复杂度为logN, 那就是二分法
        // 成对出现, 那前面一半偶数个肯定是成对的, 如果不成对那就是这个下标
        int length = num.length;
        boolean find = false;
        int start = 0;
        int end = length;
        int ans = 0;
        while (!find) {
            int index = start + (end - start) / 2;
            if (index % 2 != 0) {
                index += 1;
            }
            // 如果是成对出现, 那应该是在右边
            if (num[index] == num[index - 1]) {
                start = index;
                continue;
            }

            // 不是成对出现
            find = true;
            ans = index;
        }
        return ans;
    }
}
