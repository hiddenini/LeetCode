package linkedList;

import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class _19_删除链表的倒数第N个结点 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 为啥要用虚拟头节点,因为可以省去很多的判断
         */
        if (head == null) return null;
        int length = getLengthOfLinkedList(head);
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        /**
         * 遍历到要删除的节点的前一个
         */
        for (int i = 1; i < length - n + 1; i++) {
            node = node.next;
        }
        //这个地方如果链表只有一个节点,并且不使用dummy那么会报空指针
        node.next = node.next.next;
        return dummy.next;
    }

    public static int getLengthOfLinkedList(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * 只遍历一遍链表，使用双指针
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = head;
        ListNode second = dummy;
        /**
         * 让first指针先走n步
         */
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        /**
         * 再让first和second一起走，直到链表走完,即fist==null
         *
         * 由于second开始时指向虚拟头节点的所以走完之后
         *
         * second 的下一个节点就是需要删除的节点
         */
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    /**
     * 使用栈
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        //此时栈顶的元素时要删除节点的前驱
        ListNode node = stack.peek();
        node.next = node.next.next;
        return dummy.next;
    }
}

