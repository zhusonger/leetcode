package cn.com.lasong.leetcode.match.weekly181;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-22
 * Description:
 * https://leetcode-cn.com/contest/weekly-contest-181/problems/four-divisors/
 * 5178. 四因数 显示英文描述
 * 用户通过次数1795
 * 用户尝试次数2505
 * 通过次数1817
 * 提交次数6925
 * 题目难度Medium
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
 *
 * 如果数组中不存在满足题意的整数，则返回 0 。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 *
 *
 */
public class SumFourDivisors {

    public static void main(String[] args) {
        SumFourDivisors sumFourDivisors = new SumFourDivisors();
        int ans = sumFourDivisors.sumFourDivisors(new int[]{1,2,3,4,5,6,7,8,9,10});
        System.out.println(ans);
    }

    /**
     * N = p1^e1 * p2^e2 * p3^e3.... * pn^en
     * 因子个数
     * f(n) = (1 + e1) * (1 + e2) * (1 + e3) *....*(1+en)
     *
     * @param nums
     * @return
     */
    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = getDivisorsCount(nums[i], 4);
            if (set.isEmpty()) {
                continue;
            }

            for (Integer value : set) {
                ans += value;
            }
        }
        return ans;
    }
    private Set<Integer> getDivisorsCount(int num, int targetCount) {
        int ans = 1;
        int count = 0;
        int num_s = num;
        Set<Integer> set = new HashSet<>();
        // 加上1和本身
        set.add(1);
        set.add(num);
        for (int i = 2; i * i <= num; i++) {
            count = 0;
            int divisor = i;
            // 可以被除尽, 表示是它的因数
            while (num % i == 0) {
                // 这里是统计因子
                // 原数字可以被除尽, 并且不为之前记录过的因子, 就记录下来
                if (!set.contains(divisor) && num_s % divisor == 0) {
                    set.add(divisor);
                }
                // 更新被除后新的值
                num = num / i;
                count++;
                divisor *= i;
            }
            // 当前数已经被因子除尽
            // 更新个数
            ans = ans * (1 + count);
            // 如果超过目标个数, 就退出, 不是我们的目标
            if (ans > targetCount) {
                break;
            }
        }

        // 表示最后相除还没有除尽, 这也是其中一个因子
        if (num > 1) {
            set.add(num);
            ans = ans * (1 + 1);
        }
        // 结果不是目标个数, 就清空数组
        if (ans != targetCount) {
            set.clear();
        }
        return set;
    }
}
