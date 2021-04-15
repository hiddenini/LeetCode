package string;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 */
public class _316_去除重复字母 {
    /**
     * 要求一、要去重。
     * <p>
     * 要求二、去重字符串中的字符顺序不能打乱 s 中字符出现的相对顺序。
     * <p>
     * 要求三、在所有符合上一条要求的去重字符串中，字典序最小
     */

    /**
     * 先不考虑要求三
     */
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            if (inStack[c]) continue;
            stack.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public String removeDuplicateLetters1(String s) {
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            if (inStack[c]) continue;
            /**
             * 插入之前和之前的元素比较下大小
             * 如果字典序比前面的小,那么弹出栈顶元素
             *
             * 这里是有问题的，因为可能某些字符在整个字符串只出现了一次，在这里pop之后,后面就丢失了
             */
            while (!stack.isEmpty() && stack.peek() > c) {
                //弹出并设置没有出现过
                inStack[stack.pop()] = false;
            }
            stack.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public String removeDuplicateLetters2(String s) {
        /**
         * 添加一个数组记录每个字符出现的次数
         */
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            //遍历到一个字符 将其次数减1
            count[c]--;
            if (inStack[c]) continue;
            /**
             * 插入之前和之前的元素比较下大小
             * 如果字典序比前面的小,那么弹出栈顶元素
             * 保持栈单调不升
             */
            while (!stack.isEmpty() && stack.peek() > c) {
                if (count[stack.peek()] == 0) break;
                //弹出并设置没有出现过
                inStack[stack.pop()] = false;
            }
            stack.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        String s = "bcabc";
        _316_去除重复字母 obj = new _316_去除重复字母();
        //输出bca
        System.out.println(obj.removeDuplicateLetters(s));
        System.out.println(obj.removeDuplicateLetters1(s));
        String s1 = "bcac";
        //输出ad 是错的
        System.out.println(obj.removeDuplicateLetters1(s1));
        System.out.println(obj.removeDuplicateLetters2(s1));

    }
}
