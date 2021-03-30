package linkedList;

/**
 * @author xz
 * @date 2020/2/15 12:43
 **/


/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class L_141_Cycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
