package cn.com.lasong.leetcode.bytedance.link_tree;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 */
public class ZNodeHelper {

    public static ListNode createList(int[] array) {
        ListNode head = null;
        ListNode ptr = null;
        for (int item : array) {
            ListNode node = new ListNode(item);
            if (null == head) {
                head = node;
            }
            if (null != ptr) {
                ptr.next = node;
            }
            ptr = node;
        }

        return head;
    }

    public static void print(ListNode node) {
        if (null == node) {
            return;
        }

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
