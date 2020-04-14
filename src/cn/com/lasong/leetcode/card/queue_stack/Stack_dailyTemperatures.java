package cn.com.lasong.leetcode.card.queue_stack;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/14
 * Description:
 * 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class Stack_dailyTemperatures {

    public static void main(String[] args) {
        Stack_dailyTemperatures dailyTemperatures = new Stack_dailyTemperatures();
        int[] ans = dailyTemperatures.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        ListHelper.print(ans);
    }

    /**
     * 使用栈来计数 栈中记录索引位置
     * 递减栈 如果碰到比栈顶大的值, 表示碰到了温度比栈顶日期高的位置
     * 更新栈顶日期的间隔为时间差
     *
     * 否则就入栈进行等待碰到温度比入栈高的日期出现
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int [] ans = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 使用数组二层遍历 找出间距
     * @param T
     * @return
     */
    public int[] dailyTemperatures_Array(int[] T) {
        int len =T.length;
        int [] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int daily = T[i];
            for (int j = i + 1; j < len; j++) {
                if(T[j] > daily) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }
}
