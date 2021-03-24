package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class _17_电话号码的字母组合_1 {
    private char[][] lettersArray = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };


    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;

        char[] chars = digits.toCharArray();
        List<String> list = new ArrayList<>();
        if (chars.length == 0) return list;
        char[] string = new char[chars.length];
        dfs(0, chars, string, list);
        return list;
    }

    /**
     * 搜索第idx层
     */
    private void dfs(int idx, char[] chars, char[] string, List<String> list) {
        //进入到最后一层,不能再深入,得到一个正确的解
        if (idx == chars.length) {
            list.add(new String(string));
            return;
        }
        char digit = chars[idx];
        char[] letters = lettersArray[digit - '2'];
        //先枚举这一层可以做的所有选择
        for (char letter : letters) {
            string[idx] = letter;
            dfs(idx + 1, chars, string, list);
        }
    }

}
