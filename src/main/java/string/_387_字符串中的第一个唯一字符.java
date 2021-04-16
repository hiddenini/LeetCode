package string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 */
public class _387_字符串中的第一个唯一字符 {

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 使用数组存贮字符和次数
     * 由于都是小写字母
     * 那么arr[0] 表示a的次数
     * 那么arr[1] 表示b的次数
     * ....
     * 以此类推
     */
    public int firstUniqChar1(String s) {
        int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
