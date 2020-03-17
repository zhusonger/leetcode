package cn.com.lasong.leetcode.bytedance.array_sort;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * https://leetcode-cn.com/problems/sort-an-array/
 * 给定一个整数数组 nums，将该数组升序排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：[5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -50000 <= A[i] <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * https://blog.csdn.net/qq_40794973/article/details/102863580
 */
public class SortArray {

    public static void main(String[] args) {
        SortArray sortArray = new SortArray();
        int[] nums = new int[]{5,2,3,1};
        ListHelper.print(sortArray.bubbleSort(nums));
        ListHelper.print(sortArray.selectionSort(nums));
    }
    public List<Integer> sortArray(int[] nums) {
        List<Integer> list = bubbleSort(nums);
        return list;
    }

    /**
     * 冒泡
     * @param nums
     * @return
     */
    public List<Integer> bubbleSort(int[] nums) {
        if (null == nums) {
            return null;
        }
        int len = nums.length;
        List<Integer> list = new ArrayList<>(len);
        int count = 0;
        if (len > 2) {
            for (int i = 0; i < len; i++) {
                boolean swap = false;
                for (int j = 0; j < len - i - 1; j++) {
                    if(nums[j] > nums[j + 1]) {
                        int temp = nums[j];
                        nums[j] = nums[j + 1];
                        nums[j + 1] = temp;
                        swap = true;
                    }
                    count++;
                }
                if (!swap) {
                    break;
                }
            }
        }

        for (int num : nums) {
            list.add(num);
        }
        System.out.println("冒泡循环次数:" + count);
        return list;
    }

    /**
     * 选择排序
     * @param nums
     * @return
     */
    public List<Integer> selectionSort(int[] nums) {
        if (null == nums) {
            return null;
        }
        int len = nums.length;
        List<Integer> list = new ArrayList<>(len);

        int count = 0;
        if (len > 2) {
            for (int i = 0; i < len; i++) {
                int minIndex = i;
                for (int j = i; j < len; j++) {
                    if(nums[j] < nums[minIndex]) {
                        minIndex = j;
                    }
                    count++;
                }

                if (i != minIndex) {
                    int tmp = nums[i];
                    nums[i] = nums[minIndex];
                    nums[minIndex] = tmp;
                }

            }
        }
        for (int num : nums) {
            list.add(num);
        }
        System.out.println("选择循环次数:" + count);
        return list;
    }


}
