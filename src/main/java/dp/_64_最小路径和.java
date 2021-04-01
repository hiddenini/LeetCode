package dp;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * <p>
 * {@link _剑指Offer_47_礼物的最大价值}
 *
 * 唯一的区别是一个是最大值,一个是最小值
 */
public class _64_最小路径和 {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];

        /**
         * 初始化第0行
         */
        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col - 1] + grid[0][col];
        }
        /**
         * 初始化第0列
         */
        for (int row = 1; row < rows; row++) {
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - 1]) + grid[row][col];
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
