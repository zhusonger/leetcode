package cn.com.lasong.leetcode.bytedance.array_sort;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int ans = maxSubArray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(ans);
    }

    /**
     * 在做这题之前 一直不太理解动态规划是什么
     * 其实简单理解就是使用已经得出的结果, 在这个结果的基础上进行下一步运算
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int ans = method2(nums);
        return ans;
    }

    /**
     * 贪心算法
     * @param nums
     * @return
     */
    private int method1(int[] nums) {
        int ans = 0;
        int length = nums.length;
        int sum = 0;
        // 每次都会比较
        // == 本次最大值 ==
        // 如果本次相加结果比较 与 当前值比较
        // 1. 之前和 反而没有当前值大, 就使用当前值作为和重新计算
        // 2. 否则还是使用累加的值
        // == 全局最大值 ==
        // 每一步都与之前全局的最大值比较, 取大值作为结果
        for (int i = 0; i < length; i++) {
            sum = Math.max(nums[i], nums[i] + sum);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int method2(int[] nums) {
        int length = nums.length;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            // 之前的位置更新为
            int index = Math.max(0, i - 1);
            int pre = nums[index];
            int current = nums[i];
            // 如果前面的值大于等于0 表示还是正直, 向上增长的,
            // 否则就是负值, 添加没有意义, 重新计数
            if (pre >= 0) {
                nums[i] = current + pre;
            }
            // 更新当前值
            current = nums[i];
            ans = Math.max(current, ans);
        }

        return ans;
    }
}
