package string;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class _3_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        //用来保存每一个字符上一次出现的位置
        HashMap<Character, Integer> preIdxes = new HashMap<>();
        //第一个元素上一次出现的位置就是0
        preIdxes.put(chars[0], 0);
        //以i-1位置字符结尾的最长不重复子串的开始位置(最左索引)
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            //i位置字符上一次出现的位置
            Integer pi = preIdxes.get(chars[i]);
            //如果一个字符在之前一直没有出现过 ,即pi==null，那么和pi<li一样的情况
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            //存储这个字符出现的位置
            preIdxes.put(chars[i], i);
            //求出最长不重复子串的长度
            max = Math.max(max, i - li + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        //用来保存每一个字符上一次出现的位置
        /**
         * 全部是单字符 范围是从0~128
         */
        int[] preIdxes = new int[128];
        for (int i = 0; i < preIdxes.length; i++) {
            preIdxes[i] = -1;
        }
        //第一个元素上一次出现的位置就是0
        preIdxes[chars[0]] = 0;
        //以i-1位置字符结尾的最长不重复子串的开始位置(最左索引)
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            //i位置字符上一次出现的位置
            Integer pi = preIdxes[chars[i]];
            //如果一个字符在之前一直没有出现过 ,即pi==null，那么和pi<li一样的情况
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            //存储这个字符出现的位置
            preIdxes[chars[i]] = i;
            //求出最长不重复子串的长度
            max = Math.max(max, i - li + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "abc";
        char[] chars = s.toCharArray();
        int[] array = new int[128];
        array[97] = 7;
        for (int i = 0; i < s.length(); i++) {
            System.out.println(chars[i]);
            System.out.println(array[chars[i]]);
        }
        System.out.println(array['a']);

    }
}
