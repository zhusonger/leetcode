package basic.arraylinked

import leetcode.tree.ListNode
import leetcode.tree.createListQueue
import leetcode.tree.listQueueToArray

// 一、快慢指针技巧
// ===>原地修改数组
// 有序数组/链表去重
// 26
// 原地修改数组, 返回修改后的长度
fun removeDuplicates(nums: IntArray): Int {
    if (nums.size <= 1) return nums.size
    // 快指针在前面, 慢指针在后面
    // 因为nums是有序数组, 相同元素在一起
    // 快指针找到跟慢指针不一样的值, 就把值更新到慢指针的位置, 并向后+1
    var slowPtr = 0
    for (fastPtr in 1 until nums.size) {
        if(nums[fastPtr] != nums[slowPtr]) {
            nums[++slowPtr] = nums[fastPtr]
        }
    }
    return slowPtr + 1
}

// 83
fun deleteDuplicates(head: ListNode?): ListNode? {
    if (head?.next == null) return head
    var slowPtr = head
    var fastPtr = head.next
    while (fastPtr != null) {
        if (fastPtr.`val` != slowPtr?.`val`) {
            slowPtr?.next = fastPtr
            slowPtr = fastPtr
        }
        fastPtr = fastPtr.next
    }
    slowPtr?.next = null
    return head
}

// 27
// 输入：nums = [3,2,2,3], val = 3
// 输出：2, nums = [2,2]
fun removeElement(nums: IntArray, `val`: Int): Int {
    var slowPtr = 0
    for (fastPtr in nums.indices) {
        // 不等于val, 更新到slowPtr
        if (nums[fastPtr] != `val`) {
            nums[slowPtr++] = nums[fastPtr]
        }
    }
    return slowPtr
}
// 283
// 输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
fun moveZeroes(nums: IntArray): Unit {
    var slowPtr = 0
    for (fastPtr in nums.indices) {
        // 不等于val, 更新到slowPtr
        if (nums[fastPtr] != 0) {
            nums[slowPtr++] = nums[fastPtr]
        }
    }
    for (index in slowPtr until nums.size) {
        nums[index] = 0
    }
}

// ====> 滑动窗口算法
/* 滑动窗口算法框架 */
//void slidingWindow(string s, string t) {
//    unordered_map<char, int> need, window;
//    for (char c : t) need[c]++;
//
//    int left = 0, right = 0;
//    int valid = 0;
//    while (right < s.size()) {
//        char c = s[right];
//        // 右移（增大）窗口
//        right++;
//        // 进行窗口内数据的一系列更新
//
//        while (window needs shrink) {
//            char d = s[left];
//            // 左移（缩小）窗口
//            left++;
//            // 进行窗口内数据的一系列更新
//        }
//    }
//}
// left指针在后，right指针在前，两个指针中间的部分就是「窗口」，算法通过扩大和缩小「窗口」来解决某些问题。

// 二、左右指针的常用算法
// 二分查找, 适用与有序数组
// numbs 升序, 目标值 target, 返回目标索引
fun binarySearch(numbs: IntArray, target: Int): Int {
    var leftPtr = 0
    var rightPtr = numbs.size - 1
    while (leftPtr <= rightPtr) {
        val midPtr = (leftPtr + rightPtr) / 2
        if (numbs[midPtr] == target) {
            return midPtr
        }
        if (numbs[midPtr] > target) {
            // target在左侧
            rightPtr = midPtr - 1
        } else if (numbs[midPtr] < target) {
            // target在右侧
            leftPtr = midPtr + 1
        }
    }
    // 未找到
    return -1
}


// 167
// 数组有序, 考虑双指针
fun twoSum(numbers: IntArray, target: Int): IntArray {
    var leftPtr = 0
    var rightPtr = numbers.size - 1
    // leftPtr对应小值, rightPtr对应大值, 根据和来调整需要增大还是减小
    // 不能相同元素
    while (leftPtr < rightPtr) {
        val sum = numbers[leftPtr] + numbers[rightPtr]
        if (sum == target) {
            return intArrayOf(leftPtr + 1, rightPtr + 1)
        }
        // 和太大, 右侧向左, 减小值
        if (sum > target) {
            rightPtr--
        }
        // 和太小, 左侧向右, 增加值
        else {
            leftPtr++
        }
    }
    return intArrayOf(-1, -1)
}

// 344
// 翻转数组
fun reverseString(s: CharArray): Unit {
    var leftPtr = 0
    var rightPtr = s.size - 1
    while (leftPtr < rightPtr) {
        val temp = s[leftPtr]
        s[leftPtr] = s[rightPtr]
        s[rightPtr] = temp
        rightPtr--
        leftPtr++
    }
}


// 回文串判断
// 回文串是不管从左往右, 还是从右往左都是一样的
fun isPalindrome(s: String): Boolean {
    val array = s.toCharArray()
    var leftPtr = 0
    var rightPtr = array.size - 1
    while (leftPtr < rightPtr) {
        if (array[leftPtr] != array[rightPtr]) return false
        leftPtr++
        rightPtr--
    }
    return true
}
// 回文子串比较特殊, 双指针是从中间往2边走, 这是为了穷举所有符合的条件的回文
fun palindrome(s: String, left: Int, right: Int): String {
    var leftPtr = left
    var rightPtr = right
    while (leftPtr >= 0 && rightPtr < s.length // 防止越界
        && s[leftPtr] == s[rightPtr]) {
        leftPtr--
        rightPtr++
    }
    return s.substring(IntRange(start = leftPtr + 1, endInclusive = rightPtr - 1))
}
// 5
// 不确定回文中点是奇数个/偶数个, 都计算一下
// 给你一个字符串 s，找到 s 中最长的回文子串。
// 输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
fun longestPalindrome(s: String): String {
    var ans = ""
    for (i in s.indices) {
        val s1 = palindrome(s, i, i)
        val s2 = palindrome(s, i, i + 1)
        ans = s1.takeIf { it.length > ans.length } ?: ans
        ans = s2.takeIf { it.length > ans.length } ?: ans
    }
    return ans
}
fun main() {
    removeDuplicates(intArrayOf(1, 1, 2)).apply {
        println(this)
    }

    deleteDuplicates(createListQueue(intArrayOf(1, 1, 2)))?.apply {
        listQueueToArray(this)
    }
    removeElement(intArrayOf(3, 2, 2, 3), 3).apply {
        println(this)
    }
    moveZeroes(intArrayOf(0, 1, 0, 3, 12))

    twoSum(intArrayOf(2,7,11,15), target = 9).apply {
        println(this.joinToString())
    }
    reverseString(charArrayOf('h','e','l','l','o'))

    longestPalindrome("babad").apply {
        println(this)
    }
}