package cn.com.lasong.leetcode.everyday;

import cn.com.lasong.leetcode.common.ListHelper;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-22
 * Description:
 * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/
 * 945. 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 *
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 *
 * 示例 1:
 *
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 *
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 提示：
 *
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 */
public class MinIncrementForUnique {

    public static void main(String[] args) {
        MinIncrementForUnique minIncrementForUnique = new MinIncrementForUnique();
//        int ans = minIncrementForUnique.minIncrementForUnique(new int[]{1,2,2});

//        int ans = minIncrementForUnique.minIncrementForUnique(new int[]{3,2,1,2,1,7});
//        int ans = minIncrementForUnique.minIncrementForUnique(new int[]{7,2,7,2,1,4,3,1,4,8});
        int ans = minIncrementForUnique.minIncrementForUnique(new int[]{2,1,1,5,3,0,2,1,0,1});
        System.out.println(ans);
    }
    public int minIncrementForUnique(int[] A) {
        return sortWay(A);
    }

    /**
     * 首先想到的就是遍历数组 用一个集合记录下出现过的数字
     * 如果出现过, 就自增, 并再次比较是否存在, 直到没有出现
     * 超时错误
     * @param A
     * @return
     */
    private int forceWay(int[] A) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int val = A[i];
            while (set.contains(val)) {
                ans++;
                // move
                val++;
            }
            set.add(val);
        }

        return ans;
    }

    /**
     * 第二个反应就是先进行排序, 这样就可以直接得到需要递增的次数, 而不需要每次都自增
     * 先遍历记录下每个数字的个数, 形成新的数组, 1个数组记录唯一的数字 1个数组记录数字对应的个数
     * 然后再次遍历进行对比, 如果当前数组对应的个数大于1时, 就取下一个数字与当前数字的差值
     * 表示可以容纳几个唯一的数字, 如果超过差值, 把剩下的归并到下一个数字的个数里
     * 依次类推, 最后再加上最后那个数字的个数大于1的部分即可
     * @param A
     * @return
     */
    private int sortWay(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        Map<Integer, Integer> countMap = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            int val = A[i];
            Integer cacheCount = countMap.get(val);
            int count = 1;
            if (null != cacheCount) {
                count += cacheCount;
            }
            countMap.put(val, count);
        }
        // +1 是为了后面统计做偏移方便
        int size = countMap.size() + 1;
        Set<Integer> set = countMap.keySet();

        int[] values = new int[size];
        int[] counter = new int[size];
        // 作为做大值来保证后面有足够空余位置可以存放最后一位多余的值
        values[size - 1] = Integer.MAX_VALUE;
        Iterator<Integer> iterator = set.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            values[index] = iterator.next();
            counter[index] = countMap.get(values[index]);
            index++;
        }

//        ListHelper.print(values);
//        ListHelper.print(counter);

        int ans = 0;
        for (int i = 0; i < size; i++) {
            System.out.println(i+"======>");
            ListHelper.print(values);
            ListHelper.print(counter);
            System.out.println("======>"+ans);
            int count = counter[i];
            // 小于等于1个 跳转到下一个值
            if (count <= 1) {
                continue;
            }
            int needCount = count - 1;
            // 当前数字 个数 大于1, 看看下一个值的中间是否有空位
            int value = values[i];
            int nextValue = values[i + 1];
            int nextCount = counter[i + 1];
            // 与下一个值没有空位, 直接累积到下一个值上
            // 在这里相当与移动了needCount步
            if (nextValue - value <= 1) {
                counter[i] = count - needCount;
                counter[i+1] = nextCount + needCount;
                ans += needCount;
                continue;
            }
            // 这里就是中间有空位
            // 剩余空位
            int leftCount = nextValue - value - 1;
            // 管够, 累加需要移动的次数
            if (leftCount >= needCount) {
                for (int j = 1; j <= needCount; j++) {
                    ans += j;
                }
                // 本数字置为1
                counter[i] = 1;
                continue;
            }

            // 不管够, 尽量用完
            for (int j = 1; j <= leftCount; j++) {
                ans += j;
            }

            leftCount = needCount - leftCount;
            counter[i+1] = nextCount + leftCount;
            counter[i] = 1;
            // 最后一位移动到下一个值, 移动步骤为值步, 并且是每个值
            ans += (nextValue - value) * leftCount;
        }

//        ListHelper.print(values);
//        ListHelper.print(counter);
        return ans;
    }
//    1,2,3,7,2147483647
//    6,2,1,1,0
}
