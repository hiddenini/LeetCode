package linkedList;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class _92_反转链表_II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        //找到left节点的前一个节点,注意pre是从dummy开始的
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        //继续从pre往后走,找到right节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        /**
         * 截取需要翻转的链表
         */
        ListNode leftNode = pre.next;

        //保存rightNode的next,后面链接在一起
        ListNode rightNext = rightNode.next;
        /**
         * 将pre 和rightNode从原链表中断开
         */
        pre.next = null;
        rightNode.next = null;

        ListNode newHead = reverseList(leftNode);
        /**
         * 将pre ->翻转后的链表->rightNext链接起来 翻转之后rightNode成了newHead leftNode成了尾节点
         *
         * pre->rightNode->leftNode->rightNext
         */
        pre.next = newHead;
        leftNode.next = rightNext;

        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
