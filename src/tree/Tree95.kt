package tree

// 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
// 输入：n = 3
// 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
class Tree95 {

    fun generateTrees(n: Int): List<TreeNode?> {
        return traverse(1, n)
    }

    private fun traverse(start: Int, end: Int): List<TreeNode?> {
        val trees =  mutableListOf<TreeNode?>()
        if (start > end) {
            // 为了执行遍历&表示没有节点
            trees.add(null)
            return trees
        }
        for (i in start..end) {
            val leftTrees = traverse(start, i - 1)
            val rightTrees = traverse(i + 1, end)
            leftTrees.forEach { left->
                rightTrees.forEach { right->
                    val root = TreeNode(i)
                    root.left = left
                    root.right = right
                    trees.add(root)
                }
            }
        }
        return trees
    }
}