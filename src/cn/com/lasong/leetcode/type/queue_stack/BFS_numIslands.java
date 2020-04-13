package cn.com.lasong.leetcode.type.queue_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/13
 * Description:
 *
 * https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/872/
 * 岛屿数量
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 */
public class BFS_numIslands {

    // 广度优先搜索BFS
    // 遇到1开始广度搜索, 直到上下左右节点都为0
    //
    // 把1作为起始点, 把上下左右为0作为终点, 并且把访问过的点置为0, 避免再次作为起点。
    // 每次访问到1就是一个1个岛屿的开始的。计数+1
    public int numIslands(char[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }

        int n_row = grid.length;
        int n_column = grid[0].length;

        int ans = 0;

        Queue<Integer> neighbors = new LinkedList<>();
        for (int row = 0; row < n_row; row++) {
            for (int column = 0; column < n_column; column++) {
                // 找到陆地, 开始广度优先搜索
                if (grid[row][column] == '1') {
                    ans++;
                    grid[row][column] = '0';
                    neighbors.clear();
                    neighbors.offer(row * n_column + column);
                    while (!neighbors.isEmpty()) {
                        int pos = neighbors.poll();
                        int g_row = pos / n_column;
                        int g_column = pos % n_column;
                        // 上 如果是1就加入队列, 并标记为访问过(置为'0')
                        if (g_row - 1 >= 0 && grid[g_row - 1][g_column] == '1') {
                            neighbors.offer((g_row - 1) * n_column + g_column);
                            grid[g_row - 1][g_column] = '0';
                        }
                        // 下
                        if (g_row + 1 < n_row && grid[g_row + 1][g_column] == '1') {
                            neighbors.offer((g_row + 1) * n_column + g_column);
                            grid[g_row + 1][g_column] = '0';
                        }
                        // 左
                        if (g_column - 1 >= 0 && grid[g_row][g_column - 1] == '1') {
                            neighbors.offer(g_row * n_column + g_column - 1);
                            grid[g_row][g_column - 1] = '0';
                        }
                        // 右
                        if (g_column + 1 < n_column && grid[g_row][g_column + 1] == '1') {
                            neighbors.offer(g_row * n_column + g_column + 1);
                            grid[g_row][g_column + 1] = '0';
                        }
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        BFS_numIslands bfs_numIslands = new BFS_numIslands();
        int ans = bfs_numIslands.numIslands(new char[][]{
                new char[]{'1', '1', '1'},
                new char[]{'1', '1', '0'},
                new char[]{'1', '0', '1'}
        });

        System.out.println(ans);
    }

}
