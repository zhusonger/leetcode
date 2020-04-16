package cn.com.lasong.leetcode.kuaishou;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/16
 * Description:
 * 题目需求
 * 被0包围(上下左右)的1置为0
 * 边界永远不会被包围
 *
 * 比如
 * Input
 * 0 0 0 0
 * 0 1 0 0
 * 0 0 0 0
 *
 * Output
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 0 0
 *
 * Input
 * 1 0 0 0
 * 0 1 0 0
 * 0 0 0 0
 *
 * Output
 * 1 0 0 0
 * 0 0 0 0
 * 0 0 0 0
 */
public class BFS_numIslands {

    public static void main(String[] args) {
        BFS_numIslands numIslands = new BFS_numIslands();
        int[][] grid = new int[][]{
                new int[]{0, 0, 0, 0},
                new int[]{0, 1, 0, 0},
                new int[]{0, 0, 0, 0},
        };
        numIslands.island(grid);
        ListHelper.print(grid);
    }
    public void island(int[][] grid) {
        // 分析 使用BFS层次遍历, 遍历到1就进行向四周的扩散, 直到遇到边界或者都为0
        // 确定终结点, 上下左右都为0时, 进行置空操作
        // 如果到边还是无法置空, 表示不是满足条件的, 跳出, 进行下一个点判断
        int n_row = grid.length;
        int n_column = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int row = 1; row < n_row - 1; row++) {
            for (int column = 1; column < n_column - 1; column++) {
                if (grid[row][column] == 1) {
                    queue.offer(row * n_column + column);
                    boolean isNeedZero = false;
                    visited.clear();
                    while (!queue.isEmpty()) {
                        int id = queue.poll();
                        int g_row = id / n_column;
                        int g_column = id % n_column;
                        // 记录访问过的点位置, 用于后面置为0
                        visited.add(id);
                        int top = (g_row - 1) * n_column + g_column;
                        int bottom = (g_row - 1) * n_column + g_column;
                        int left = (g_row) * n_column + g_column - 1;
                        int right = (g_row) * n_column + g_column + 1;
                        if (top == 0 || bottom == n_row - 1 || left == 0 || right == n_column - 1) {
                            continue;
                        }

                        if ((/*visited.contains(top) ||*/ g_row - 1 >= 0 && grid[g_row - 1][g_column] == 0)
                                && ((/*visited.contains(bottom) || */g_row + 1 < n_row && grid[g_row + 1][g_column] == 0))
                                && ((/*visited.contains(left) || */g_column - 1 >= 0 && grid[g_row][g_column - 1] == 0))
                                && ((/*visited.contains(right) || */g_column + 1 < n_column && grid[g_row][g_column + 1] == 0))) {
                            isNeedZero = true;
                            break;
                        }

                        if (!visited.contains(top) && g_row - 1 >= 0 && grid[g_row - 1][g_column] == 1) {
                            queue.offer((g_row - 1) * n_column + g_column);
                        }
                        if (!visited.contains(bottom) && g_row + 1 < n_row && grid[g_row + 1][g_column] == 1) {
                            queue.offer((g_row + 1) * n_column + g_column);
                        }
                        if (!visited.contains(left) && g_column - 1 >= 0 && grid[g_row][g_column - 1] == 1) {
                            queue.offer((g_row) * n_column + g_column - 1);
                        }
                        if (!visited.contains(right) && g_column + 1 < n_column && grid[g_row][g_column + 1] == 1) {
                            queue.offer((g_row) * n_column + g_column + 1);
                        }
                    }

                    if (isNeedZero) {
                        for (Integer id : visited) {
                            int g_row = id / n_column;
                            int g_column = id % n_column;
                            grid[g_row][g_column] = 0;
                        }
                    }
                }
            }
        }

    }
}
