package cn.com.lasong.leetcode.common;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 */
public class ListHelper {

    public static void print(List<?> list) {
        if (null == list) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (Object item : list) {
            builder.append(item).append(",");
        }
        if(builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }

    public static void print(int[] ans, int len) {
        if (null == ans) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(ans[i]);

        }
        print(list);
    }

    public static void print(int[] ans) {
        print(ans, ans.length);
    }

    public static void print(int[][] ans) {
        int length = ans.length;
        for (int i = 0; i < length; i++) {
            print(ans[i], ans[i].length);
        }

    }

    /**
     * 生成随机数 数组
     * @param len
     * @return
     */
    public static int[] createArray(int len, int offset) {
        if (len <= 0) {
            return null;
        }
        int[] nums = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(20) + offset;
        }
        return nums;
    }
    public static int[] createArray(int len) {
        return createArray(len, 0);
    }
    /**
     * 安全取出堆顶
     * @param heap
     * @return
     */
    public static int pollHeap(PriorityQueue<Integer> heap) {
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
    public static int peekHeap(PriorityQueue<Integer> heap) {
        if (null == heap || heap.peek() == null) {
            return Integer.MIN_VALUE;
        }

        return heap.peek();
    }
}
