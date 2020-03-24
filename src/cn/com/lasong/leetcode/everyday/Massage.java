package cn.com.lasong.leetcode.everyday;

import cn.com.lasong.leetcode.common.ListHelper;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/24
 * Description:
 * https://leetcode-cn.com/problems/the-masseuse-lcci/
 * 面试题 17.16. 按摩师
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *
 * 注意：本题相对原题稍作改动
 *
 *
 *
 * 示例 1：
 *
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * 示例 2：
 *
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * 示例 3：
 *
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 */
public class Massage {

    public static void main(String[] args) {
        Massage massage = new Massage();
//        int ans = massage.massage(new int[]{2,1,1,2});
        int ans = massage.massage(new int[]{2,1,4,5,3,1,1,3});
        System.out.println(ans);
    }
    public int massage(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        // dp[i]记录的是到当前位置i时的最优值
        int[] dp = new int[len];
        // 2, 1,1,2
        // dp[0] = 2
        // dp[1] = 2
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
//        ListHelper.print(nums);
        for (int i = 2; i < len; i++) {
            // dp[2] = Math.max(1 + 2, 2) = 3
            // dp[3] = Math.max(2 + 2, 2) = 4
            // 这里的含义就是: 当前数与前间隔的数字最优解累加 是否大于 前面的最优解
            // 如果累加值小于前面的最优解, 就忽略当前数字, 更新当前位置的最优解为之前那个
            // 否则就累加上当前数字与前面间隔的累积值 更新当前最优值
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
//            System.out.println(dp[i]);
        }
//        ListHelper.print(dp);
        return dp[len - 1];
    }
}
