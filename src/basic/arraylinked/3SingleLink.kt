package basic.arraylinked

import leetcode.tree.ListNode
import leetcode.tree.createListQueue
import leetcode.tree.listQueueToArray

// LeetCode21
// 合并两个有序链表
// 输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
//    if (null == list1 || null == list2) return list1 ?: list2
//    val root: ListNode
//    var p1 = list1
//    var p2 = list2
//    if (p1.`val` <= p2.`val`) {
//        root = ListNode(p1.`val`);
//        p1 = p1.next
//    } else {
//        root = ListNode(p2.`val`);
//        p2 = p2.next
//    }
//    // 指向合成的数组
//    var p = root
//    while (p1 != null && p2 != null) {
//        if (p1.`val` <= p2.`val`) {
//            p.next = ListNode(p1.`val`);
//            p1 = p1.next
//        } else {
//            p.next = ListNode(p2.`val`);
//            p2 = p2.next
//        }
//        p = p.next!!
//    }
//
//    p1?.apply {
//        p.next = this
//    }
//    p2?.apply {
//        p.next = this
//    }
//
//    return root

    // 虚拟头技巧
    val dummy = ListNode(-1)
    var p1 = list1
    var p2 = list2
    var p = dummy
    while (p1 != null && p2 != null) {
        if (p1.`val` <= p2.`val`) {
            p.next = ListNode(p1.`val`);
            p1 = p1.next
        } else {
            p.next = ListNode(p2.`val`);
            p2 = p2.next
        }
        p = p.next!!
    }
    p1?.apply {
        p.next = this
    }
    p2?.apply {
        p.next = this
    }
    return dummy.next
}

// LeetCode23
// 合并 k 个有序链表
// 跟2个类似, 只需要找出多个单链表中的最小值
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val dummy = ListNode(-1)
    fun minNodeInHead(node: ListNode, min: ListNode): ListNode {
        if (node.`val` < min.`val`) {
            return node
        }
        return min
    }

    // 缓存头结点, 避免每次更新时查询数组去更新
    val headSet = mutableSetOf<ListNode>()
    headSet.addAll(lists.filterNotNull())
    var p = dummy
    while (headSet.size > 1) {
        var min = headSet.first()
        headSet.forEach {
            min = minNodeInHead(
                it, min
            )
        }
        p.next = min
        p = min
        // 更新min对应的头节点为它的下一个
        headSet.remove(min)
        // 如果没有下个节点表示这个链表处理完了
        min.next?.apply {
            headSet.add(this)
        }
    }

    headSet.firstOrNull()?.apply {
        p.next = this
    }

    return dummy.next
}

// 19
// 单链表的倒数第 k 个节点
// 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
// 输入：head = [1,2,3,4,5], n = 2
// 输出：[1,2,3,5]
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(-1)
    dummy.next = head
    // 因为需要找到前面那个节点修改next, 所以找到倒数n+1节点
    val node = findNthFromEnd(dummy, n + 1)
    node?.next = node?.next?.next
    return dummy.next
}

// 最简单的是遍历2次, 第一次获取长度length, 第二次遍历到 length-n
// 一次遍历可以借助2个指针, 先让一个指针走n个, 再让第二个指针从head走,
// 到第一个指针到结尾时, 第二个指针就是需要的节点
fun findNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var p1 = head
    for (i in 0 until n) {
        p1 = p1?.next
    }
    var p2 = head
    while (p1 != null) {
        p1 = p1.next
        p2 = p2?.next
    }
    return p2
}

// 876
// 单链表的中点
// 简单做法先遍历一遍获取长度, 再从头遍历length/2个节点, 常规做法
// 优化 ==>
// 快慢指针, slow&fast, 就是fast快slow一倍, 会先到达结尾,
// 当fast到达结尾时, slow刚好时fast的一半, 即中点
// 偶数个时获取的是右侧节点
fun middleNode(head: ListNode?): ListNode? {
    var slowPtr = head
    var fastPtr = head

    while (fastPtr?.next != null) {
        fastPtr = fastPtr.next?.next
        slowPtr = slowPtr?.next
    }
    return slowPtr
}


