package sort_array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets/
 */
public class _78_子集 {
    /**
     * dfs基本思路
     * <p>
     * 递归(参数**){
     * if（终止条件）{
     * 要做的具体操作
     * return;//ruturn 是必须加的，要不然方法没有结束，会StackOverflow，也就是一直在递归没有穷尽
     * }
     * for（循环遍历）{
     * //先枚举这一层可以做的所有选择
     * 要做的操作（比如添加元素）
     * <p>
     * 跳入下一层
     * 递归（参数***）
     * <p>
     * 撤销刚刚做的操作
     * }
     * <p>
     * }
     */

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();


    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;

    }

    private void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        /**
         * 如果选择i 那么将i设置到t中
         */
        t.add(nums[cur]);
        /**
         *
         */
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        /**
         * 不选择i 那么直接进入到下一个数
         */
        dfs(cur + 1, nums);
    }

    /**
     * 迭代法实现子集枚举
     */

    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}
