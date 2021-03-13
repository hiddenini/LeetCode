package linkedList;

/**
 * https://leetcode-cn.com/problems/partition-list/
 */
public class _86_分隔链表 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        //虚拟头节点
        ListNode lHead = new ListNode(0);
        ListNode lTail = lHead;
        //虚拟头节点
        ListNode rHead = new ListNode(0);
        ListNode rTail = rHead;

        while (head != null) {
            if (head.val < x) {
                lTail.next = head;
                lTail = head;
            } else {
                rTail.next = head;
                rTail = head;
            }
            head = head.next;
        }
        /**
         * 这句代码不能漏
         * 因为可能出现这样的情况,原链表的倒数第N个元素A>=X ，但是A后面的所有元素都是<X的，
         * 那么rTail的next就指向了A.next 不是null了
         */
        rTail.next = null;
        lTail.next = rHead.next;
        return lHead.next;
    }

}
