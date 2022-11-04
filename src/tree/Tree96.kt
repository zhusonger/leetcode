package tree

// 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
// 输入：n = 3
// 输出：5
class Tree96 {
    fun numTrees(n: Int): Int {
        return traverse(1, n)
    }

    private fun traverse(start: Int, end: Int): Int {
        if (start >= end) {
            return 1
        }
        var res = 0
        for (i in start..end) {
            val left = traverse(start, i - 1)
            val right = traverse(i + 1, end)
            res += left * right
        }
        return res
    }
}

//fun main() {
//    val t96 = Tree96()
//    println(t96.numTrees(1))
//}