package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 */
public class _155_最小栈_1 {

    private Node head;

    public _155_最小栈_1() {
        head = new Node(0, Integer.MAX_VALUE, null);
    }

    public void push(int x) {
        head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private static class Node {
        private int val;
        private int min;
        public Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
