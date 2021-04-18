package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 */
public class _131_分割回文串 {
    /**
     * 方法1
     */
    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        /**
         *
         */
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }

        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    //双指针判断是否是回文
    public boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 方法2
     */
    List<List<String>> res;
    List<String> path;
    int len;
    char[] arr;

    public List<List<String>> partition1(String s) {
        //初始化
        this.res = new ArrayList<>();
        this.path = new ArrayList<>();
        this.len = s.length();
        this.arr = s.toCharArray();
        backtrack(0);
        return res;
    }

    //index 为起始位置
    public void backtrack(int index) {
        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            if (isPalindrome(index, i)) {
                path.add(new String(arr, index, i + 1 - index));
                backtrack(i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    //判断是否是回文
    public boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 再写一遍
     */
    List<List<String>> result;
    List<String> list;
    int length;
    char[] chars;

    public List<List<String>> partition2(String s) {
        this.result = new ArrayList<>();
        list = new ArrayList<>();
        length = s.length();
        chars = s.toCharArray();
        dfs1(0);
        return result;
    }

    private void dfs1(int index) {
        if (index == length) {
            result.add(new ArrayList<>(list));
            return;
        }

        /**
         *先枚举这一层可以做的所有选择
         * 从index 开始 直到length都是可以选择的
         */
        for (int i = index; i < length; i++) {
            //判断是否是回文串
            if (isPalindrome(chars, index, i)) {
                //如果是 添加一个到list中
                list.add(new String(chars, index, i + 1 - index));
                //钻到下一层 即跳到下一个index 因为流程就是从0 开始一直往后直到结束
                dfs1(index + 1);
                //还原现场
                list.remove(list.size() - 1);
            }

        }
    }

    //判断是否是回文
    public boolean isPalindrome1(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        _131_分割回文串 obj = new _131_分割回文串();
        List<List<String>> aab = obj.partition2("aab");
        //List<List<String>> aab = obj.partition1("aab");
        for (List<String> list : aab) {
            System.out.println("==");
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
