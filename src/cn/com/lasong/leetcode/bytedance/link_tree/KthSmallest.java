package cn.com.lasong.leetcode.bytedance.link_tree;

import cn.com.lasong.leetcode.common.NodeHelper;
import cn.com.lasong.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-22
 * Description:
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/89/
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
public class KthSmallest {

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        TreeNode root = NodeHelper.createTree(new Integer[]{3,1,4,null,2});
        kthSmallest.kthSmallest(root, 1);
    }

    /**
     * 取第K小的值, 由于二叉搜索树一定是左边小于root, root小于右边
     * 所以采用left->root->right的顺序, 就可以得到有序数组, 第K个就是数组的 (k-1)
     * 当然这里不需要求出数组, 所以计数一下, 找到第K个就返回即可
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root;
        int index = 0;
        int kValue = 0;
        while (!stack.isEmpty() || null != ptr) {
            if (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            } else {
                TreeNode node = stack.pop();
                index++;
                if (k == index) {
                    kValue = node.val;
//                    System.out.println(kValue);
                    break;
                }
                ptr = node.right;
            }
        }

        return kValue;
    }
}
