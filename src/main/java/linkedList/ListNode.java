package linkedList;

/**
 * @author xz
 * @date 2020/2/15 12:32
 **/

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + " -> " + next;
    }
}
