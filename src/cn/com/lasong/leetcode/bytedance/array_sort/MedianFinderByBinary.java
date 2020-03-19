package cn.com.lasong.leetcode.bytedance.array_sort;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/19
 * Description:
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 *
 面试题41. 数据流中的中位数
 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

 例如，

 [2,3,4] 的中位数是 3

 [2,3] 的中位数是 (2 + 3) / 2 = 2.5

 设计一个支持以下两种操作的数据结构：

 void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 double findMedian() - 返回目前所有元素的中位数。
 示例 1：

 输入：
 ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 [[],[1],[2],[],[3],[]]
 输出：[null,null,null,1.50000,null,2.00000]
 示例 2：

 输入：
 ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 [[],[2],[],[3],[]]
 输出：[null,null,2.00000,null,2.50000]


 限制：

 最多会对 addNum、findMedia进行 50000 次调用。
 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/

 每次插入都进行二分查找插入

 */
public class MedianFinderByBinary {

    public static void main(String[] args) {
        MedianFinderByBinary medianFinderByTwoHeap = new MedianFinderByBinary();
        int[] nums;
        nums = ListHelper.createArray(11);
        ListHelper.print(nums);
        for (int n : nums) {
            medianFinderByTwoHeap.addNum(n);
            System.out.println(medianFinderByTwoHeap.findMedian());
        }

        ListHelper.print(medianFinderByTwoHeap.nums, medianFinderByTwoHeap.len);
    }
    /** initialize your data structure here. */
    public MedianFinderByBinary() {

    }
    int[] nums = new int[10];
    int len = 0;
    public void addNum(int num) {
        // 扩容
        if (len >= nums.length) {
            int size = nums.length;
            size = size + size;
            nums = Arrays.copyOf(nums, size);
        }

        int index = 0;
//        index = Arrays.binarySearch(nums, 0, len, num);
//        if (index < 0) {
//            index = -(index) - 1;
//        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < num) {
                left = mid + 1;
            } else if (nums[mid] > num) {
                right = mid - 1;
            } else {
                // find & break
                index = mid;
                break;
            }
            // not found
            // update index from left
            index = left;
        }


        System.arraycopy(nums, index, nums, index + 1, len - index);
        nums[index] = num;
        len++;
    }

    public double findMedian() {
        if (len <= 1) {
            return nums[0];
        }
        double ans = 0;
        int mid = len >> 1;
        if (len % 2 == 0) {
            ans = (nums[mid] + nums[mid - 1]) / 2.0f;
        } else {
            ans = nums[mid];
        }
        return ans;
    }
}
