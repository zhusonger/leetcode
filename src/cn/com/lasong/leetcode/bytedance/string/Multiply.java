package cn.com.lasong.leetcode.bytedance.string;

import java.util.Arrays;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-16
 * Description:
 * https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1015/
 * 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Multiply {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "0";
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] num1Array = num1.toCharArray();
        char[] num2Array = num2.toCharArray();

        int num1Len = num1Array.length;
        int num2Len = num2Array.length;
        int resultLen = num1Len + num2Len;
        // 乘积之后最大长度就是num1Len + num2Len
        char[] result = new char[resultLen];
        Arrays.fill(result, '0');
        int resultIndex = resultLen - 1;
        int appendI = 0;
        for (int i = num1Len - 1; i >= 0 ; i--) {
            for (int j = num2Len - 1, offset = 0; j >= 0; j--, offset++) {
                int num2I = num2Array[j] - '0';
                int num1I = num1Array[i] - '0';
                int value = num1I * num2I;
                // 进的值
                int outI = value / 10;
                // 当前位的值
                int curI = value % 10;
                int resultCurIndex = resultIndex - offset;
                // 结果当前位的值
                int resultCurI = result[resultCurIndex] - '0';
                // 累加得到当前位与结果位
                resultCurI += curI;
                // 累加之前的进位
                resultCurI += appendI;
                // 当前位跟之前的当前结果位相加, 累加进的值
                outI += resultCurI / 10;
                resultCurI = resultCurI % 10;
                appendI = outI;

                result[resultCurIndex] = (char) (resultCurI + '0');
                // 计算到最后一位时, 更新前面那位的数值, 并重置进数
                if (j == 0) {
                    int resultFirstIndex = resultCurIndex - 1;
                    int resultFirstI = result[resultFirstIndex] - '0';
                    resultFirstI += appendI;
                    result[resultFirstIndex] = (char) (resultFirstI + '0');
                    appendI = 0;
                }
            }
            resultIndex--;
        }
        int offset = 0;
        for (int i = 0; i < resultLen; i++) {
            if(result[i] != '0') {
                offset = i;
                break;
            }
        }
        int len = resultLen - offset;
        char[] r = new char[len];
        System.arraycopy(result, offset, r, 0, len);
        return String.valueOf(r);
    }
}
