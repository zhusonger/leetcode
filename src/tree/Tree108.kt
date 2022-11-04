package tree

// 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
//
//高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

// 输入：nums = [-10,-3,0,5,9]
//输出：[0,-3,9,-10,null,5]
//解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
class Tree108 {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return traverse(nums, 0, nums.size - 1)
    }

    private fun traverse(numbs: IntArray, left: Int, right: Int): TreeNode? {
        if (left > right) return null
        // 取数组左侧作为根节点
        val mid: Int = (left + right) / 2
        val value = numbs[mid]
        val node = TreeNode(value)
        node.left = traverse(numbs, left, mid - 1)
        node.right = traverse(numbs, mid + 1, right)
        return node
    }
}

fun main() {
    val tree = Tree108()
    binaryTreeToArray(tree.sortedArrayToBST(intArrayOf(-10,-3,0,5,9)))
}