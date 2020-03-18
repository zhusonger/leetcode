package cn.com.lasong.leetcode.everyday;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/18
 * Description:
 * https://leetcode-cn.com/problems/rectangle-overlap/
 * 836. 矩形重叠
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 示例 1：
 *
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 * 说明：
 *
 * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
 * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
 */
public class IsRectangleOverlap {

    public static void main(String[] args) {
        IsRectangleOverlap isRectangleOverlap = new IsRectangleOverlap();
//        int[] rec1 = new int[]{0,0,2,2};
//        int[] rec2 = new int[]{1,1,3,3};
        int[] rec1 = new int[]{0,0,1,1};
        int[] rec2 = new int[]{1,0,2,1};
        boolean ans = isRectangleOverlap.isRectangleOverlap(rec1, rec2);
        System.out.println(ans);
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int rec1Left = rec1[0];
        int rec1Bottom = rec1[1];
        int rec1Right = rec1[2];
        int rec1Top = rec1[3];

        int rec2Left = rec2[0];
        int rec2Bottom = rec2[1];
        int rec2Right = rec2[2];
        int rec2Top = rec2[3];

        boolean ans = false;

        if ((rec2Left >= rec1Left && rec2Left < rec1Right) // 左边界在范围内, 再判断top跟bottom
                || (rec2Right <= rec1Right && rec2Right > rec1Left) // 右边界在范围内, 再判断top跟bottom
                || (rec2Left <= rec1Left && rec2Right >= rec1Right) // 包住了rec1
                ) {
            // 在下半部分
            if (rec2Top > rec1Bottom && rec2Top <= rec1Top) {
                ans = true;
            }
            // 在上半部分
            else if (rec2Bottom < rec1Top && rec2Bottom >= rec1Bottom) {
                ans = true;
            }
            // 在内部
            else if (rec2Top <= rec1Top && rec2Bottom >= rec1Bottom) {
                ans = true;
            }
            // 包住rec1
            else if (rec2Top >= rec1Top && rec2Bottom <= rec1Bottom) {
                ans = true;
            }
        }

        return ans;
    }
}
