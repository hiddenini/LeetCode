package string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class _395_至少有K个重复字符的最长子串 {

    /**
     * 重点：我们在调用递归函数的时候，把递归函数当做普通函数（黑箱）来调用，即明白该函数的输入输出是什么，而不用管此函数内部在做什么。
     * 下面是详细讲解。
     *
     * 递归最基本的是记住递归函数的含义（务必牢记函数定义）：本题的 longestSubstring(s, k) 函数表示的就是题意，即求一个最长的子字符串的长度
     *该子字符串中每个字符出现的次数都最少为 kk。函数入参 ss 是表示源字符串；kk 是限制条件，即子字符串中每个字符最少出现的次数；函数返回结果是满足题意的最长子字符串长度。
     *
     * 递归的终止条件（能直接写出的最简单 case）：如果字符串 ss 的长度少于 kk，那么一定不存在满足题意的子字符串，返回 0；
     *
     * 调用递归（重点）：如果一个字符 cc 在 ss 中出现的次数少于 kk 次，那么 ss 中所有的包含 cc 的子字符串都不能满足题意。
     *
     * 所以，应该在 ss 的所有不包含 cc 的子字符串中继续寻找结果：把 ss 按照 cc 分割（分割后每个子串都不包含 cc），得到很多子字符串 tt
     *
     * 下一步要求 tt 作为源字符串的时候，它的最长的满足题意的子字符串长度（到现在为止，我们把大问题分割为了小问题(ss → tt)）
     *
     * 此时我们发现，恰好已经定义了函数 longestSubstring(s, k) 就是来解决这个问题的！所以直接把 longestSubstring(s, k) 函数拿来用，于是形成了递归。
     *
     * 未进入递归时的返回结果：如果 ss 中的每个字符出现的次数都大于 kk 次，那么 ss 就是我们要求的字符串，直接返回该字符串的长度。
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Character character : map.keySet()) {
            if (map.get(character) < k) {
                int res = 0;
                for (String s1 : s.split(String.valueOf(character))) {
                    res = Math.max(res, longestSubstring(s1, k));
                }
                return res;
            }
        }
        return s.length();
    }
}
