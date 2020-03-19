package cn.com.lasong.leetcode.bytedance.array_sort;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.*;

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
        int len = 10;
        int[] nums = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(20);
        }
        int[] temp = new int[len];
        System.arraycopy(nums, 0, temp, 0, len);
        ListHelper.print(sortArray.bubbleSort(temp));
        System.arraycopy(nums, 0, temp, 0, len);
        ListHelper.print(sortArray.selectionSort(temp));
        System.arraycopy(nums, 0, temp, 0, len);
        ListHelper.print(sortArray.insertSort(temp));
        System.arraycopy(nums, 0, temp, 0, len);
        ListHelper.print(sortArray.quickSort(temp));
        System.arraycopy(nums, 0, temp, 0, len);
        ListHelper.print(temp);
        sortArray.aiaj(temp);
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

    /**
     * 插入排序
     * @param nums
     * @return
     */
    public List<Integer> insertSort(int[] nums) {
        if (null == nums) {
            return null;
        }
        int len = nums.length;
        List<Integer> list = new ArrayList<>(len);

        int count = 0;
        if (len > 2) {
            for (int i = 1; i < len; i++) {
                int val = nums[i];
                int insertIndex = i;
                for (int j = insertIndex; j > 0 && val < nums[j-1]; j--) {
                    // 选取的值 比 前面的值小
                    // 前面的值后移一位
                    nums[j] = nums[j - 1];
                    insertIndex = j - 1;
                    count++;
                }
                // 到最后没有后移的位置, 更新的选择的值
                nums[insertIndex] = val;
            }
        }
        for (int num : nums) {
            list.add(num);
        }
        System.out.println("插入循环次数:" + count);
        return list;
    }

    /**
     * 快速排序
     * @param nums
     * @return
     */
    int quickCount = 0;
    public List<Integer> quickSort(int[] nums) {
        if (null == nums) {
            return null;
        }
        quickCount = 0;
        int len = nums.length;
        List<Integer> list = new ArrayList<>(len);

        if (len > 2) {
//            int left = 0;
//            int right = nums.length - 1;
//            quickSortRecur(nums, left, right);
            quickSortNoRecur(nums);
        }
        for (int num : nums) {
            list.add(num);
        }
        System.out.println("快速循环次数:" + quickCount);
        return list;
    }

    /**
     * 快速排序非递归
     * 模拟了递归入栈出栈的过程
     * @param nums
     */
    private void quickSortNoRecur(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int right = nums.length - 1;
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();
            if (left < right) {
                int pivotIndex = partitionLeft(nums, left, right);
                stack.push(left);
                stack.push(pivotIndex - 1);
                stack.push(pivotIndex + 1);
                stack.push(right);
            }
        }
    }
    /**
     * 递归版本快速排序
     * @param nums
     * @param left
     * @param right
     */
    private void quickSortRecur(int[] nums, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionRight(nums, left, right);
            quickSortRecur(nums, 0, pivotIndex - 1);
            quickSortRecur(nums, pivotIndex + 1, right);
        }
    }
    /*
     * 这里有左右之分的关系是, 基准位置不同, 判断的顺序就不同,
     * 假如以右侧为基准值, 那我交换之后, 最后跟基准值交换的值, 是希望它是大于基准值的, 这样交换之后顺序才正确,
     * 所以 if (nums[pLeft] <= pivot) 放到前面, 这样不满足条件就是这个值比基准值大
     *
     * 同理对应左侧基准值, 最后交换的是希望它是小于基准值的, 因为左侧代表小的, 这样交换之后顺序才正确
     * 所以 if (nums[pRight] >= pivot) 放在前面, 不满足条件就是这个值比基准值小, 就把基准值放到右边去
     *
     * 关于随机选择基准值, 我看网上基本都是通过先随机选取一个值, 然后换到left/right再进行分治方法
     *
     * 一样有模板
     * 理解了判断条件的关系, 就可以换成右侧基准
     *
     * int pLeft = left;
     * int pRight = right;
     * int pivot = nums[left];
     * while(pLeft < pRight) {
     *      if (nums[pRight] >= pivot) {
     *          pRight--; // 向左移动
     *      }
     *      else if (nums[pLeft] < pivot) {
     *          pLeft++; // 向右移动
     *      } else {
     *          int temp = nums[pLeft];
     *          nums[pLeft] = nums[pRight];
     *          nums[pRight] = temp;
     *      }
     * }
     * // 最后交换基准值与最后的值
     * nums[left] = nums[pLeft];
     * nums[pLeft] = pivot;
     * return pLeft;
     */
    /**
     * 右侧基准 快速排序分治
     * @param nums
     * @param left
     * @param right
     */
    private int partitionRight(int[] nums, int left, int right) {
        int pLeft = left;
        int pRight = right;

        // ====以右侧为基准时的值===
        int pivot = nums[right];
        while (pLeft < pRight) {
            quickCount++;
            // ====以右侧为基准时的判断顺序===
            // 右边的值已经 比 锚点值 大, 往前移动
            if (nums[pLeft] <= pivot) {
                pLeft++;
            }
            // 左边的值已经 比 锚点值 小, 往后移动
            else if (nums[pRight] >= pivot) {
                pRight--;
            }
            // 找到2个在基准值两侧的, 交换
            else {
                int tmp = nums[pLeft];
                nums[pLeft] = nums[pRight];
                nums[pRight] = tmp;
            }
        }
//        0,0,0,9
//        4,4,1,9
//        7,7,5,9
//        8,8,8,9
//        6,6,5,6
//        1,1,1,3
//        3,3,2,3
//        System.out.println(pLeft+"," + pRight +"," + left+","+ right);
        // 更新基准值到顺序中的位置
        // ====以右侧为基准时的值===
        nums[right] = nums[pLeft];

        nums[pLeft] = pivot;
        return pLeft;
    }
    /**
     * 左侧基准 快速排序分治
     * @param nums
     * @param left
     * @param right
     */
    private int partitionLeft(int[] nums, int left, int right) {
        int pLeft = left;
        int pRight = right;

        // ====以左侧为基准时的值===
        int pivot = nums[left];
        while (pLeft < pRight) {
            quickCount++;
            // ====以左侧为基准时的判断顺序===
            // 右边的值已经 比 锚点值 大, 往前移动
            if (nums[pRight] >= pivot) {
                pRight--;
            }
            // 左边的值已经 比 锚点值 小, 往后移动
            else if (nums[pLeft] <= pivot) {
                pLeft++;
            }
            // 找到2个在基准值两侧的, 交换
            else {
                int tmp = nums[pLeft];
                nums[pLeft] = nums[pRight];
                nums[pRight] = tmp;
            }
        }
//        4,4,0,9
//        5,5,5,9
//        9,9,6,9
//        7,7,6,8
//        0,0,0,3
//        3,3,1,3
//        1,1,1,2
//        System.out.println(pLeft+"," + pRight +"," + left+","+ right);
        // 更新基准值到顺序中的位置
        // ====以左侧为基准时的值===
        nums[left] = nums[pLeft];

        nums[pLeft] = pivot;
        return pLeft;
    }


    public void aiaj(int[] nums) {
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            count = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[j-1]) {
                    count++;
                    continue;
                }
                if (count > 0) {
                    j--;
                    System.out.println(i+","+j+", ai:" + nums[i]+", aj:"+nums[j]);
                }
                break;
            }
        }
    }

}
