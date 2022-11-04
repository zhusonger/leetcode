package tree

import java.util.*

// 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
//叶子节点 是指没有子节点的节点。

// 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 输入：root = [1,2,3], targetSum = 5
//输出：[]

class Tree113 {
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>>  {
        if (null == root) return emptyList()
        val nodesQueue = LinkedList<TreeNode>()
        nodesQueue.add(root)

        val levelSum = LinkedList<Int>()
        levelSum.add(root.`val`)

        val levelPath = LinkedList<List<Int>>()
        levelPath.add(arrayListOf(root.`val`))
        val ans = mutableListOf<List<Int>>()
        while (nodesQueue.isNotEmpty()) {
            val size = nodesQueue.size
            for (i in 0 until size) {
                val node = nodesQueue.removeFirst()
                val sum = levelSum.removeFirst()
                val path = levelPath.removeFirst()
                if (node.left == null && node.right == null) {
                    if (sum == targetSum) {
                        ans.add(path)
                    }
                }
                node.left?.apply {
                    nodesQueue.add(this)
                    levelSum.add(sum + `val`)
                    levelPath.add(path.plus(`val`))
                }
                node.right?.apply {
                    nodesQueue.add(this)
                    levelSum.add(sum + `val`)
                    levelPath.add(path.plus(`val`))
                }
            }
        }
        return ans
    }
}

fun main() {
    val tree = Tree113()

    tree.pathSum(createBinaryTree(arrayOf(5,4,8,11,null,13,4,7,2,null,null,5,1)), 22).apply {
        println(this)
    }
    tree.pathSum(createBinaryTree(arrayOf(1,2,3)), 5).apply {
        println(this)
    }
}