package linkedList;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class _23_合并K个升序链表 {
    /**
     * 最朴素的方法
     * <p>
     * 时间复杂度为 O(k^2 n)
     * <p>
     * 空间复杂度为 O(1)O(1)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
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


    /**
     * 使用优先队列 首先将比较每个链表的头节点offer到优先队列中,poll得到最小的那个,虚拟头节点和尾节点分别指向这个节点
     * <p>
     * 该节点成为新的尾节点,如果该节点的next不为空,入队,重复上面的步骤,直到queue为空
     * 时间复杂度为O(kn*logk)
     * 空间复杂度为 O(k)O(k)
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            tail.next = poll;
            tail = tail.next;
            if (poll.next != null) {
                queue.offer(poll.next);
            }
        }
        return dummyHead.next;
    }

}
