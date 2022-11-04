package leetcode.tree

// 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
// 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//输出：[3,9,20,null,null,15,7]
class Tree106 {

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        for ((i,v) in inorder.withIndex()) {
            val2Index[v] = i
        }
        return traverse(inorder, 0, inorder.size - 1,
        postorder, 0, postorder.size - 1)
    }

    private val val2Index = hashMapOf<Int, Int>()
    private fun traverse(inorder: IntArray, inStart: Int, inEnd: Int,
                         postorder: IntArray, postStart: Int, postEnd: Int): TreeNode? {
        if (postStart > postEnd) return null
        // 中序[[左子树] 根 [右子树]]
        // 后序[[左子树] [右子树] 根]

        val rootVal = postorder[postEnd]
        val rooIndex = val2Index[rootVal]!!
        val leftSize = rooIndex - inStart
        val root = TreeNode(rootVal)

        root.left = traverse(inorder, inStart, rooIndex - 1,
            postorder, postStart, postStart + leftSize - 1)

        root.right = traverse(inorder, rooIndex + 1, inEnd,
        postorder, postStart + leftSize, postEnd - 1)
        return root
    }
}

fun main() {
    val tree = Tree106()
    binaryTreeToArray(tree.buildTree(intArrayOf(9,3,15,20,7), intArrayOf(9,15,7,20,3)))
}