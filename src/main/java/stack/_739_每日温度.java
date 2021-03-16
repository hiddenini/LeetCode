package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 */
public class _739_每日温度 {
    public int[] dailyTemperatures(int[] T) {
        int[] ris = new int[T.length];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int topIndex = stack.peek();
                ris[topIndex] = i - topIndex;
                stack.pop();
            }
            stack.push(i);
        }
        return ris;
    }
}
