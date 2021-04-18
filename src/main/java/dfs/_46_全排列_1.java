package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 */
public class _46_全排列_1 {
    private List<List<Integer>> list;

    private int[] nums;


    private List<Integer> result;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        list = new ArrayList<>();
        if (nums.length == 0) return list;
        result = new ArrayList<>();
        this.nums = nums;
        dfs(0);
        return list;
    }

    private void dfs(int idx) {
        //不能往下搜索
        if (idx == nums.length) {
            list.add(new ArrayList<>(result));
            return;
        }

        //枚举这一层所有的选择
        for (int num : nums) {
            //contains耗时
            if (result.contains(num)) continue;
            result.add(num);
            System.out.println("before");
            for (Integer integer : result) {
                System.out.print(integer+" ");
            }
            dfs(idx + 1);
            //还原现场,删除list的最后一个元素
            result.remove(result.size() - 1);
            System.out.println();
            System.out.println("after");

            for (Integer integer : result) {
                System.out.print(integer+"");

            }
        }
    }

    public static void main(String[] args) {
        _46_全排列_1 obj = new _46_全排列_1();
        obj.permute(new int[]{1, 2, 3});
    }
}
