package hf;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class _150_逆波兰表达式求值 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(cal(num2, num1, token));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    int cal(int a, int b, String d) {
        int ans = 0;
        switch (d) {
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = a - b;
                break;
            case "*":
                ans = a * b;
                break;
            case "/":
                ans = a / b;
                break;
        }
        return ans;
    }

    public static void main(String[] args) {
        _150_逆波兰表达式求值 obj = new _150_逆波兰表达式求值();
        String[] str = new String[]{"4","13","5","/","+"};
        obj.evalRPN(str);

    }
}
