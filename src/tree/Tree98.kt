package tree

// 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
//有效 二叉搜索树定义如下：
//
//节点的左子树只包含 小于 当前节点的数。
//节点的右子树只包含 大于 当前节点的数。
//所有左子树和右子树自身必须也是二叉搜索树。
// 输入：root = [2,1,3]
//输出：true


class Tree98 {
    fun isValidBST(root: TreeNode?): Boolean {
        return traverse(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun traverse(root: TreeNode?, min: Long, max: Long): Boolean {
        if (null == root) return true
        if (root.`val` >= max || root.`val` <= min) return false
        return traverse(root.left, min, root.`val`.toLong()) && traverse(root.right, root.`val`.toLong(), max)
    }
}
