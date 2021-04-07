package linkedList;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_链表的中间结点 {
    /**
     * 如果有两个中间结点，下面返回的时第二个中间结点,右边那个。
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        /**
         * fast != null 节点数量是偶数时，最后一步fast==null
         *  fast.next != null 节点数量是奇数时，最后一步fast.next==null
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 使用快慢指针找到中间节点
     * 如果有两个中间结点，下面返回的时第一个中间结点,左边那个。
     */
    private ListNode middleNode1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        /**
         * fast.next != null  节点是奇数个
         *
         * fast.next.next != null 节点是偶数个
         */
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public ListNode middleNode(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        //head.next.next.next.next = new ListNode(5);
        _876_链表的中间结点 o = new _876_链表的中间结点();
        System.out.println(o.middleNode(head).val);
        System.out.println(o.middleNode1(head).val);

    }
}
