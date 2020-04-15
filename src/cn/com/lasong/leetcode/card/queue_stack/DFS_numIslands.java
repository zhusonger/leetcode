package cn.com.lasong.leetcode.card.queue_stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/15
 * Description:
 */
public class DFS_numIslands {

    public int numIslands(char[][] grid) {
        int ans = 0;
        int n_row = grid.length;
        int n_column = grid[0].length;
        for (int row = 0; row < n_row; row++) {
            for (int column = 0; column < n_column; column++) {
                char item = grid[row][column];
                if (item == '1') {
                    ans++;
                    grid[row][column] = '0';
                    dfs(grid, row - 1, column, n_row, n_column);
                    dfs(grid, row + 1, column, n_row, n_column);
                    dfs(grid, row, column - 1, n_row, n_column);
                    dfs(grid, row, column + 1, n_row, n_column);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int row, int column, int n_row, int n_column) {
        // 在有效范围内且仍然是岛屿(1)
        // 继续往深度遍历, 并标记为访问(置为0)
        if (row >= 0 && row < n_row && column >= 0 && column < n_column && grid[row][column] == '1') {
            grid[row][column] = '0';
            dfs(grid, row - 1, column, n_row, n_column);
            dfs(grid, row + 1, column, n_row, n_column);
            dfs(grid, row, column - 1, n_row, n_column);
            dfs(grid, row, column + 1, n_row, n_column);
        }
    }

    public static void main(String[] args) {
        DFS_numIslands numIslands = new DFS_numIslands();
        int ans = numIslands.numIslands(new char[][]{
                new char[]{'1', '1', '1'},
                new char[]{'1', '1', '0'},
                new char[]{'1', '0', '1'}
        });

        System.out.println(ans);
    }
}
