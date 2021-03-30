package linkedList;

/**
 * @author xz
 * @date 2020/2/15 12:54
 **/

/**
 * ◼ https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class L_206_reverse {

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        /**
         * 原链表 5->4->3->2->1->null
         *
         * 函数的作用是传入一个节点node 4,翻转后的链表是 1->2->3->4->null
         *
         * 那么执行完下一句代码之后,就只剩头节点需要串 head此时还是指向5 head
         *
         * head.next=4
         *
         * 4.next指向5
         *
         * 即head.next.next=head;
         *
         * 5指向null
         * head.next=null;
         *
         */
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 非递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
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
