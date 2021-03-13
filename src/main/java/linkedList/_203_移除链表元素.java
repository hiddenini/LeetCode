package linkedList;

public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        /**
         * 虚拟头节点,后面的代码可以节省很多判断
         */
        ListNode newHead = new ListNode(0);
        ListNode newTail = newHead;
        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }
        newTail.next = null;
        return newHead.next;
    }
}
