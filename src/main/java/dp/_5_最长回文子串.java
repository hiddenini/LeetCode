package dp;

/***
 *
 *https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 */

public class _5_最长回文子串 {
    public String longestPalindrome(String s) {
        if (s == null) return null;
        if (s.length() == 0) return s;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        //最长回文子串的长度
        int maxLen = 1;
        //最长回文子串的开始索引
        int begin = 0;
        /**
         * 从下到上 i从大到小
         */
        for (int i = chars.length - 1; i >= 0; i--) {
            /**
             * 从左到右 j从i到 chars.length-1
             */
            for (int j = i; j < chars.length; j++) {
                /**
                 * chars[i,j]的长度
                 */
                int len = j - i + 1;
                dp[i][j] = (chars[i] == chars[j]) && (len <= 2 || dp[i + 1][j - 1]);
                //如果 chars[i,j]是回文子串
                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return new String(chars, begin, maxLen);
    }

    /**
     * 扩展中心法
     */
    public String longestPalindrome1(String s) {
        if (s == null) return null;
        if (s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        //最长回文子串的长度
        int maxLen = 1;
        //最长回文子串的开始索引
        int begin = 0;
        //从后往前扫描
        for (int i = chars.length - 2; i >= 1; i--) {
            /**
             *从i为中心，向2边扫描
             */
            int len1 = palindromeLength(chars, i - 1, i + 1);

            /**
             *从i右边的间隙为中心，向2边扫描 因为回文串也可能是偶数串 abba
             */
            int len2 = palindromeLength(chars, i, i + 1);

            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                /**
                 * 通过中心i和长度计算该串的起始索引
                 */
                begin = i - ((maxLen - 1) >> 1);
            }

        }

        /**
         * 处理0号字符右边的间隙
         */
        if (chars[0] == chars[1] && maxLen < 2) {
            maxLen = 2;
            begin = 0;
        }
        return new String(chars, begin, maxLen);
    }

    /**
     * 从L开始，向左扫描 从r开始，向右扫描 获得的最长回文子串的长度
     */
    private int palindromeLength(char[] cs, int l, int r) {
        while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }


    /**
     * 扩展中心法优化
     */
    public String longestPalindrome2(String s) {
        if (s == null) return null;
        if (s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        //最长回文子串的长度
        int maxLen = 1;
        //最长回文子串的开始索引
        int begin = 0;
        int i = 0;
        while (i < chars.length) {
            int l = i - 1;
            //找到右边第一个不等于char[i]的位置
            int r = i;
            while (++r < chars.length && chars[r] == chars[i]) ;
            //r会成为新的i
            i = r;
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                l--;
                r++;
            }
            //扩展结束后,chars[l+1,r)就是刚才找到的最长回文子串
            //++l后 l就是最长回文子串的开始索引
            int len = r - ++l;
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }

        }
        return new String(chars, begin, maxLen);
    }

    /**
     * 马拉车算法
     */

    private char[] preprocess(char[] oldCs) {
        char[] cs = new char[(oldCs.length << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < oldCs.length; i++) {
            int idx = (i + 1) << 1;
            cs[idx] = oldCs[i];
            cs[idx + 1] = '#';
        }
        return cs;
    }

    /**
     * 马拉车算法
     */
    public String longestPalindrome3(String s) {
        if (s == null) return null;
        char[] oldCs = s.toCharArray();
        if (oldCs.length <= 1) return s;

        // 预处理
        char[] cs = preprocess(oldCs);
        // 构建m数组
        int[] m = new int[cs.length];
        int c = 1, r = 1, lastIdx = m.length - 2;
        int maxLen = 0, idx = 0;
        for (int i = 2; i < lastIdx; i++) {
            if (r > i) {
                int li = (c << 1) - i;
                m[i] = (i + m[li] <= r) ? m[li] : (r - i);
            }

            // 以i为中心，向左右扩展
            while (cs[i + m[i] + 1] == cs[i - m[i] - 1]) {
                m[i]++;
            }

            // 更新c、r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }

            // 找出更大的回文子串
            if (m[i] > maxLen) {
                maxLen = m[i];
                idx = i;
            }
        }
        int begin = (idx - maxLen) >> 1;
        return new String(oldCs, begin, maxLen);
    }
}
