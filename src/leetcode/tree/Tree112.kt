package leetcode.tree

import java.util.*

// 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
//
//叶子节点 是指没有子节点的节点。

// 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//解释：等于目标和的根节点到叶节点路径如上图所示。

// 输入：root = [1,2,3], targetSum = 5
//输出：false
//解释：树中存在两条根节点到叶子节点的路径：
//(1 --> 2): 和为 3
//(1 --> 3): 和为 4
//不存在 sum = 5 的根节点到叶子节点的路径。

class Tree112 {
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (null == root) return false
        val nodesQueue = LinkedList<TreeNode>()
        nodesQueue.add(root)

        val levelSum = LinkedList<Int>()
        levelSum.add(root.`val`)
        while (nodesQueue.isNotEmpty()) {
            val size = nodesQueue.size
            for (i in 0 until size) {
                val node = nodesQueue.removeFirst()
                val sum = levelSum.removeFirst()
                if (node.left == null && node.right == null) {
                    if (sum == targetSum) {
                        return true
                    }
                }
                node.left?.apply {
                    nodesQueue.add(this)
                    levelSum.add(sum + `val`)
                }
                node.right?.apply {
                    nodesQueue.add(this)
                    levelSum.add(sum + `val`)
                }
            }
        }
        return false
    }
}

fun main() {
    val tree = Tree112()

    tree.hasPathSum(createBinaryTree(arrayOf(5,4,8,11,null,13,4,7,2,null,null,null,1)), 22).apply {
        println(this)
    }
    tree.hasPathSum(createBinaryTree(arrayOf(1,2,3)), 5).apply {
        println(this)
    }
}