package cn.com.lasong.leetcode.bytedance.array_sort;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/18
 * Description:
 * https://leetcode-cn.com/problems/two-sum/
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
//        int[] ans = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
//        int[] ans = twoSum.twoSum(new int[]{3, 3}, 6);
        int[] ans = twoSum.twoSum(new int[]{-3,4,3,90}, 0);
        ListHelper.print(ans);
    }

    public int[] twoSum(int[] nums, int target) {
        return hashMap(nums, target);
    }

    /**
     * 暴力方法, 双层循环
     * @param nums
     * @param target
     * @return
     */
    private int[] brute(int[] nums, int target) {
        int[] ans = new int[2];
        boolean find = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    find = true;
                    break;
                }
            }

            if (find) {
                break;
            }
        }
        return ans;
    }

    /**
     * 通过hashmap实现
     * @param nums
     * @param target
     * @return
     */
    private int[] hashMap(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 不需要值比target大的数
//            if (num > target) {
//                continue;
//            }
            Set<Integer> set = map.get(num);
            if (null == set) {
                set = new HashSet<>();
                map.put(num, set);
            }
            set.add(i);
        }

        Iterator<Integer> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            int num1 = keys.next();
            int num2 = target - num1;
            Set<Integer> indexSet;
            if (num1 == num2) {
                indexSet = map.get(num1);
                Integer[] indexArray = new Integer[indexSet.size()];
                indexSet.toArray(indexArray);
                ans[0] = indexArray[0];
                ans[1] = indexArray[1];
                break;
            }
            if ((indexSet = map.get(num2)) != null) {
                Integer[] indexArray = new Integer[indexSet.size()];
                indexSet.toArray(indexArray);
                ans[1] = indexArray[0];

                indexSet = map.get(num1);
                indexArray = new Integer[indexSet.size()];
                indexSet.toArray(indexArray);
                ans[0] = indexArray[0];
                break;
            }
        }

        return ans;
    }
}
