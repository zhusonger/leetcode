package cn.com.lasong.leetcode.bytedance.link_tree;

import cn.com.lasong.leetcode.common.TreeNode;
import cn.com.lasong.leetcode.common.NodeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * 使用递归的方式遍历二叉树
 */
public class TreeTraversalByRecur {

    public static void main(String[] args) {
        TreeNode tree = NodeHelper.createTree(new Integer[]{3,9,20,null,null,15,7}, 0);
        TreeTraversalByRecur treeTraversal = new TreeTraversalByRecur();
        // 前序遍历 3 9 20 15 7
        List<Integer> ans = new ArrayList<>();
        treeTraversal.preOrderTreeByRecur(ans, tree);
        System.out.println("前序");
        for (Integer val : ans) {
            System.out.println(val);
        }
        // 中序遍历 9 3 15 20 7
        ans.clear();
        System.out.println("中序");
        treeTraversal.midOrderTreeByRecur(ans, tree);
        for (Integer val : ans) {
            System.out.println(val);
        }

        // 后序遍历 9 15 7 20 3
        ans.clear();
        System.out.println("后序");
        treeTraversal.postOrderTreeByRecur(ans, tree);
        for (Integer val : ans) {
            System.out.println(val);
        }

    }
    /**
     * 前序递归遍历树
     * root->left->right
     * @param root
     * @return
     */
    public void preOrderTreeByRecur(List<Integer> ans, TreeNode root) {
        if (null == ans || null == root) {
            return;
        }
        // root
        ans.add(root.val);
        // left
        preOrderTreeByRecur(ans, root.left);
        // right
        preOrderTreeByRecur(ans, root.right);
    }

    /**
     * 中序递归遍历树
     * left->root->right
     * @param root
     * @return
     */
    public void midOrderTreeByRecur(List<Integer> ans, TreeNode root) {
        if (null == ans || null == root) {
            return;
        }
        // left
        midOrderTreeByRecur(ans, root.left);
        // root
        ans.add(root.val);
        // right
        midOrderTreeByRecur(ans, root.right);
    }

    /**
     * 后序递归遍历树
     * left->right->root
     * @param root
     * @return
     */
    public void postOrderTreeByRecur(List<Integer> ans, TreeNode root) {
        if (null == ans || null == root) {
            return;
        }
        // left
        postOrderTreeByRecur(ans, root.left);
        // right
        postOrderTreeByRecur(ans, root.right);
        // root
        ans.add(root.val);
    }
}
