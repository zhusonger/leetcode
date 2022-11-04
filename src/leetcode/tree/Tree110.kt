package leetcode.tree

// 给定一个二叉树，判断它是否是高度平衡的二叉树。
//
//本题中，一棵高度平衡二叉树定义为：
//
//一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
// 输入：root = [3,9,20,null,null,15,7]
//输出：true
class Tree110 {

    // 自顶向下, 前序遍历
//    fun isBalanced(root: TreeNode?): Boolean {
//        if (null == root) return true
//        return kotlin.math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right)
//    }
//
//    private fun height(root: TreeNode?): Int {
//        if (null == root) return 0
//        return kotlin.math.max(height(root.left), height(root.right)) + 1
//    }

    // 自底向上, 后续遍历
    fun isBalanced(root: TreeNode?): Boolean {
        if (null == root) return true

        return traverse(root) > 0
    }

    private fun traverse(root: TreeNode?) : Int {
        if (root == null) return 0
        val left = traverse(root.left)
        val right = traverse(root.right)
        if (left < 0 || right < 0 || kotlin.math.abs(left-right) > 1) {
            return -1;
        }
        return kotlin.math.max(left, right) + 1
    }
}

fun main() {
    val tree = Tree110()
    println(tree.isBalanced(createBinaryTree(arrayOf(3,9,20,null,null,15,7))))
    println(tree.isBalanced(createBinaryTree(arrayOf(1,2,2,3,3,null,null,4,4))))


}