package cn.com.lasong.leetcode.match.weekly181;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.Arrays;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-22
 * Description:
 * https://leetcode-cn.com/contest/weekly-contest-181/problems/create-target-array-in-the-given-order/
 * 5364. 按既定顺序创建目标数组 显示英文描述
 * 用户通过次数2709
 * 用户尝试次数2800
 * 通过次数2754
 * 提交次数3330
 * 题目难度Easy
 * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
 *
 * 目标数组 target 最初为空。
 * 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
 * 重复上一步，直到在 nums 和 index 中都没有要读取的元素。
 * 请你返回目标数组。
 *
 * 题目保证数字插入位置总是存在。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,2,3,4], index = [0,1,2,2,1]
 * 输出：[0,4,1,3,2]
 * 解释：
 * nums       index     target
 * 0            0        [0]
 * 1            1        [0,1]
 * 2            2        [0,1,2]
 * 3            2        [0,1,3,2]
 * 4            1        [0,4,1,3,2]
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4,0], index = [0,1,2,3,0]
 * 输出：[0,1,2,3,4]
 * 解释：
 * nums       index     target
 * 1            0        [1]
 * 2            1        [1,2]
 * 3            2        [1,2,3]
 * 4            3        [1,2,3,4]
 * 0            0        [0,1,2,3,4]
 * 示例 3：
 *
 * 输入：nums = [1], index = [0]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length, index.length <= 100
 * nums.length == index.length
 * 0 <= nums[i] <= 100
 * 0 <= index[i] <= i
 */
public class CreateTargetArray {

    public static void main(String[] args) {
        CreateTargetArray createTargetArray = new CreateTargetArray();
        int[] ans = createTargetArray.createTargetArray(new int[]{1, 2, 3}, new int[]{0, 0, 0});
        ListHelper.print(ans);
    }
    public int[] createTargetArray(int[] nums, int[] index) {
        int len = nums.length;
        int[] target = new int[len];
        // 题目保证数字插入位置总是存在。
        // nums : 1, 2, 3
        // index : 0, 0, 0
        // target: -1, -1, -1
        // step1: 1, -1, -1
        // step2: 2, 1, -1
        // step2: 3, 2, 1
        // 填充表示未设置过
        Arrays.fill(target, -1);
        for (int i = 0; i < len; i++) {
            int position = index[i];
            int value = nums[i];
            if(target[position] != -1) {
                System.arraycopy(target, position, target, position + 1, len - (position + 1));
            }
            target[position] = value;
        }

        return target;
    }
}