// 判断链表是否包含环
// 判断是否有环, 跟长跑跑步类似, 快慢指针, 当有环时, 以2倍速走的快指针会绕过环跟慢指针重合
// 就像跑步的套环, 有一刻时位置相同的
fun hasCycle(head: ListNode?): Boolean {
    var slowPtr = head
    var fastPtr = head
    while (fastPtr?.next != null) {
        fastPtr = fastPtr.next?.next
        slowPtr = slowPtr?.next
        // 相交表示存在环
        if (slowPtr == fastPtr) return true
    }
    return false
}

// 进阶
// 获取环的起点
// 如果有环存在,
// 慢指针走k步, 快指针(n倍速)就是走nk步, 多走的(n-1)k就是在环内转圈, (n-1)k = (n-1)个环长度
// 一般使用二倍速快指针
// 那【多】走了k步就是1个环的长度, 假设相交点距离环起点m, 那剩余环的长度为 k-m
// 相交的慢指针走了k步, 距离环起点也是m, 那从head到环起点就是k-m步
// 那现在其实再走k-m步就到达了环的起点,
// 任一指针重新从head开始走, 快慢指针同步移动, 当快指针跟慢指针再次相交时, 就是环的起点
fun detectCycle(head: ListNode?): ListNode? {
    var fastPtr = head
    var slowPtr = head
    while (fastPtr?.next != null) {
        fastPtr = fastPtr.next?.next
        slowPtr = slowPtr?.next
        // 相交
        if (slowPtr == fastPtr) break
    }
    // 没有环
    if (fastPtr?.next == null) return null
    // 从头开始再同步走
    slowPtr = head
    while (slowPtr != fastPtr) {
        slowPtr = slowPtr?.next
        fastPtr = fastPtr?.next
    }
    return slowPtr
}

// 160
// 两个链表是否相交
// listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
// 注意比较的是对象而不是值
fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    // 简单做法, 先遍历一遍listA, 记录下所有节点, 再遍历listB, 找到相交点
//    val headASet = mutableSetOf<ListNode>()
//    var ptrA = headA
//    while (ptrA != null) {
//        headASet.add(ptrA)
//        ptrA = ptrA.next
//    }
//
//    var ptrB = headB
//    while (ptrB != null) {
//        if (headASet.contains(ptrB)) break
//        ptrB = ptrB.next
//    }
//    return ptrB

    // 优化做法
    // 把listA跟listB逻辑上连起来, 如果有相交的点, 连接的链表最后一定是相同的
    // 双指针遍历 listA+listB & listB+listA, 碰到第一个相同的点就是相交点
    var ptrA = headA
    var ptrB = headB
    // 如果没有相交点, 遍历到最后是null, 也是满足退出循环条件
    while (ptrA != ptrB) {
        // A走到结尾切换到B头结点
        ptrA = if (ptrA == null) headB
        else ptrA.next

        ptrB = if (ptrB == null) headA
        else ptrB.next
    }

    return ptrA
}

fun main() {
    mergeTwoLists(createListQueue(intArrayOf(1, 2, 4)), createListQueue(intArrayOf(1, 3, 4))).apply {
        listQueueToArray(this)
    }

    mergeKLists(arrayOf(createListQueue(intArrayOf(1, 2, 4)), createListQueue(intArrayOf(1, 3, 4)))).apply {
        listQueueToArray(this)
    }

    removeNthFromEnd(createListQueue(intArrayOf(1, 2, 3, 4)), 1)?.apply {
        listQueueToArray(this)
    }

    middleNode(createListQueue(intArrayOf(1, 2, 3, 4)))?.apply {
        println(`val`)
    }

    val root = createListQueue(intArrayOf(1, 2, 3, 4))
    var end = root
    while (end?.next != null) {
        end = end.next
    }
    // 结尾处连接第二个节点
    end?.next = root!!.next
    println(hasCycle(root))
    println(detectCycle(root)?.`val`)

    // 构造相交单链表
    val intersectNode = createListQueue(intArrayOf(8, 4, 5))
    val headA = createListQueue(intArrayOf(4, 1))
    var ptrA = headA
    while (ptrA?.next != null) {
        ptrA = ptrA.next
    }
    ptrA?.next = intersectNode
    val headB = createListQueue(intArrayOf(5, 6, 1))
    var ptrB = headB
    while (ptrB?.next != null) {
        ptrB = ptrB.next
    }
    ptrB?.next = intersectNode
    getIntersectionNode(headA, headB)?.apply {
        println(`val`)
    }
}