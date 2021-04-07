package linkedList;

import commom.TreeNode;

/**
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class _109_有序链表转换二叉搜索树 {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }

    public ListNode middleNode(ListNode head,ListNode tail) {
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        ListNode slow = middleNode(head, tail);
        TreeNode root = new TreeNode(slow.val);
        //递归构建左右子树
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }


}
