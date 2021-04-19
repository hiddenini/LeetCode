package linkedList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class _142_环形链表_II {
    public ListNode detectCycle(ListNode head) {
        ListNode cur = head;
        Set<ListNode> visited = new HashSet<>();
        while (cur != null) {
            if (visited.contains(cur)) {
                return cur;
            }
            visited.add(cur);
            cur = cur.next;
        }
        return null;
    }

    /**
     * 快慢指针
     */
    public ListNode detectCycle1(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }

        ListNode pre = head;
        while (pre != fast) {
            pre = pre.next;
            fast = fast.next;
        }
        return pre;
    }

}
