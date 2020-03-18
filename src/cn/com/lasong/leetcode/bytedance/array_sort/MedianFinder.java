package cn.com.lasong.leetcode.bytedance.array_sort;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/18
 * Description:
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */
public class MedianFinder {

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    /** initialize your data structure here. */
    private PriorityQueue<Integer> heap = new PriorityQueue<>();
    private List<Integer> list = new ArrayList<>();
    private Comparator comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };
    public MedianFinder() {
        heap.clear();
    }

    public void addNum(int num) {
        heap.add(num);
        list.add(num);
    }

    public double findMedian() {
        return findMediaByList();
    }

    // TLM
    private double findMediaByHeap() {
        PriorityQueue<Integer> tmp = new PriorityQueue<>(heap);
        int size = tmp.size();
        Set<Integer> set = new HashSet<>();
        // 奇数, 取中间值
        set.add(size / 2);
        // 偶数, 取中间平均
        if (size % 2 == 0) {
            set.add(size / 2 - 1);
        }
        int currentIndex = 0;
        int sum = 0;
        int sumLen = set.size();
        // 找完需要的值, 退出
        while (!tmp.isEmpty() && !set.isEmpty()) {
            Integer value = tmp.poll();
            if (set.contains(currentIndex)) {
                sum += value;
                set.remove(currentIndex);
            }
            currentIndex++;
        }
        return sum * 1.0f / sumLen;
    }
    // Pass
    private double findMediaByList() {
        list.sort(comparator);
        for (Integer item : list) {
            System.out.println(item);
        }
        int size = list.size();
        int mid = size / 2;
        double ans = 0;
        if (size % 2 == 0) {
            ans = (list.get(mid) + list.get(mid - 1)) / 2.0f;
        } else {
            ans = list.get(mid);
        }
        return ans;
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        System.out.println("1, 2");
        double ans = finder.findMedian();
        System.out.println("ans:" + ans);
        finder.addNum(3);
        System.out.println("1, 2, 3");
        ans = finder.findMedian();
        System.out.println("ans:" + ans);
    }
}
