package tree

import java.util.*

// 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
// 输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
class Tree102 {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = LinkedList<List<Int>>()
        if (null != root) {
            val nodesQueue = LinkedList<TreeNode>()
            nodesQueue.add(root)
            while (nodesQueue.isNotEmpty()) {
                val size = nodesQueue.size
                val level = LinkedList<Int>()
                for (i in 0 until size) {
                    val node = nodesQueue.removeFirst()
                    level.add(node.`val`)
                    if (node.left != null) {
                        nodesQueue.add(node.left!!)
                    }
                    if (node.right != null) {
                        nodesQueue.add(node.right!!)
                    }
                }
                result.add(level)
            }
        }

        return result
    }
}

//fun main() {
//    val tree = Tree102()
//    var level = 0
//    tree.levelOrder(createBinaryTree(arrayOf(3,9,20,null,null,15,7))).forEach { levelAll->
//        println("level$level => ${levelAll.joinToString()}")
//        level++
//    }
//}