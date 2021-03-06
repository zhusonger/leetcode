package cn.com.lasong.leetcode.common;

import cn.com.lasong.leetcode.common.ListNode;
import cn.com.lasong.leetcode.common.TreeNode;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 */
public class NodeHelper {

    public static ListNode createList(Integer[] array) {
        if (null == array || array.length == 0) {
            return null;
        }
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

    public static TreeNode createTree(Integer[] array) {
        return createTree(array, 0);
    }
    public static TreeNode createTree(Integer[] array, int index) {
        if (null == array || array.length == 0 || index >= array.length || array[index] == null) {
            return null;
        }
        int value = array[index];
        TreeNode root = new TreeNode(value);
        root.left = createTree(array, index * 2 + 1);
        root.right = createTree(array, index * 2 + 2);
        return root;
    }

    public static void print(ListNode node) {
        if (null == node) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            int val = node.val;
            node = node.next;
            builder.append(val);
            if (null != node) {
                builder.append(",");
            }
        }
        System.out.println(builder.toString());
    }
}
