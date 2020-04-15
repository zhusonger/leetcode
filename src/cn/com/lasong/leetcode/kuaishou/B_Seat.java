package cn.com.lasong.leetcode.kuaishou;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/15
 * Description:
 * m * n 的矩阵
 * 由 * .组成 .表示可用, * 不可用
 * 同时 . 的上下左右不能再用
 * 返回可用个数
 */
public class B_Seat {

    public static void main(String[] args) {
        B_Seat seat = new B_Seat();
        int ans = seat.getAvailableSeat(new char[][]{
                new char[]{'*', '.', '*', '*', '.'},
                new char[]{'*', '.', '*', '*', '*'},
                new char[]{'*', '.', '*', '*', '.'},
        });
        System.out.println(ans);
    }
    /**
     * 跟岛屿问题类似, 但是简化了, 不需要去延生出去
     * 当遇到.就计数, 同时把它的四周置为不可用
     * @param seats
     * @return
     */
    public int getAvailableSeat(char[][] seats) {
        int n_row = seats.length;
        int n_column = seats[0].length;
//        Queue<Integer> neighbors = new LinkedList<>();
        int ans = 0;
        for (int row = 0; row < n_row; row++) {
            for (int column = 0; column < n_column; column++) {
                char item = seats[row][column];
                if (item == '.') {
                    ans++;
                    seats[row][column] = '*';
                    // 上
                    if (row - 1 >= 0 && seats[row - 1][column] == '.') {
                        seats[row - 1][column] = '*';
                    }
                    // 下
                    if (row + 1 < n_row && seats[row + 1][column] == '.') {
                        seats[row + 1][column] = '*';
                    }
                    // 左
                    if (column - 1 >= 0 && seats[row][column - 1] == '.') {
                        seats[row][column - 1] = '*';
                    }
                    // 右
                    if (column + 1 < n_column && seats[row][column + 1] == '.') {
                        seats[row][column + 1] = '*';
                    }
                }

            }
        }
        return ans;
    }
}
