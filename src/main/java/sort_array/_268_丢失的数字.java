package sort_array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/missing-number/
 */
public class _268_丢失的数字 {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
    }

    /**
     * 使用异或 可以参考136只出现一次的数字
     */
    public int missingNumber1(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < res; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}
