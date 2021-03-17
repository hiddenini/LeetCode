package string;

/**
 * https://leetcode-cn.com/problems/valid-anagram/
 */
public class _242_有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        /**
         * count[0]=xx; 字符a的数量
         *
         * count[1]=xx; 字符b的数量
         */

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        if (sChars.length != tChars.length) return false;
        int counts[] = new int[26];
        for (int i = 0; i < sChars.length; i++) {
            counts[sChars[i] - 'a']++;
        }

        for (int i = 0; i < tChars.length; i++) {
            if (--counts[tChars[i] - 'a'] < 0) return false;
        }
        return true;
    }
}
