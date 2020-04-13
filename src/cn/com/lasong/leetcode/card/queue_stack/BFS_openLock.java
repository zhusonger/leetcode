package cn.com.lasong.leetcode.card.queue_stack;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/04/13
 * Description:
 * https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/873/
 * 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 *
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *
 *
 * 提示：
 *
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 */
public class BFS_openLock {

    /**
     * 最短路径问题
     * deadends 其实就是是起始默认已经访问过的节点
     * target是目标节点
     * 起始节点 0000
     * 目标节点 target
     *
     * 可以理解为一个图
     *
     *         9000    0100
     *  1000 <--- [0000] ---> 0900
     *    0010  0090  0001 0009
     *
     * 4位数字, 每一位都有2种可能(每次只能转一个)
     * 从0000出发有8个可能下一个节点
     *
     * 剩余就是分支再继续扩展
     * 就是从0000 到 target的最短路径问题
     *
     * 默认再把deadends 加入到访问过的集合中
     *
     * 每次访问一个节点之后, 生成它的8种可能节点, 加入除了visited节点进行后续的广度查询
     *
     * 没有找到直接返回-1
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {


        Set<String> visited = new HashSet<>();
        Collections.addAll(visited, deadends);

        if (visited.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        int ans = -1;
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String node = queue.poll();
                // 找到目标, 返回
                if (node.equals(target)) {
                    return ans;
                }

                // 找到当前节点的邻居节点
                // 0000 1次转动有8种可能, 每一位有2种可能 2 x 4
                // 1000 9000 0100 0900 0010 0090 0001 0009
                // 4位
                char[] array = node.toCharArray();
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    char item = array[j];
                    // 向上加
                    array[j] = item == '9' ? '0' : (char)(item + 1);
                    String append = String.valueOf(array);
                    if (!visited.contains(append)) {
                        list.add(append);
                    }
                    // 向下减
                    array[j] = item == '0' ? '9' : (char)(item - 1);
                    String decrease = String.valueOf(array);
                    if (!visited.contains(decrease)) {
                        list.add(decrease);
                    }
                    // 还原这位
                    array[j] = item;
                }

                // 遍历邻居节点, 只加入未访问过的
                for (String item : list) {
                    if (!visited.contains(item)) {
                        visited.add(item);
                        queue.offer(item);
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BFS_openLock openLock = new BFS_openLock();
        int ans = openLock.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888");
        System.out.println(ans);
    }

}
