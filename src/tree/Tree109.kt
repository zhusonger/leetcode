package tree

// 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
//
//本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
// 输入: head = [-10,-3,0,5,9]
//输出: [0,-3,9,-10,null,5]
//解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
// 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
class Tree109 {
    fun sortedListToBST(head: ListNode?): TreeNode? {
        val numbs = arrayListOf<Int>()
        var pointer = head
        while (pointer != null) {
            numbs.add(pointer.`val`)
            pointer = pointer.next
        }
        return traverse(numbs.toIntArray(), 0, numbs.size - 1)
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
    val tree = Tree109()
    binaryTreeToArray(tree.sortedListToBST(createListQueue(intArrayOf(-10,-3,0,5,9))))
}