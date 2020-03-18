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

    public MedianFinder() {

    }

    public void addNum(int num) {
        addNumToLinkedList(num);
    }

    public double findMedian() {
        return findMediaByLinkedList();
    }

    /**
     * 使用一个最小堆来实现, 每次拷贝一份, 然后取中间的值
     * TLM
     * @return
     */
    private PriorityQueue<Integer> heap = new PriorityQueue<>();
    private void addNumToHeap(int num) {
        heap.add(num);
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

    /**
     * 使用系统的排序算法
     * PASS
     * @return
     */
    private List<Integer> arrayList = new ArrayList<>();
    private void addNumToList(int num) {
        arrayList.add(num);
    }
    private double findMediaByListSort() {
        arrayList.sort(null);
        int size = arrayList.size();
        int mid = size / 2;
        double ans = 0;
        if (size % 2 == 0) {
            ans = (arrayList.get(mid) + arrayList.get(mid - 1)) / 2.0f;
        } else {
            ans = arrayList.get(mid);
        }
        return ans;
    }

    /**
     * 使用快速排序算法
     * TLM
     * @return
     */
    private double findMediaByQiuckSort() {
        Integer[] array = new Integer[arrayList.size()];
        arrayList.toArray(array);
        double ans = 0;

        int left = 0;
        int right = array.length - 1;

        Stack<Integer> stack = new Stack<>();
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();
            if (left < right) {
                int pivotIndex = partition(array, left, right);
                stack.push(left);
                stack.push(pivotIndex - 1);
                stack.push(pivotIndex + 1);
                stack.push(right);
            }
        }

        int size = array.length;
        int mid = size / 2;
        if (size % 2 == 0) {
            ans = (array[mid] + array[mid - 1]) / 2.0f;
        } else {
            ans = array[mid];
        }
        return ans;
    }

    private int partition(Integer[] nums, int left, int right) {
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

    /**
     *  使用链表数组来添加, 每次选择一个位置插入
     *  未优化, 可以通过二分法继续对半分
     */
    private List<Integer> linkedList = new LinkedList<>();
    public void addNumToLinkedList(int num) {
        if (linkedList.size() <= 0 || linkedList.get(linkedList.size() - 1) <= num) {
            linkedList.add(num);
            return;
        }
        int midIndex = linkedList.size() / 2;
        int mid = linkedList.get(midIndex);
        int left = 0;
        int right = linkedList.size() - 1;
        if (mid > num) {
            right = midIndex;
        } else {
            left = midIndex;
        }
        for (int i = left; i <= right; i++) {
            if (linkedList.get(i) >= num) {
                linkedList.add(i, num);
                break;
            }
        }
    }

    private double findMediaByLinkedList() {
        int size = linkedList.size();
        int mid = size / 2;
        double ans = 0;
        if (size % 2 == 0) {
            ans = (linkedList.get(mid) + linkedList.get(mid - 1)) / 2.0f;
        } else {
            ans = linkedList.get(mid);
        }
        return ans;
    }

    // 最小堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 最大堆
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    public void addNumToTwoHeap(int num) {

    }

    public double findByHeap(int num) {
        addNumToHeap(num);
        return findMediaByHeap();
    }

    public double findByListSort(int num) {
        addNumToList(num);
        return findMediaByListSort();
    }

    public double findByQuickSort(int num) {
        addNumToList(num);
        return findMediaByQiuckSort();
    }

    public double findByLinkedList(int num) {
        addNumToLinkedList(num);
        return findMediaByLinkedList();
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        int[] nums = new int[] {-1, -2, -3, -4, -5};
//        int[] nums = new int[] {6, 10, 2, 6, 5, 6, 3, 1, 0, 0};
//        int len = 100000;
//        int[] nums = new int[len];
//        Random random = new Random();
//        for (int i = 0; i < len; i++) {
//            nums[i] = random.nextInt(20);
//        }
        for (int item : nums) {
            System.out.println("ans:" + finder.findByLinkedList(item));
        }
    }
}
