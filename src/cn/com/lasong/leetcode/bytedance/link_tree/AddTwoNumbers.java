package cn.com.lasong.leetcode.bytedance.link_tree;

import cn.com.lasong.leetcode.common.ListNode;
import cn.com.lasong.leetcode.common.NodeHelper;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * https://leetcode-cn.com/explore/featured/card/bytedance/244/linked-list-and-tree/1022/
 * 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = NodeHelper.createList(new Integer[]{1, 1, 9});
        ListNode l2 = NodeHelper.createList(new Integer[]{5, 6, 4});
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode head = addTwoNumbers.addTwoNumbers(l1, l2);
        NodeHelper.print(head);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 这里思路很简单了, 因为它给的链表已经是倒叙的了, 不然还得自己再做一下倒叙
        // 回忆一下加法, 是不是从最后一位相加往前进数
        // 按照这个思路写代码即可
        ListNode l1Node = l1;
        ListNode l2Node = l2;
        ListNode lNode = null;
        ListNode lHeadNode = null;
        int appendI = 0;

        while (l1Node != null || l2Node != null) {
            int l1Value = null != l1Node ? l1Node.val : 0;
            int l2Value = null != l2Node ? l2Node.val : 0;
            // 更新下一位
            l1Node = null != l1Node ? l1Node.next : null;
            l2Node = null != l2Node ? l2Node.next : null;

            int aValue = l1Value + l2Value;
            // 计算这一位最终相加的值
            aValue += appendI;

            // 更新下一位进的值
            appendI = aValue / 10;
            // 当前位的值
            int cValue = aValue % 10;

            // 得到当前计算的Node
            ListNode cNode = new ListNode(cValue);
            if (null != lNode) {
                lNode.next = cNode;
            }
            lNode = cNode;

            if (null == lHeadNode) {
                lHeadNode = lNode;
            }
//            System.out.println(cNode.val);
        }
        // 补上最后一位
        if (appendI > 0) {
            lNode.next = new ListNode(appendI);
        }

        return lHeadNode;
    }
}
