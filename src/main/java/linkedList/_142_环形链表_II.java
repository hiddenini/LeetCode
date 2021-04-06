package linkedList;

import java.util.HashSet;
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


}
