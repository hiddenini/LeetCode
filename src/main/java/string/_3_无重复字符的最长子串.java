package string;


import java.util.HashMap;
import java.util.Map;

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

    /**
     * 滑动窗口
     *
     * start不动，end向后移动
     *
     * 当end遇到重复字符，start应该放在上一个重复字符的位置的后一位，同时记录最长的长度
     *
     * 怎样判断是否遇到重复字符，且怎么知道上一个重复字符的位置？--用哈希字典的key来判断是否重复，
     *
     * 用value来记录该字符的下一个不重复的位置。
     *
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(c, end + 1);
        }
        return ans;
    }


    /**
     * map (k, v)，其中 key 值为字符，value 值为字符位置;
     */
    public int lengthOfLongestSubstring3(String s) {
        int length = s.length();
        int max = 0;

        Map<Character, Integer> map = new HashMap<>();
        for(int start = 0, end = 0; end < length; end++){
            char element = s.charAt(end);
            if(map.containsKey(element)){
                start = Math.max(map.get(element) + 1, start); //map.get()的地方进行+1操作
            }
            max = Math.max(max, end - start + 1);
            map.put(element, end);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        _3_无重复字符的最长子串 obj = new _3_无重复字符的最长子串();
        obj.lengthOfLongestSubstring1(s);


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
