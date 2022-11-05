package basic.arraylinked

// LeetCode370
// 给定n默认都为0(不用自己计算初始diff)
// 多个三元组操作[startIndex, endIndex, inc]
// [[1, 3, 2], [2, 4, 3], [0, 2, -2]], 5
// [-2, 0, 3, 5, 3]
fun regionPlus(updates: Array<IntArray>, n: Int, startOffset: Int = 0, endOffset: Int = 0): IntArray {
    val diff = IntArray(n) { 0 }
    for (ac in updates) {
        val start = ac[0] + startOffset
        val end = ac[1] + endOffset
        val inc = ac[2]
        diff[start] += inc
        if (end + 1 < n) {
            diff[end + 1] -= inc
        }
    }
    // 恢复数组
//    val ans = IntArray(n) { 0 }
//    ans[0] = diff[0]
    val ans = diff// 内存优化, diff i的值用过之后可以覆盖, 用不到了
    for (i in 1 until n) {
        ans[i] = diff[i - 1] + diff[i]
    }

    return ans
}

// LeetCode1109
fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
    return regionPlus(bookings, n, startOffset = -1, endOffset = -1)
}

// LeetCode1094
// 题意其实就是算出新的数组, 是否存在大于capacity, 超出座位数就false
// 注意to的时候之前的已经下去了, 所以索引-1
fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
    trips.forEach {
        val inc = it[0]
        val start = it[1]
        val end = it[2]
        it[0] = start
        it[1] = end
        it[2] = inc
    }
    return !regionPlus(trips, 1001, endOffset = -1).any {
        it > capacity
    }
}

fun main() {
    regionPlus(arrayOf(intArrayOf(1, 3, 2), intArrayOf(2, 4, 3), intArrayOf(0, 2, -2)), 5).apply {
        println(this.joinToString())
    }
    corpFlightBookings(arrayOf(intArrayOf(1,2,10), intArrayOf(2,3,20), intArrayOf(2,5,25)), 5).apply {
        println(this.joinToString())
    }
    carPooling(arrayOf(intArrayOf(2,1,5), intArrayOf(3,3,7)), 4).apply {
        println(this)
    }
}