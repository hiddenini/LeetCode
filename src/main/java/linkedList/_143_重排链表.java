package linkedList;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/reorder-list/
 */
public class _143_重排链表 {
    /**
     * 使用list保存链表
     */
    public void reorderList1(ListNode head) {
        if (head == null) return;
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0, j = list.size() - 1;

        while (i < j) {
            list.get(i).next = list.get(j);
            i++;

            if (i == j) break;

            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode middle = middleNode(head);
        ListNode right = middle.next;

        //将右边链表断开
        middle.next = null;


        //反转右边部分
        ListNode rHead = reverseList(right);

        //合并链表
        ListNode lHead = head;
        merge(lHead, rHead);
    }

    /**
     * 找到中间节点 如果是偶数个节点那么返回右边那个
     *
     * fast.next != null && fast.next.next != null 返回右边那个也没关系
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
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = temp;
        }
        return newHead;
    }

    /**
     * 合并链表
     */
    public void merge(ListNode lHead, ListNode rHead) {
        ListNode lTemp = null;
        ListNode rTemp = null;
        while (lHead != null && rHead != null) {
            lTemp = lHead.next;
            rTemp = rHead.next;

            lHead.next = rHead;
            lHead = lTemp;

            rHead.next = lHead;
            rHead = rTemp;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        _143_重排链表 obj = new _143_重排链表();
        obj.reorderList(head);
    }
}
