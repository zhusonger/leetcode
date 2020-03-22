package cn.com.lasong.leetcode.everyday;

import java.util.Map;
import java.util.Objects;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-21
 * Description:
 * https://leetcode-cn.com/problems/water-and-jug-problem/
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 */
public class CanMeasureWater {

    public static void main(String[] args) {
        CanMeasureWater canMeasureWater = new CanMeasureWater();
//        boolean ret = canMeasureWater.canMeasureWater(3, 5, 4);
//        boolean ret = canMeasureWater.canMeasureWater(2, 6, 5);
//        boolean ret = canMeasureWater.canMeasureWater(34, 5, 6);
//        System.out.println(ret);
        int gcd = canMeasureWater.gcd(3, 5);
        Objects.hash( 1, 3);
        Objects.hash( 3, 1);
        System.out.println(gcd);
    }
    public boolean canMeasureWater(int x, int y, int z) {

        return math(x, y, z);
    }

    /**
     * https://baike.baidu.com/item/%E8%A3%B4%E8%9C%80%E5%AE%9A%E7%90%86/5186593?fromtitle=%E8%B4%9D%E7%A5%96%E5%AE%9A%E7%90%86&fromid=5185441
     * 贝祖定理
     * @param a
     * @param b
     * @return
     */
    private boolean math(int a, int b, int c) {
        if (a + b < c) {
            return false;
        }
        if (a == c || b == c || (a + b) == c || Math.abs(a - b) == c) {
            return true;
        }

        return c % gcd(a, b) == 0;
    }
    /**
     * 欧几里德算法/辗转相除法 求最大公约数
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
//        System.out.println(a+","+b);
        return b == 0 ? a : gcd(b, a % b);
    }
}
