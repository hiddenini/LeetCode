package string;

/**
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class _344_反转字符串 {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) return;
        int head = 0;
        int len = s.length;
        int tail = len - 1;
        while (head < tail) {
            swap(head, tail, s);
            head++;
            tail--;
        }
    }

    private void swap(int head, int tail, char[] s) {
        char temp = s[head];
        s[head] = s[tail];
        s[tail] = temp;
    }
}
