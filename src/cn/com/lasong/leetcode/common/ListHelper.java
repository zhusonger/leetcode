package cn.com.lasong.leetcode.common;

import java.util.List;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 */
public class ListHelper {

    public static void print(List<?> list) {
        if (null == list) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (Object item : list) {
            builder.append(item);
        }
        System.out.println(builder.toString());
    }
}
