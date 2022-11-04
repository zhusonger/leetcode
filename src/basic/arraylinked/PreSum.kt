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

    var preSum = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) }

    init {
        // 包含最后一个
        for (row in 1 .. matrix.size) {
            for (col in 1 .. matrix[0].size) {
                //  ___
                // |3|0|
                // |5|6|
                //  ---
                // 2行2列的和 = 1行2列(3+0) + 2行1列(3+5) - 1行1列(3) + 2行2列值(6) = 14
                preSum[row][col] = preSum[row - 1][col] + preSum[row][col - 1] -
                        preSum[row - 1][col - 1] + matrix[row - 1][col - 1]
            }
        }

        preSum.forEach {
            println(it.joinToString())
        }

    }
    // 题目的row2跟col2是以块的左上角定位的, 所以要包括它的话, 需要加1
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        //  _____
        // |3|0|1|
        // |5|6|3|
        // |1|2|0|
        //  -----
        // row1: 1, col1: 1 => 1行1列和
        // row2: 2, col2: 2 => 3行3列和
        // 取值: 3行3列和 - 1行1列和 = 3行3列和 - 3行1列和 - 1行3列和 + 1行1列和
        //                                 【这段重复减去了1行1列和】
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1]
    }

}

fun main() {
//    var obj = NumArray(intArrayOf(1, 2, 3))
//    println(obj.sumRange(1, 2))
    var obj2 = NumMatrix(
        arrayOf(
            intArrayOf(3, 0, 1, 4, 2), intArrayOf(5, 6, 3, 2, 1),
            intArrayOf(1, 2, 0, 1, 5), intArrayOf(4, 1, 0, 1, 7), intArrayOf(1, 0, 3, 0, 5)
        )
    )
    println(obj2.sumRegion(2,1,4,3))
}