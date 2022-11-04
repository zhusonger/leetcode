package tree

// 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
// 输入：root = [1,3,null,null,2]
//输出：[3,1,null,null,2]
//解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
class Tree99 {

    var left: TreeNode? = null
    var right: TreeNode? = null
    var orderPre: TreeNode? = null

    fun recoverTree(root: TreeNode?): Unit {
        traverse(root)
        if (null != left && null != right) {
            val temp = left!!.`val`
            left!!.`val` = right!!.`val`
            right!!.`val` = temp
        }

        binaryTreeToArray(root)
    }

    private fun traverse(root: TreeNode?) {
        if (null == root) return
        traverse(root.left)
        // 中序二叉搜索树时, 应该是有序的, 前一个值应该是小于root值得
        if ((orderPre?.`val`?.toLong() ?: Long.MIN_VALUE) > root.`val`) {
            // 首次出现的不匹配的值应该是在左子树
            if (null == left) left = orderPre
            // 出现的错误节点不一定是当前这个, 也可能是后面的, 所以后面的值需要一直更新
            right = root
        }
        orderPre = root
        traverse(root.right)
    }
}

//fun main() {
//    val tree = Tree99()
//    tree.recoverTree(createBinaryTree(arrayOf(1,3,null,null,2)))
//}