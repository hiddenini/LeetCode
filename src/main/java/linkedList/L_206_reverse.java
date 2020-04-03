package linkedList;

/**
 * @author xz
 * @date 2020/2/15 12:54
 **/

/**
 ◼ https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class L_206_reverse {

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 非递归
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead =null;
        while(head!=null){
            ListNode temp=head.next;
            head.next=newHead;
            newHead=head;
            head=temp;
        }
        return newHead;
    }
}
