package cn.com.lasong.leetcode.kuaishou;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.Arrays;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/15
 * Description:
 * 2个数组 a, b
 * 求和 ai * (i - 1) + bi * (n - i)最小
 *
 */
public class B_SumMin {

    public static void main(String[] args) {
        B_SumMin sumMin = new B_SumMin();
        int[] ans = sumMin.waitSumMin(new int[]{8, 9, 7}, new int[]{5, 8, 3});
        ListHelper.print(ans);
    }
    /**
     * 拆分计算公式
     * = ai*i - ai + bi*n - bi*i
     * = (ai - bi)*i + (bi*n-ai)
     *
     * bi*n-ai部分为固定的常量
     * 所以演化成求(ai - bi)*i最小
     * 那就是让ai - bi小, i越大
     * 即按照ai - bi降序,
     * ai - bi大的在前, i最小
     * ai - bi小的在后, i最大
     * @param a
     * @param b
     * @return
     */
    public int[] waitSumMin(int[] a, int[] b) {
        int len = a.length;
        int[] sum = new int[len];
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            sum[i] = a[i] - b[i];
            ans[i] = i + 1;
        }
        // 使用快排排序sum 和 ans
        quickSort(sum, 0, sum.length - 1, ans);
        // 再对结果逆向变成降序
        for (int i = 0; i < len/2; i++) {
            int tmp = ans[i];
            ans[i] = ans[len - i - 1];
            ans[len - i - 1] = tmp;
        }
        return ans;
    }

    private void quickSort(int[] num, int left, int right, int[] ans) {
        if (left < right) {
            int pivotIndex = part(num, left, right, ans);
            quickSort(num, left, pivotIndex - 1, ans);
            quickSort(num,pivotIndex + 1, right, ans);
        }
    }

    private int part(int[] num, int left, int right, int[] ans) {
        int pLeft = left;
        int pRight = right;
        int pivot = num[left];
        int ans_pivot = ans[left];
        while (pLeft < pRight) {
            if (num[pRight] >= pivot) {
                pRight--;
            } else if (num[pLeft] <= pivot) {
                pLeft++;
            } else {
                int tmp = num[pLeft];
                num[pLeft] = num[pRight];
                num[pRight] = tmp;

                tmp = ans[pLeft];
                ans[pLeft] = ans[pRight];
                ans[pRight] = tmp;
            }
        }
        num[left] = num[pLeft];
        num[pLeft] = pivot;

        ans[left] = ans[pLeft];
        ans[pLeft] = ans_pivot;
        return pLeft;
    }

}
