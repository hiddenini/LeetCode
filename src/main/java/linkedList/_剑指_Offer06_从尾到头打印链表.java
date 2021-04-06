package linkedList;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class _剑指_Offer06_从尾到头打印链表 {
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.add(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }

    public int[] reversePrint1(ListNode head) {
        if (head == null) return new int[0];
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int[] arr = new int[size];
        cur = head;
        while (cur != null) {
            arr[size - 1] = cur.val;
            size--;
            cur = cur.next;
        }
        return arr;
    }

}
