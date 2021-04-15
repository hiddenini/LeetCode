package dp;

import java.util.Map;

/**
 * https://leetcode-cn.com/problems/house-robber-ii/
 */
public class _213_打家劫舍_II {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        //去尾留头
        //rob(nums, 0, len - 2);
        //去头留尾
        //rob(nums, 1, len - 1);
        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }

    /**
     * 偷取给定范围的房屋
     */
    public int rob1(int[] nums, int start, int end) {
        if (start == end) return nums[end];
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end];
    }

    /**
     * 优化
     */
    public int rob(int[] nums, int start, int end) {
        if (start == end) return nums[end];
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }

}
