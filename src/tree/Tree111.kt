package tree

import java.util.*

// 给定一个二叉树，找出其最小深度。
//
//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
//说明：叶子节点是指没有子节点的节点。
// 输入：root = [3,9,20,null,null,15,7]
//输出：2
class Tree111 {
    fun minDepth(root: TreeNode?): Int {
        return bfs(root)
    }

    private fun dfs(root: TreeNode?): Int {
        if (null == root) return 0
        if (root.left == null && root.right == null) return 1
        val left = dfs(root.left)
        var minDepth = Int.MAX_VALUE
        if (left > 0) {
            minDepth = kotlin.math.min(left, minDepth);
        }
        val right = dfs(root.right)
        if (right > 0) {
            minDepth = kotlin.math.min(right, minDepth);
        }

        return minDepth + 1
    }

    private fun bfs(root: TreeNode?): Int {
        if (null == root) return 0
        val nodesQueue = LinkedList<TreeNode>()
        nodesQueue.add(root)
        var minDepth = Int.MAX_VALUE
        var depth = 0
        while (nodesQueue.isNotEmpty()) {
            depth++
            val size = nodesQueue.size
            for (i in 0 until size) {
                val node = nodesQueue.removeFirst()
                if (node.left == null && node.right == null) {
                    minDepth = kotlin.math.min(depth, minDepth)
                }
                node.left?.apply {
                    nodesQueue.add(this)
                }
                node.right?.apply {
                    nodesQueue.add(this)
                }
            }
        }
        return minDepth
    }


}

fun main() {
    val tree = Tree111()
    println(tree.minDepth(createBinaryTree(arrayOf(3,9,20,null,null,15,7))))
    println(tree.minDepth(createBinaryTree(arrayOf(2,null,3,null,4,null,5,null,6))))
}