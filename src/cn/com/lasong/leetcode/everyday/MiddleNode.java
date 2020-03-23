package cn.com.lasong.leetcode.everyday;

import cn.com.lasong.leetcode.common.ListHelper;
import cn.com.lasong.leetcode.common.ListNode;
import cn.com.lasong.leetcode.common.NodeHelper;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/23
 * Description:
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 876. 链表的中间结点
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 *
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 *
 * 提示：
 *
 * 给定链表的结点数介于 1 和 100 之间。
 */
public class MiddleNode {

    public static void main(String[] args) {
        MiddleNode middleNode = new MiddleNode();
        ListNode head = NodeHelper.createList(new Integer[] {1,2,3,4,5,6});
        ListNode ans = middleNode.middleNode(head);
        NodeHelper.print(ans);

    }
    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode ptr = head;
        while (null != ptr) {
            size++;
            ptr = ptr.next;
        }

        if (size <= 1) {
            return head;
        }
        int center = size / 2;
        ptr = head;
        int index = 0;
        while (null != ptr) {
            index++;
            ptr = ptr.next;
            if (index == center) {
                break;
            }
        }

        return ptr;
    }

}
