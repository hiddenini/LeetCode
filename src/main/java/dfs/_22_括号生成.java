package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class _22_括号生成 {
    /***
     *左右括号数量一样时，只能选择左括号
     *
     * 什么时候可以选择左括号(左括号数量大于0)
     *
     *  什么时候可以选择右括号(右括号数量大于0 &&右括号数量!= 左括号数量)
     */
    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<>();
        if (n < 0) return list;
        dfs(0, n, n, new char[n << 1], list);
        return list;
    }

    private void dfs(int idx, int leftRemain, int rightRemain, char[] string, ArrayList<String> list) {
        if (idx == string.length) {
            list.add(new String(string));
            return;
        }

        //枚举这一层所有的可能,选择一种可能之后,进入下一层搜索,下面其实也是一个for循环，但是拿出了循环体,下面的2块表示着每层的选择


        /**
         * 左右括号数量一样时，只能选择左括号
         * 什么时候可以选择左括号(左括号数量大于0)
         *
         * 选择左括号,然后进入下一层进行搜索
         */
        if (leftRemain > 0) {
            string[idx] = '(';
            dfs(idx + 1, leftRemain - 1, rightRemain, string, list);
        }


        /**
         * 什么时候可以选择右括号(右括号数量大于0 &&右括号数量!= 左括号数量)
         *
         * 选择左括号,然后进入下一层进行搜索
         */
        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            dfs(idx + 1, leftRemain, rightRemain - 1, string, list);
        }

    }

}
