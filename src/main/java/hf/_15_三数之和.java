package hf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 */
public class _15_三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        //排序
        Arrays.sort(nums);
        int lastIdx = nums.length - 3;
        int lastR = nums.length - 1;
        for (int i = 0; i <= lastIdx; i++) {
            //排除重复
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = lastR, remain = -nums[i];
            while (l < r) {
                int sumLr = nums[l] + nums[r];
                if (sumLr == remain) {
                    //找到符合条件的三元组
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //跳过相同的值
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    //往中间逼近
                    l++;
                    r--;
                } else if (sumLr < remain) {
                    l++;
                } else {//sumLr > remain
                    r--;
                }
            }

        }
        return result;
    }

}
