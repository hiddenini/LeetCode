package string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/remove-k-digits/
 */
public class _402_移掉K位数字 {
    /**
     * 在使用 kk 个删除次数之前 保持栈单调不降
     */
    public String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > chars[i]) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(chars[i]);
        }

        /**
         * 如果删除的数据不足k那么删除尾部的元素直到k
         */
        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            Character pop = deque.pollFirst();
            if (leadingZero && pop == '0') {
                continue;
            }
            leadingZero = false;
            sb.append(pop);
        }
        String s = sb.toString();
        return s.equals("") ? "0" : s;
    }

    public static void main(String[] args) {
        _402_移掉K位数字 obj = new _402_移掉K位数字();
        System.out.println(obj.removeKdigits("10200", 1));
    }
}
