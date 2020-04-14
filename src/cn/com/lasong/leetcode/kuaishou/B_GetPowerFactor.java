package cn.com.lasong.leetcode.kuaishou;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/14
 * Description:
 * 无重复幂因子的N进制完美数之和
 * R = N ^ M
 * R为完美数 N为进制 M为幂因子
 * 无重复幂因子, 就是M不重复
 * 比如
 * 39 3
 * 输出 [1 2 3]
 * 39 = 3 + 3^2 + 3^3
 *
 * 33 3
 * 输出[]
 * 33 = 3 + 3 + 3^3
 * 1 1 3 ==> 1重复
 *
 * 1 3
 * 输出 [0]
 * 1 = 3^0
 */
public class B_GetPowerFactor {

    public static void main(String[] args) {
        B_GetPowerFactor getPowerFactor = new B_GetPowerFactor();
        List<Integer> ans = getPowerFactor.getPowerFactor(33, 3);
        ListHelper.print(ans);
    }

    /**
     * 题目其实就是进制转换
     *
     * 10进制R 转换成 N进制
     *
     * 无重复就是N进制的每一位都 <=1
     *
     * 那只要求出进制即可
     *
     * 通过取余得到 当前位的进制数
     * 进制转换
     *   2 |42     --> 0        从上往下就是2进制
     *      --
     *     2|21     ---> 1
     *       --
     *       10
     *          ...
     *
     *
     * @param R
     * @param N
     * @return
     */
    public List<Integer> getPowerFactor(int R, int N) {
        List<Integer> ans = new ArrayList<>();
        if (R == 1) {
            ans.add(0);
            return ans;
        }
        if (N > R) {
            return ans;
        }
        StringBuilder builder = new StringBuilder();
        do {
            int mod = R % N;
            // 出现重复的, 清空并结束
            if (mod > 1) {
                ans.clear();
                break;
            }
            builder.insert(0, mod);
            // 有1的位置表示有一个幂因子, 加到数组
            if (mod > 0) {
                ans.add(builder.length() - 1);
            }
            R = R / N;
        } while (R > 0);

        return ans;
    }
}
