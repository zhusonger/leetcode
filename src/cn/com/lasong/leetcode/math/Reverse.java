package cn.com.lasong.leetcode.math;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/23
 * Description:
 * https://leetcode-cn.com/problems/reverse-integer/
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class Reverse {

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        int ans;
//        ans = reverse.reverse(123);
//        ans = reverse.reverse(-123);
        ans = reverse.reverse(Integer.MAX_VALUE);
        System.out.println(ans);
    }
    public int reverse(int x) {
        int num = x > 0 ? x : -x;
        long ans = 0;
        while (num > 0) {
            ans *= 10;
            int mod = num % 10;
            if (mod != 0) {
                ans += mod;
            }
            num = num / 10;
        }
        ans = x > 0 ? ans : -ans;
        if (ans < Integer.MIN_VALUE || ans > Integer.MAX_VALUE) {
            ans = 0;
        }
        return (int) ans;
    }
}
