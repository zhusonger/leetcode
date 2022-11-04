package tree

// 给定一个二叉树，找出其最大深度。
//
//二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
//说明: 叶子节点是指没有子节点的节点。
// 给定二叉树 [3,9,20,null,null,15,7]，
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//返回它的最大深度 3 。

class Tree104 {
    fun maxDepth(root: TreeNode?): Int {
        traverse(root, 0)
        return maxDepth
    }

    var maxDepth = 0
    private fun traverse(node: TreeNode?, depth: Int) {

        if (node == null) return

        val curDepth = depth + 1;
        maxDepth = kotlin.math.max(curDepth, maxDepth)
        traverse(node.left, curDepth)

        traverse(node.right, curDepth)
    }
}

fun main() {
    val tree = Tree104()
    tree.maxDepth(createBinaryTree(arrayOf(3,9,20,null,null,15,7)))
}