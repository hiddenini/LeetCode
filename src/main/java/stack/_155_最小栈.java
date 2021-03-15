package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 */
public class _155_最小栈 {
    /**
     * 正常存放数据
     */
    private Stack<Integer> stack;
    /**
     * 存放当前push时的最小值
     */
    private Stack<Integer> minStack;

    public _155_最小栈() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
