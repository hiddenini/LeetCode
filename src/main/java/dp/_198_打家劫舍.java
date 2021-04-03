package dp;

/**
 * https://leetcode-cn.com/problems/house-robber/
 */
public class _198_打家劫舍 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        int dp[] = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            /**
             * 对于第i间房
             * 偷第i间房,那么就不能偷第i-1间房,那么就等于前i-2间房的能偷到的最大值加上第i间房的价值
             *
             * 不偷第i间房,就等于前i-1间房能偷到的最大价值
             */
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    /**
     * 优化成滚动数组
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }
}
