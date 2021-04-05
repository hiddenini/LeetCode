package linkedList;

/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class _24_两两交换链表中的节点 {

    /**
     * 不需要关注每一次递归的细节,只需要理解递归函数的意义,然后选取最小的单位进行推进
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode sub = swapPairs(head.next.next);
        ListNode headNext = head.next;
        headNext.next = head;
        head.next = sub;
        return headNext;
    }

    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        /**
         * cur 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换
         */
        while (cur.next != null || cur.next.next != null) {
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            cur.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            /**
             * 让cur指向node1 继续即node1成为新的dummy 继续交换后面的节点
             */
            cur = node1;
        }
        return dummy.next;
    }

}
