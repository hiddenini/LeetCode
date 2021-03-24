package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 */
public class _46_全排列 {
    private List<List<Integer>> list;

    private int[] nums;

    /**
     * 用来保存每一层选择的数字
     */
    private int[] result;

    /**
     * 用来标记nums数组中数字是否被使用了
     */
    private boolean[] used;
    //private List<Integer> result;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        list = new ArrayList<>();
        if (nums.length == 0) return list;
        //result = new ArrayList<>();
        result = new int[nums.length];
        used = new boolean[nums.length];
        this.nums = nums;
        dfs(0);
        return list;
    }

    private void dfs(int idx) {
        //不能往下搜索
        if (idx == nums.length) {
            List<Integer> resultList = new ArrayList<>();
            for (int value : result) {
                resultList.add(value);
            }
            list.add(resultList);
            //list.add(new ArrayList<>(result));
            return;
        }

        //枚举这一层所有的选择
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            result[idx] = nums[i];
            used[i] = true;
            dfs(idx + 1);
            //还原现场
            used[i] = false;
        }
    }

}
