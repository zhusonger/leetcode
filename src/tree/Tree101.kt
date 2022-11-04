package tree

// 给你一个二叉树的根节点 root ， 检查它是否轴对称。
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
class Tree101 {
    fun isSymmetric(root: TreeNode?): Boolean {
        return isSameTree(root?.left, root?.right)
    }

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p?.`val` != q?.`val`) return false
        return isSameTree(p?.left, q?.right) && isSameTree(p?.right, q?.left)
    }
}

//fun main() {
//    val tree = Tree101()
//    println(tree.isSymmetric(createBinaryTree(arrayOf(1,2,2,3,4,4,3))))
//}