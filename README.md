# leetcode
<https://mp.weixin.qq.com/s/00yRZUaU5c5KV-yTWhoDig>

# basic
---
## 数组/链表
### [前缀和](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=2&sn=19a2609f33eadbbda1f6b75e2298d931&scene=21#wechat_redirect)
主要用在频繁取值子数组和  
1.建立从0开始穷举的查询结构  
2.通过包含关系加减获得子数组和
> 二维数组矩阵原理一样, 只是它的加减关系比一维数组多
```kotlin
val numbs = intArrayOf(1,2,3)
val preSum = mutableListOf<Int>()
preSum.add(0)

for((index, value) in numbs.withIndex()) {
    preSum.add(preSum[index] + value)
}
// [1,2] = 2 + 3 = 5
// [1,2] = [0,3)-[0,1] = 6 - 1 = 5
val ans = preSum[2 + 1] - preSum[1]
```

### [差分数组](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=3&sn=1f13cb4b49e6ff698d396220ad6c54f7&scene=21#wechat_redirect)
主要用在给子数组频繁加减值  
1.建立numbs\[i\]-numbs\[i+1\]的差分数组  
2.对区域\[i,j\]的修改变成对差分数组diff\[i\]和diff\[j+1\]的修改
```kotlin
val numbs = intArrayOf(1,2,4,5,7,6)
val diff = mutableListOf<Int>() 
diff.add(numbs[0])
for(i in 1 until numbs.size) {
    diff[i] = numbs[i] - numbs[i-1]
}
// diff: [1,1,2,1,2,-1]
// [1, 4] + 3
diff[1] += 3 // 从1开始都+3
diff[4 + 1] -= 3 // 5开始恢复之前的差值, 注意不要超出数组长度
```

### [单链表](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247492022&idx=1&sn=35f6cb8ab60794f8f52338fab3e5cda5&scene=21#wechat_redirect)
* 合并有序单链表成新的有些单链表  
    主要是取所有链表头的最小值进行新的连接, 使用虚拟头简化代码, 多条链表原理一样, 通过set辅助记录头结点
```kotlin
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val dummy = ListNode(-1)
    var p1 = list1
    var p2 = list2
    var p = dummy
    while (p1 != null && p2 != null) {
        if (p1.`val` <= p2.`val`) {
            p.next = p1
            p1 = p1.next
        } else {
            p.next = p2
            p2 = p2.next
        }
        p = p.next
    } 
    return dummpy.next
}
```    
* 单链表倒数N结点  
    通过双指针, p1先走N步, p2从head开始, p1到结尾时, p2就是倒数N节点, 减少遍历次数
```kotlin
fun findNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var fastPtr = head
    // 先走N步
    for(i in 0 until n) {
        fastPtr = fastPtr?.next
    }
    // 同步遍历
    var slowPtr = head
    while(fastPtr != null) {
        fastPtr = fastPtr.next
        slowPtr = slowPtr?.next
    }
    // 快指针到结尾, 慢指针刚好到倒数N节点
    return slowPtr
}
```    
* 单链表中点  
    通过快慢指针, 快指针2倍速, 当快指针到结尾时, 慢指针刚好在中点
```kotlin
fun middleNode(head: ListNode?): ListNode? {
    var fastPtr = head
    var slowPtr = head
    // 快指针双倍速, 到达结尾时, 慢指针在中点
    while (fastPtr != null) {
        fastPtr = fastPtr.next?.next
        slowPtr = slowPtr?.next
    }
    return slowPtr
}
```    
* 链表是否有环  
    通过快慢指针, 快指针2倍速, 当快慢指针相同时, 表示相交, 就是有环
```kotlin
fun hasCycle(head: ListNode?): Boolean {
    var fastPtr = head
    var slowPtr = head
    // 快指针双倍速, 相同说明有环
    while (fastPtr != null) {
        fastPtr = fastPtr.next?.next
        slowPtr = slowPtr?.next
        if (fastPtr == slowPtr) return true
    }
    return false
}
```    
* 链表环的起点  
    在链表是否有环的基础上, 通过让其一指针回到链表头, 重新同步走到相交点来找到环起点
```kotlin
fun detectCycle(head: ListNode?): ListNode? {
    var fastPtr = head
    var slowPtr = head
    // 快指针双倍速, 相同说明有环
    while (fastPtr != null) {
        fastPtr = fastPtr.next?.next
        slowPtr = slowPtr?.next
        if (fastPtr == slowPtr) break
    }
    // 没有相交
    if (fastPtr == null) return null
    // 重新走 环长度 - (环起点到相交)就会再次在环起点相交
    slowPtr = head
    while (fastPtr != slowPtr) {
        fastPtr = fastPtr?.next
        slowPtr = slowPtr?.next
    }
    return slowPtr
}
```    
* 链表是否相交  
    通过拼接链表达到结尾处为相交点, 然后通过双指针遍历各自数组, 直到碰到相同点, 就是相交点       
```kotlin
fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    var ptrA = headA
    var ptrB = headB
    // ptrA跟ptrB相等的第一个点就是相交点
    // 因为需要遍历完整个拼接数组,才能直到有没有相交点, 所以遍历到最后也没有的话
    // ptrA跟ptrB都是null
    while (ptrA != ptrB) {
        ptrA = if (ptrA == null) headB else ptrA.next
        ptrB = if (ptrB == null) headA else ptrB.next
    }
    return ptrA
}
```
