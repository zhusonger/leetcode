package cn.com.lasong.leetcode.type.dp;

import cn.com.lasong.leetcode.common.ListHelper;
import cn.com.lasong.leetcode.common.NodeHelper;
import cn.com.lasong.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/24
 * Description:
 * https://leetcode-cn.com/problems/house-robber-iii/
 337. 打家劫舍 III
 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

 示例 1:

 输入: [3,2,3,null,3,null,1]

 3
 / \
 2   3
 \   \
 3   1

 输出: 7
 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 示例 2:

 输入: [3,4,5,1,3,null,1]

 3
 / \
 4   5
 / \   \
 1   3   1

 输出: 9
 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

 */
public class Rob3 {

    public static void main(String[] args) {
        Rob3 rob = new Rob3();
//        TreeNode root = NodeHelper.createTree(new Integer[]{3,2,3,null,3,null,1});
        TreeNode root = NodeHelper.createTree(new Integer[]{3,4,5,1,3,null,1});

        int ans = rob.rob(root);
        System.out.println(ans);
    }
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * 递归统计子树的最优值
     * res[0] = 选中根节点的最优值
     * res[1] = 不选跟节点的最优值
     * @param root
     * @return
     */
    private int[] helper(TreeNode root) {
        int[] res = new int[2];
        if (null == root) {
            return res;
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        // 最优值结果的情况
        // 选中根节点, 只能选择子树的非根节点的最优值
        // 不选根节点, 可以选择子树的根节点/非根节点的最优值之和
        res[0] = root.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}
