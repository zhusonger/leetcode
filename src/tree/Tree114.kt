package tree

// 给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
//展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
//展开后的单链表应该与二叉树 先序遍历 顺序相同。
// 输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
class Tree114 {
    fun flatten(root: TreeNode?): Unit {
        if (null == root) return

        flatten(root.left)
        flatten(root.right)

        val left = root.left
        val right = root.right
        // 把左节点放到右节点, 清空左节点
        left?.apply {
            root.right = this
            root.left = null
            var endNode = this
            while (endNode.right != null) {
                endNode = endNode.right!!
            }
            endNode.right = right
        }
    }
}

fun main() {
    val tree = Tree114()
    val root = createBinaryTree(arrayOf(1,2,5,3,4,null,6))
    tree.flatten(root)
    binaryTreeToArray(root)
}