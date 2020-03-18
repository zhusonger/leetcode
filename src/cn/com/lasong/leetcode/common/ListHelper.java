package cn.com.lasong.leetcode.common;

import java.util.ArrayList;
import java.util.Arrays;
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
            builder.append(item).append(",");
        }
        if(builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }

    public static void print(int[] ans) {
        List<Integer> list = new ArrayList<>();
        for (int item : ans) {
            list.add(item);
        }
        print(list);
    }
}
