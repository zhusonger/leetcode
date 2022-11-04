package leetcode.tree

import java.util.*

// 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
// 输入：root = [3,9,20,null,null,15,7]
// 输出：[[3],[20,9],[15,7]]
class Tree103 {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val result = LinkedList<LinkedList<Int>>()
        if (null != root) {
            val nodesQueue = LinkedList<TreeNode>()
            nodesQueue.add(root)
            var level = 0 // 偶数从左往右, 基数从右往左
            while (nodesQueue.isNotEmpty()) {
                val size = nodesQueue.size
                val levelOrder = LinkedList<Int>()
                val reverse = level % 2 != 0
                for (i in 0 until size) {
                    val node = nodesQueue.removeFirst()
                    // m1
//                    levelOrder.add(node.`val`)
                    // m2
                    if (reverse) {
                        levelOrder.add(0, node.`val`)
                    } else {
                        levelOrder.add(node.`val`)
                    }
                    if (node.left != null) {
                        nodesQueue.add(node.left!!)
                    }
                    if (node.right != null) {
                        nodesQueue.add(node.right!!)
                    }
                }
//                if (level % 2 != 0) {
//                    levelOrder.reverse()
//                }
                result.add(levelOrder)
                level++
            }
        }
        return result
    }
}

fun main() {
    val tree = Tree103()
    var level = 0
    tree.zigzagLevelOrder(createBinaryTree(arrayOf(3,9,20,null,null,15,7))).forEach { levelAll->
        println("level$level => ${levelAll.joinToString()}")
        level++
    }
}