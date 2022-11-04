package tree

import java.util.*

// 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
// 输入：root = [3,9,20,null,null,15,7]
//输出：[[15,7],[9,20],[3]]
class Tree107 {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {

        val result = mutableListOf<List<Int>>()
        if (null != root) {
            val nodesQueue = LinkedList<TreeNode>()
            nodesQueue.add(root)

            while (nodesQueue.isNotEmpty()) {
                val size = nodesQueue.size
                val levelOrder = arrayListOf<Int>()
                for (i in 0 until size) {
                    val node = nodesQueue.removeFirst()
                    levelOrder.add(node.`val`)
                    node.left?.apply {
                        nodesQueue.add(this)
                    }
                    node.right?.apply {
                        nodesQueue.add(this)
                    }
                }
                result.add(0, levelOrder)

            }
        }

        return result

    }
}

fun main() {
    val tree = Tree107()
    var level = 0
    tree.levelOrderBottom(createBinaryTree(arrayOf(3,9,20,null,null,15,7))).forEach { levelAll->
        println("level$level => ${levelAll.joinToString()}")
        level++
    }
}