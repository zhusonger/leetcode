package cn.com.lasong.leetcode.bytedance.array_sort;

import cn.com.lasong.leetcode.common.ListHelper;

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

 使用2个堆来实现动态更新中位数
 */
public class MedianFinderByTwoHeap {

    public static void main(String[] args) {
        MedianFinderByTwoHeap medianFinderByTwoHeap = new MedianFinderByTwoHeap();
        int[] nums;
        nums = ListHelper.createArray(7);
        for (int n : nums) {
            medianFinderByTwoHeap.addNum(n);
            System.out.println(medianFinderByTwoHeap.findMedian());
        }
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    /** initialize your data structure here. */
    public MedianFinderByTwoHeap() {
        minHeap.clear();
        maxHeap.clear();
    }

    public void addNum(int num) {
        int value = num;
        int maxHeapSize = maxHeap.size();
        int minHeapSize = minHeap.size();

        int maxHeapTop = peekHeap(maxHeap);
        int minHeapTop = peekHeap(minHeap);
        // 需要保证最后大小堆相等, 或者相差1
        // 2. 如果当前值 比 最大堆的顶要小, 说明, 应该在最大堆(前有序数组)
        if (value <= maxHeapTop) {
            // 2.1 如果最大堆的个数大于最小堆, 做一个平衡, 把最大堆的顶(前有序数组的最后一位) 放到 最小堆(后有序数组)
            if (maxHeapSize > minHeapSize) {
                maxHeap.poll();
                minHeap.add(maxHeapTop);
            }
            maxHeap.add(value);
        }
        // 3. 当这里表示, 当前值肯定比最大堆顶(前有序数组的最后一位最大值)大
        // 如果当前值 比 最小堆顶(后有序数组)大, 表示是在最小堆内 (后有序数组))
        else if (value >= minHeapTop) {
            // 3.1 如果最小堆的个数大于最大堆, 就取最小堆的顶(后有序最小值) 到最大堆
            if (minHeapSize > maxHeapSize) {
                minHeap.poll();
                maxHeap.add(minHeapTop);
            }
            minHeap.add(value);
        }
        // 4. 在2个堆的中间, 那就要看堆大小来决定放哪里
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

    public double findMedian() {
        double ans = 0;
        int maxHeapTop = peekHeap(maxHeap);
        int minHeapTop = peekHeap(minHeap);
        int maxHeapSize = maxHeap.size();
        int minHeapSize = minHeap.size();

        int length = maxHeapSize + minHeapSize;
        // 偶数个, 取最大堆 跟 最小堆顶的平均值
        if (length % 2 == 0) {
            ans = (maxHeapTop + minHeapTop) / 2.0f;
        } else {
            ans = maxHeapSize > minHeapSize ? maxHeapTop : minHeapTop;
        }
        return ans;
    }

    /**
     * 安全取出堆顶
     * @param heap
     * @return
     */
    public int pollHeap(PriorityQueue<Integer> heap) {
        if (null == heap || heap.peek() == null) {
            return Integer.MIN_VALUE;
        }

        return heap.poll();
    }

    /**
     * 安全得到堆顶
     * @param heap
     * @return
     */
    public int peekHeap(PriorityQueue<Integer> heap) {
        if (null == heap || heap.peek() == null) {
            return Integer.MIN_VALUE;
        }

        return heap.peek();
    }

}
