package basic.arraylinked

/// 前缀和 ==> 频繁求区间和(包括多维矩阵)

// 一维数组中的前缀和
// https://leetcode.cn/problems/range-sum-query-immutable/
// 求索引[left,right]和
// 前缀和其实就是先构造一个查询集合, 再根据各个集合的差值算出结果
class NumArray(nums: IntArray) {
    var preSum = mutableListOf<Int>()
    init {
        preSum.add(0)
        for ((i, v) in nums.withIndex()) {
            preSum.add(preSum[i] + v)
        }
    }
    fun sumRange(left: Int, right: Int): Int {
        // 最简单取值直接遍历即可
//        var ans = 0
//        for (i in left..right) {
//            ans += nums[i]
//        }
//        return ans

        // preSum[right + 1], 前right总和
        // preSum[left], 前left-1总和
        // [..., left,....right....]
        // [..., left,....right]
        // [..., ]
        // =>    [left,....right]
        return preSum[right + 1] - preSum[left]
    }

}

// 二维矩阵中的前缀和
// https://leetcode.cn/problems/range-sum-query-2d-immutable/
class NumMatrix(matrix: Array<IntArray>) {

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {

    }

}
fun main() {
    var obj = NumArray(intArrayOf(1, 2, 3))
    println(obj.sumRange(1, 2))
}