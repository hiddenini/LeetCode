package linkedList;

/**
 * https://leetcode-cn.com/problems/sort-list/
 */
public class _148_排序链表 {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //ListNode slow = middleNode(head);

        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = mergeTwoLists(list1, list2);
        return sorted;
    }

    /**
     * 使用快慢指针找到中间节点
     */
    private ListNode middleNode(ListNode head) {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        /**
         * 虚拟头节点
         */
        ListNode dummyHead = new ListNode(-1);
        /**
         * 串链表用 当前遍历到的节点
         */
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }


    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null)
            return head;
/*        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }*/
        ListNode slow = middleNode(head);
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        return mergeTwoLists(left, right);
    }

}
