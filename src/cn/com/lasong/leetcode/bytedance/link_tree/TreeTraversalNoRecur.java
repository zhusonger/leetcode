package cn.com.lasong.leetcode.bytedance.link_tree;

import cn.com.lasong.leetcode.common.TreeNode;
import cn.com.lasong.leetcode.common.NodeHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 *
 *
 *          List<Integer> ans = new ArrayList<>();
 *         // 压栈 用于记录树的节点
 *         Stack<TreeNode> stack = new Stack<>();
 *         // 记录指针 指向当前比较的节点
 *         TreeNode ptr = root;
 *         while (ptr != null || !stack.isEmpty()) {
 *             if (null != ptr) {
 *                 // section1
 *                 // 然后要入栈
 *                 stack.push(ptr);
 *                 // 2. 再去读取left
 *                 ptr = ptr.left;
 *             } else {
 *                 // 3. 左边节点没有了, 弹出最后压入的那个节点, 即这个空节点的父节点
 *                 // 去读取右节点
 *                 // section2
 *                 TreeNode node = stack.pop();
 *                 ptr = node.right;
 *             }
 *         }
 *
 *   结构都是一样的
 *   主要是记录值的位置,ans.add(val);
 *   默认情况下弹出的是左节点 如果从弹出位置(section2)开始统计就是 left->root->right 即中序
 *   如果是从中间节点(section1)开始统计就是 root->left->right root就是一压入就添加, 一直取左子树, 最后才统计right root->left->right 即前序
 *
 *   后序跟前序紧密关联, 前序是中间节点在前(列表头), 那后序相反, 最后才取root, 那加入到最后(列表尾, add(0, node)), 因为我们统一都往前面加了
 *   所以如果还是先往左取值的话, 那就是先加left( root =>  left->root, 因为add(0, node)) 最后取right (left->root => right->left->root)
 *   跟后序的顺序好像不太对得上(left->right->root) 那我们换一下, 先取右边的 (root = > right->root),
 *   最后取左边的 (right->root => left->right->root)
 *   哎 现在对上了
 *   {@link #preOrderTraversal}
 */
public class TreeTraversalNoRecur {


    public static void main(String[] args) {
        TreeNode tree = NodeHelper.createTree(new Integer[]{3,9,20,null,null,15,7}, 0);
//        TreeNode tree = ZNodeHelper.createTree(new Integer[]{1,2,3}, 0);
        TreeTraversalNoRecur treeTraversal = new TreeTraversalNoRecur();
        // 前序遍历 3 9 20 15 7
        List<Integer> ans = treeTraversal.preOrderTraversal(tree);
        System.out.println("前序");
        for (Integer val : ans) {
            System.out.println(val);
        }
        // 中序遍历 9 3 15 20 7
        ans = treeTraversal.midOrderTraversal(tree);
        System.out.println("中序");
        for (Integer val : ans) {
            System.out.println(val);
        }
        // 后序遍历 9 15 7 20 3
        ans = treeTraversal.postOrderTraversal(tree);
        System.out.println("后序");
        for (Integer val : ans) {
            System.out.println(val);
        }
    }


    /**
     * 前序 root->left->right
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // 压栈 用于记录树的节点
        Stack<TreeNode> stack = new Stack<>();
        // 记录指针 指向当前比较的节点
        TreeNode ptr = root;
        while (ptr != null || !stack.isEmpty()) {
            if (null != ptr) {
                // section1
                // 1. 得到一个节点, 首先读取root
                ans.add(ptr.val);
                // 然后要入栈
                stack.push(ptr);
                // 2. 再去读取left
                ptr = ptr.left;
            } else {
                // 3. 左边节点没有了, 弹出最后压入的那个节点, 即这个空节点的父节点
                // 去读取右节点
                // section2
                TreeNode node = stack.pop();
                ptr = node.right;
            }
        }

        return ans;
    }

    /**
     * left->root->right
     * @param root
     * @return
     */
    public List<Integer> midOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // 压栈 用于记录树的节点
        Stack<TreeNode> stack = new Stack<>();
        // 记录指针 指向当前比较的节点
        TreeNode ptr = root;
        while (ptr != null || !stack.isEmpty()) {
            if (null != ptr) {
                // 1. 先压入栈
                stack.push(ptr);
                // 2. 从左节点开始
                ptr = ptr.left;
            } else {
                TreeNode node = stack.pop();
                // 3. 记录下最左节点的值, 即left
                // 左侧节点没有了, 这个是中间节点, 可以记录下来了
                ans.add(node.val);
                // 4. 再遍历右边节点
                ptr = node.right;
            }
        }
        return ans;
    }

    /**
     * 前序: root->left->right
     * 后序: left->right->root
     *
     * 前序倒过来 right->left->root 跟后序有点像了
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // 压栈 用于记录树的节点
        Stack<TreeNode> stack = new Stack<>();
        // 记录指针 指向当前比较的节点
        TreeNode ptr = root;
        while (ptr != null || !stack.isEmpty()) {
            if (null != ptr) {
                // 1. 得到一个节点, 首先读取root
                ans.add(0, ptr.val);
                // 然后要入栈
                stack.push(ptr);
                // 2. 再去读取left
                ptr = ptr.right;
            } else {
                // 3. 左边节点没有了, 弹出最后压入的那个节点, 即这个空节点的父节点
                // 去读取右节点
                TreeNode node = stack.pop();
                ptr = node.left;
            }
        }
        return ans;
    }
}
