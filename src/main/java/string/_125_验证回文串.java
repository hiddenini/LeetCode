package string;


/**
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class _125_验证回文串 {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        int length = s.length();
        if (length == 0) return true;
        //只保留数字和字母生成一个新的字符串
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stringBuffer.append(Character.toLowerCase(ch));
            }
        }
        int len = stringBuffer.length();
        char[] chars = stringBuffer.toString().toCharArray();
        int i = 0, j = len - 1;
        while (i <= j) {
            if (chars[i] != chars[j]) return false;
            ++i;
            --j;
        }
        return true;
    }

    public boolean isPalindrome1(String s) {
        if (s == null) return false;
        int length = s.length();
        if (length == 0) return true;
        int left = 0, right = length - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            }
            ++left;
            --right;
        }
        return true;
    }
}
