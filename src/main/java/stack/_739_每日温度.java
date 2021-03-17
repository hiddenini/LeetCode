package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 */
public class _739_每日温度 {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return null;
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


    //倒推法
    public int[] dailyTemperatures1(int[] T) {
        int[] values = new int[T.length];
        //从后往前遍历
        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    values[i] = j - i;
                    //break之后会进入新的循环,i会--
                    break;
                } else if (values[j] == 0) {
                    values[i] = 0;
                    break;
                } else if (T[i] == T[j]) {
                    values[i] = values[j] + j - i;
                    break;
                } else {
                    j = values[j] + j;
                }
            }
        }
        return values;
    }

    //倒推法
    public int[] dailyTemperatures2(int[] T) {
        int[] values = new int[T.length];
        //从后往前遍历
        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    values[i] = j - i;
                    //break之后会进入新的循环,i会--
                    break;
                } else if (values[j] == 0) {
                    values[i] = 0;
                    break;
                }
                j = values[j] + j;

            }
        }
        return values;
    }
}
