package cn.com.lasong.leetcode.everyday;

import cn.com.lasong.leetcode.common.ListHelper;
import cn.com.lasong.leetcode.common.NodeHelper;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/20
 * Description:
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * 面试题40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 *
 */
public class GetLeastNumbers {
    public static void main(String[] args) {
        int[] nums;
        nums = ListHelper.createArray(3);
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
        getLeastNumbers.getLeastNumbers(nums, 2);
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (null == arr || arr.length <= 0 || k <= 0 || k > arr.length) {
            return new int[0];
        }
        // 拿到题目的第一时间, 就想到了那倒无序中位数的题
        // 这里要取 前有序数组, 用最大堆实现
        // 动态规划应该也是可以做的
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if(ListHelper.peekHeap(maxHeap) > arr[i]) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        int[] ans = new int[k];
        int size = maxHeap.size();
        for (int i = 0; i < size; i++) {
            int val = maxHeap.poll();
            ans[i] = val;
        }
        return ans;
    }
}
