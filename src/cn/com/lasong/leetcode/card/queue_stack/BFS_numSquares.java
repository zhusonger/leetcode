package cn.com.lasong.leetcode.card.queue_stack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/13
 * Description:
 * https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/874/
 *  完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class BFS_numSquares {

    /**
     * 起始点 0
     * 终点 n
     * 起始点0 的下一种状态是 0+1 0+4 0+9 .... 但sum不能超过n
     * 继续下一层循环是在这个和的基础上, 继续扩展, 比如1节点
     *  1+1 1+4 1+9
     *      1
     *  1+16 1+25 1+36
     *
     * @param n 返回平方数个数
     * @return
     */
    public int numSquares(int n) {
        int ans = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            // 把所有节点遍历一遍再次取得这些节点的分支节点
            for (int i = 0; i < size; i++) {
                int value = queue.poll();
                // 找到节点, 直接返回
                if (value == n) {
                    return ans;
                }
                int square = 1;
                int append = square * square;
                int sum;
                //  把没有出现过的分支节点加入到遍历队列中
                while ((sum = value + append) <= n) {
                    if (!visited.contains(sum)) {
                        queue.offer(sum);
                        visited.add(sum);
                    }
                    square++;
                    append = square * square;
                }

            }

        }

        return ans;
    }

    public static void main(String[] args) {
        BFS_numSquares numSquares = new BFS_numSquares();
        int ans = numSquares.numSquares(12);
        System.out.println(ans);
    }
}
