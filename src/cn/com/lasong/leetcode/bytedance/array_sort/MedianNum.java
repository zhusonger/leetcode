package cn.com.lasong.leetcode.bytedance.array_sort;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import static cn.com.lasong.leetcode.common.ListHelper.peekHeap;
import static cn.com.lasong.leetcode.common.ListHelper.pollHeap;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/19
 * Description:
 */
public class MedianNum {

    public static void main(String[] args) {
        MedianNum medianNum = new MedianNum();
        int[] nums;
        nums = ListHelper.createArray(7);
//        nums = new int[] {-1, -2, -3, -4, -5};
        double ans;
        ans = medianNum.findMedianByQuickSort(nums);
        ListHelper.print(nums);
        System.out.println(ans);

        ans = medianNum.findMediaByMinHeap(nums);
        ListHelper.print(nums);
        System.out.println(ans);

        ans = medianNum.findMediaByTwoHeap(nums);
        ListHelper.print(nums);
        System.out.println(ans);
    }

    /**
     * 使用最大堆跟最小堆来实现中位数查找
     * @param nums
     * @return
     */
    public double findMediaByTwoHeap(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int heapSize = nums.length / 2 + 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(heapSize);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(heapSize, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();
            int maxHeapTop = peekHeap(maxHeap);
            int minHeapTop = peekHeap(minHeap);
            // 需要保证最后大小堆相等, 或者相差1
            // 1. 如果当前值 比 最大堆的顶要小, 说明, 应该在最大堆(前有序数组)
            if (value <= maxHeapTop) {
                // 1.1 如果最大堆的个数大于最小堆, 做一个平衡, 把最大堆的顶(前有序数组的最后一位) 放到 最小堆(后有序数组)
                if (maxHeapSize > minHeapSize) {
                    maxHeap.poll();
                    minHeap.add(maxHeapTop);
                }
                maxHeap.add(value);
            }
            // 2. 当这里表示, 当前值肯定比最大堆顶(前有序数组的最后一位最大值)大
            // 如果当前值 比 最小堆顶(后有序数组)大, 表示是在最小堆内 (后有序数组))
            else if (value >= minHeapTop) {
                // 2.1 如果最小堆的个数大于最大堆, 就取最小堆的顶(后有序最小值) 到最大堆
                if (minHeapSize > maxHeapSize) {
                    minHeap.poll();
                    maxHeap.add(minHeapTop);
                }
                minHeap.add(value);
            }
            // 3. 在2个堆的中间, 那就要看堆大小来决定放哪里
            else {
                // 谁个数少放谁那, 相等就随便放哪
                if (maxHeapSize < minHeapSize) {
                    maxHeap.add(value);
                } else if (minHeapSize < maxHeapSize) {
                    minHeap.add(value);
                } else {
                    maxHeap.add(value);
                }
            }
        }

        double ans = 0;
        int maxHeapTop = peekHeap(maxHeap);
        int minHeapTop = peekHeap(minHeap);
        int maxHeapSize = maxHeap.size();
        int minHeapSize = minHeap.size();

        // 偶数个, 取最大堆 跟 最小堆顶的平均值
        if (nums.length % 2 == 0) {
            ans = (maxHeapTop + minHeapTop) / 2.0f;
        } else {
            ans = maxHeapSize > minHeapSize ? maxHeapTop : minHeapTop;
        }
        return ans;
    }

    /**
     * 使用最小堆实现中位数
     * 最小堆的堆顶保证是最小的值(其他不一定)
     * 同样思路最大堆也可以实现
     * @param nums
     * @return
     */
    public double findMediaByMinHeap(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        double ans = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int len = nums.length;
        // +1 是考虑偶数的情况下需要取到中间两位值
        int heapSize = len / 2 + 1;
        for (int i = 0; i < heapSize; i++) {
            minHeap.add(nums[i]);
        }

        for (int i = heapSize; i < len; i++) {
            Integer value = minHeap.peek();
            if (null == value) {
                continue;
            }
            if (value < nums[i]) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }
        if (len % 2 == 0) {
            ans = (pollHeap(minHeap) + pollHeap(minHeap)) / 2.0f;
        } else {
            ans = pollHeap(minHeap);
        }
        return ans;
    }
    /**
     * 快排后找到中位数
     * @param nums
     * @return
     */
    public double findMedianByQuickSort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        // 模拟入栈出栈
        Stack<Integer> stack = new Stack<>();
        int left, right;
        stack.push(0);
        stack.push(nums.length - 1);
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();
            if (left < right) {
                int pivotIndex = partition(nums, left, right);
                stack.push(left);
                stack.push(pivotIndex - 1);
                stack.push(pivotIndex + 1);
                stack.push(right);
            }
        }
        double ans;
        if (nums.length % 2 == 0) {
            ans = (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2.0f;
        } else {
            ans = nums[nums.length / 2];
        }
        return ans;
    }

    private int partition(int[] nums, int left, int right) {
        int pLeft = left;
        int pRight = right;
        int pivot = nums[left];

        while (pLeft < pRight) {
            if (nums[pRight] >= pivot) {
                pRight--;
            } else if (nums[pLeft] <= pivot) {
                pLeft++;
            } else {
                int temp = nums[pLeft];
                nums[pLeft] = nums[pRight];
                nums[pRight] = temp;
            }
        }

        nums[left] = nums[pLeft];
        nums[pLeft] = pivot;
        return pLeft;
    }
}
