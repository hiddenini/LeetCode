package dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/word-break/
 */
public class _139_单词拆分 {
    Map<String, Boolean> map = new HashMap<>();

    /**
     * 动态规划算法，dp[i]表示s前i个字符能否拆分
     * 转移方程：dp[j] = dp[i] && check(s[i+1, j]);
     * check(s[i+1, j])就是判断i+1到j这一段字符是否能够拆分
     * <p>
     * <p>
     * 假如wordDict=["apple", "pen", "code"],s = "applepencode";
     * dp[8] = dp[5] + check("pen")
     * 翻译一下：前八位能否拆分取决于前五位能否拆分，加上五到八位是否属于字典
     * <p>
     * dp[8] = dp[4] + check("epen")
     * dp[8] = dp[3] + check("lepen")
     * dp[8] = dp[2] + check("pepen")
     * ...
     * 只要有一个成立 则break
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        //记录单词的最大长度
        int wordMaxLen = 0;
        for (String s1 : wordDict) {
            map.put(s1, true);
            wordMaxLen = Math.max(wordMaxLen, s1.length());
        }
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            /**
             * 优化 j只需要走到单词表里面最大长度即可即j>=i- wordMaxLen 后面的单词不可能出现在字典之中
             */
            for (int j = i - 1; j >= 0 && j >= i - wordMaxLen; j--) {
                dp[i] = dp[j] && check(s.substring(j, i));
                if (dp[i]) break;
            }
        }

        return dp[s.length()];
    }

    private boolean check(String substring) {
        return map.getOrDefault(substring, false);
    }
}
