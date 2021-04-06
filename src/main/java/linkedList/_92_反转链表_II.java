package linkedList;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * <p>
 * 在用递归思想解题时，明确递推公式的含义后
 * <p>
 * 不要试图想明白每一步是如何递归的，这很容易把自己绕晕哈
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

    /**
     * 上面的算法在如果 left 和 right 的区域很大
     * 恰好是链表的头节点和尾节点时，找到 left 和 right 需要遍历一次
     * 反转它们之间的链表还需要遍历一次
     * <p>
     * 可以遍历一次就完成
     */
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        //找到left节点的前一个节点,注意pre是从dummy开始的
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }
}
