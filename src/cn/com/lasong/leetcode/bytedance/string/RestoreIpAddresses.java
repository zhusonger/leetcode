package cn.com.lasong.leetcode.bytedance.string;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-16
 * Description:
 * https://leetcode-cn.com/explore/featured/card/bytedance/242/string/1044/
 * 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {
        RestoreIpAddresses addresses = new RestoreIpAddresses();
        addresses.restoreIpAddresses("010010");
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> set = new ArrayList<>();
        if (s.length() >= 4) {
            int sLen = s.length();
            int maxIpLength = 3;
            for (int ip1Index = 1; ip1Index <= maxIpLength; ip1Index++) {
                int ip1EndIndex = ip1Index;
                // ip1 肯定是有效的
                String ip1 = s.substring(0, ip1EndIndex);
                // 不是有效的ip值, 跳过
                if (!isValidIpValue(ip1)) {
                    continue;
                }
                for (int ip2Index = 1; ip2Index <= maxIpLength; ip2Index++) {
                    int ip2EndIndex = ip1EndIndex + ip2Index;
                    // ip2超过字符串长度, 跳过
                    if (ip2EndIndex > sLen) {
                        continue;
                    }
                    String ip2 = s.substring(ip1EndIndex, ip2EndIndex);
                    // 不是有效的ip值, 跳过
                    if (!isValidIpValue(ip2)) {
                        continue;
                    }

                    for (int ip3Index = 1; ip3Index <= maxIpLength; ip3Index++) {
                        int ip3EndIndex = ip2EndIndex + ip3Index;
                        // ip3超过字符串长度, 跳过
                        if (ip3EndIndex > sLen) {
                            continue;
                        }
                        String ip3 = s.substring(ip2EndIndex, ip3EndIndex);
                        // 不是有效的ip值, 跳过
                        if (!isValidIpValue(ip3)) {
                            continue;
                        }
                        int leftLen = sLen - ip3EndIndex;
                        if (leftLen <= 0 || leftLen > 3) {
                            continue;
                        }

                        String ip4 = s.substring(ip3EndIndex, sLen);
                        // 不是有效的ip值, 跳过
                        if (!isValidIpValue(ip4)) {
                            continue;
                        }
                        String ip = ip1+"."+ip2+"."+ip3+"."+ip4;
                        //Integer.parseInt(ip1)+"."+Integer.parseInt(ip2)+"."+Integer.parseInt(ip3)+"."+Integer.parseInt(ip4);
                        if (set.contains(ip)) {
                            continue;
                        }
                        set.add(ip);
                        System.out.println(ip);
                    }
                }
            }
        }
        return set;
    }

    private boolean isValidIpValue(String ipValue) {
        if (ipValue.startsWith("0") && ipValue.length() > 1) {
            return false;
        }
        int ip = Integer.parseInt(ipValue);
        return ip >= 0 && ip < 256;
    }
}
