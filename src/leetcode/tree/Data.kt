package leetcode.tree

import com.sun.jmx.remote.internal.ArrayQueue
import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

// 根据数组创建二叉树节点
fun createBinaryTree(_nodes: Array<Int?>): TreeNode? {
    if (_nodes.isEmpty()) return null
    var nodes = _nodes;

    // 补齐最后一个节点的值
    if (nodes.size % 2 == 0) nodes = nodes.plus(null as Int?)
    val root: TreeNode? = TreeNode(nodes.first()!!)
    val nodesQueue = ArrayQueue<TreeNode?>(nodes.size)
    nodesQueue.add(root)
    for (i in 1 until nodes.size step 2) {
        val node = nodesQueue.first();
        nodesQueue.remove(node)

        val left = nodes[i]
        val right = nodes[i + 1]
        if (null != left) {
            node?.left = TreeNode(left)
            nodesQueue.add(node?.left)
        }
        if (null != right) {
            node?.right = TreeNode(right)
            nodesQueue.add(node?.right)
        }
    }
    return root
}

// 还原二叉树成数组
fun binaryTreeToArray(root: TreeNode?, log: Boolean = true): Array<Int?> {
    val result = mutableListOf<Int?>()
    val nodesQueue = LinkedList<TreeNode?>()
    nodesQueue.add(root)

    while (nodesQueue.isNotEmpty()) {
        val node = nodesQueue.poll()
        result.add(node?.`val`)
        if (null != node) {
            nodesQueue.add(node.left)
            nodesQueue.add(node.right)
        }
    }
    for (i in result.size - 1 downTo 0) {
        if (null == result[i]) result.removeAt(i)
        else break
    }
    val array = result.toTypedArray()
    if (log)
        println(array.joinToString())
    return array
}

fun createListQueue(array: IntArray): ListNode? {
    if (array.isEmpty()) return null
    val head = ListNode(array[0])
    var pointer: ListNode? = head
    for (index in 1 until array.size) {
        pointer?.next = ListNode(array[index])
        pointer = pointer?.next
    }
    return head
}

fun listQueueToArray(head: ListNode?, log: Boolean = true): IntArray {
    val numbs = arrayListOf<Int>()
    var pointer = head
    while (pointer != null) {
        numbs.add(pointer.`val`)
        pointer = pointer.next
    }
    if (log)
        println(numbs.joinToString())
    return numbs.toIntArray()
}

//fun main() {
//    val root = createBinaryTree(arrayOf(1, 3, null, null, 2))
//    binaryTreeToArray(root).forEach {
//        println(it)
//    }
//
//}
