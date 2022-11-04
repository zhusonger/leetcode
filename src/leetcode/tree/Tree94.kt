package leetcode.tree

// 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
// 输入：root = [1,null,2,3]
// 输出：[1,3,2]
class Tree94 {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val numbs = mutableListOf<Int>()
        traverse(root, numbs)
        return numbs;
    }

    private fun traverse(root: TreeNode?, numbs: MutableList<Int>) {
        if (root == null) {
            return
        }
        traverse(root.left, numbs)
        numbs.add(root.`val`)
        traverse(root.right, numbs)
    }
}