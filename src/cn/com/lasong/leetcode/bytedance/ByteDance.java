package cn.com.lasong.leetcode.bytedance;

import cn.com.lasong.leetcode.common.ListHelper;
import cn.com.lasong.leetcode.common.NodeHelper;
import cn.com.lasong.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020/03/19
 * Description:
 *
 * （求最大子数组的和）、（二叉树非递归后序遍历）、（找无序数组的中位数）、（整型无序数组a，第i下标的值为ai，找任意一对下标（i,j）使得i<j 且ai<aj）
 */
public class ByteDance {

    public static void main(String[] args) {
        ByteDance byteDance = new ByteDance();
        int[] nums;
        nums = ListHelper.createArray(5, -10);
        byteDance.maxSubArray(nums);

        Integer[] tree = new Integer[] {1, 3, 5, 4, 6};
        TreeNode node = NodeHelper.createTree(tree, 0);
        byteDance.treeTraversalNoRecur(node);

        nums = ListHelper.createArray(5);
        byteDance.findMedian(nums);


    }

    /**
     * 贪心/动态规划
     * @param nums
     */
    public void maxSubArray(int[] nums) {
        System.out.println("====最大子序和====");
        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, nums.length);
        int ans = 0;
        int sum = 0;
        // 贪心
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(nums[i], sum+nums[i]);
            ans = Math.max(ans, sum);
        }
        System.out.print("贪心:" + ans+" nums: ");
        ListHelper.print(temp);

        System.arraycopy(temp, 0, nums, 0, nums.length);
        // 动态规划
        // 更新前一个值为最大值
        for (int i = 1; i < nums.length; i++) {
            int pre = nums[i - 1];
            if (pre >= 0) {
                nums[i] += pre;
            }
            ans = Math.max(nums[i], ans);
        }
        System.out.print("动态规划:" + ans + " nums: ");
        ListHelper.print(temp);
    }

    /**
     * 前序 root->left->right
     * 后序 left->right->root
     * @param root
     */
    public void treeTraversalNoRecur(TreeNode root) {
        System.out.println("====二叉树非递归后序遍历====");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root;
        List<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty() || null != ptr) {
            if (ptr != null) {
//                ans.add(0, ptr.val);
                stack.push(ptr);
//                ptr = ptr.left;
                ptr = ptr.right;
            } else {
                TreeNode node = stack.pop();
//                ptr = node.right;
                ptr = node.left;
            }
        }
        System.out.print("后序非递归遍历: ");
        ListHelper.print(ans);
    }

    public void findMedian(int[] nums) {
        System.out.println("====找无序数组的中位数====");
        int heapSize = nums.length / 2 + 1;
        // 把后半部分有序的取出来, 额外+1确保偶数时的平均数
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(heapSize);
        for (int i = 0; i < heapSize; i++) {
            minHeap.add(nums[i]);
        }

        for (int i = heapSize; i < nums.length; i++) {
            if (minHeap.peek() < nums[i]) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        double ans = 0;
        if (nums.length % 2 == 0) {
            ans = (minHeap.poll() + minHeap.poll()) / 2.0f;
        } else {
            ans = minHeap.poll();
        }
        System.out.print("无序中位数:" + ans +" nums:");
        ListHelper.print(nums);
    }
}
