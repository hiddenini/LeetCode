package Queue;

import java.util.Stack;

/**
 * @author xz
 * @date 2020/2/18 13:29
 **/
//https://leetcode-cn.com/problems/implement-queue-using-stacks/ 使用栈实现队列
public class L_232_QueueUseStack {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public L_232_QueueUseStack() {
        inStack=new Stack<Integer>();
        outStack=new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
            inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack(){
        if(outStack.isEmpty()){
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }

}
