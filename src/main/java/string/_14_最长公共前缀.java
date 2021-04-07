package string;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class _14_最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int length = strs[0].length();
        int count = strs.length;
        /**
         * 遍历第一个字符串的每一个字符
         */
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            /**
             * 遍历其他所有的字符串,拿到相同位置的字符
             */
            for (int j = 1; j < count; j++) {
                /**
                 *
                 * i == strs[j].length()意思是
                 *
                 * 如果后面的字符串的长度比第一个要小，那么遍历完较小的字符串就要结束,否则会越界
                 */
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        String s = "hello";
        //System.out.println(s.substring(0, 2));
        System.out.println(s.substring(0, 0));

    }
}
