package leetcode.tree

// 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
//
//如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

// 输入：p = [1,2,3], q = [1,2,3]
//输出：true

class Tree100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p?.`val` != q?.`val`) return false
        return isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
    }
}

//fun main() {
//    val leetcode.tree = Tree100()
//    println(leetcode.tree.isSameTree(createBinaryTree(arrayOf(1,2,3)), createBinaryTree(arrayOf(1,2,3))))
//
//    println(leetcode.tree.isSameTree(createBinaryTree(arrayOf(1,2)), createBinaryTree(arrayOf(1,null,2))))
//}