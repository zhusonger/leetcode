package leetcode.tree

// 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
// 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]

class Tree105 {

    private val val2Index = hashMapOf<Int, Int>()
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        for ((index, value) in inorder.withIndex()) {
            val2Index[value] = index
        }
        return traverse(preorder, 0, preorder.size - 1,
        inorder, 0, inorder.size - 1)
    }

    private fun traverse(preorder: IntArray, preStart: Int, preEnd: Int,
                         inorder: IntArray, inStart: Int, inEnd: Int): TreeNode? {
        if (preStart > preEnd) return null

        // 前序第一个就是根节点
        val rootVal = preorder[preStart]
        val rootIndexInOrder = val2Index[rootVal]!!

        val leftSize = rootIndexInOrder - inStart
        val root = TreeNode(rootVal)

        root.left = traverse(preorder, preStart + 1, preStart + leftSize,
            inorder, inStart, rootIndexInOrder - 1)
        root.right = traverse(preorder, preStart + leftSize + 1, preEnd,
            inorder, rootIndexInOrder + 1, inEnd
            )
        return root
    }
}

fun main() {
    val tree = Tree105()
    binaryTreeToArray(tree.buildTree(intArrayOf(3,9,20,15,7), intArrayOf(9,3,15,20,7)))
}