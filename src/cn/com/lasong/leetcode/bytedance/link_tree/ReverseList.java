package cn.com.lasong.leetcode.bytedance.link_tree;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-03-17
 * Description:
 * https://leetcode-cn.com/explore/featured/card/bytedance/244/linked-list-and-tree/1038/
 * 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList {

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        reverseList.reverseList(ZNodeHelper.createList(new Integer[] {1,2,3,4,5}));
    }

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        // 1 2 3
        // 手上没有纸笔, 不能推算, 那就用举例法, 先弄一个可以遍历的过程 比如1,2,3
        // 写完之后就是把重复的部分提取成循环

        // 记录之前的值, 用来更新 反转的head的next值
        ListNode pre = null;
        // 返回反转head
        ListNode rHead = null;
        while (head != null) {
//            System.out.println(head.val);
            ListNode next = head.next; // next 2      next 3        next null
            head.next = pre; // head(1).next = pre(null)  head(2).next = pre(1)  head(3).next = pre(2)
            pre = head; // pre = head(1)    pre = head(2) pre = head(3)
            // 遍历完链表, 更新反转头Head
            if (null == next) {
                rHead = head;
            }
            head = next; // head = next(2) head = next(3) head = next(null)
        }

        return rHead;
    }
}
