package cn.com.lasong.leetcode.bytedance.link_tree;

import cn.com.lasong.leetcode.common.NodeHelper;
import cn.com.lasong.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-22
 * Description:
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/87/
 * 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        BuildTree buildTree = new BuildTree();
        buildTree.buildTree(preorder, inorder);
    }
    Map<Integer, Integer> inorder_map = new HashMap<>();
    int preIndex = 0;

    /**
     * 前序的按顺序 root->left-right, 它的每一个节点相对来说, 其实都是一个root(相对于子树)
     * 中序的顺序 left->root->right, 它的左右子树是被root分割的, 结合前序, 就能分割左右子树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length <= 0) {
            return null;
        }
        int len = preorder.length;
        TreeNode root = new TreeNode(preorder[0]);
        // 为了避免每次都去查找需要分割的左右数组, 这里直接先记录下来位置
        for (int i = 0; i < len; i++) {
            int inVal = inorder[i];
            inorder_map.put(inVal, i);
        }
        int index = inorder_map.get(root.val);
        preIndex++;
//        [0, index - 1) left
//        (index + 1, end] right
        // 左子树
        root.left = helper(preorder, 0, index - 1);
        // 右子树
        root.right = helper(preorder, index + 1, len);
        return root;
    }

    /**
     *
     * @param left 中序数组的左侧
     * @param right 中序数组的右侧
     * @return
     */
    public TreeNode helper(int[] preorder, int left, int right) {
        if (left <= right && preIndex < preorder.length) {
            int rootVal = preorder[preIndex];
            TreeNode root = new TreeNode(rootVal);
            // 就是取下一个子树的root的节点
            preIndex++;
            int index = inorder_map.get(rootVal);
            root.left = helper(preorder, left, index - 1);
            root.right = helper(preorder, index + 1, right);
            return root;
        }

        return null;
    }
}
