package dp;

/**
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class _62_不同路径 {
    /**
     * 用 f(i, j)f(i,j) 表示从左上角走到 (i, j)(i,j) 的路径数量
     * <p>
     * 其中 ii 和 jj 的范围分别是 [0, m)[0,m) 和 [0, n)[0,n)。
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        /**
         * 初始化第0行
         */
        for (int col = 0; col < n; col++) {
            dp[0][col] = 1;
        }
        /**
         * 初始化第0列
         */
        for (int row = 0; row < m; row++) {
            dp[row][0] = 1;
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                /**
                 *dp[row][col] 表示走到第row行,第col列时的走法
                 *
                 * 因为只能往下或者往右走,所以
                 *
                 * 等于第row行,第col-1的走法+第row-1行,第col的走法
                 */
                dp[row][col] = dp[row][col - 1] + dp[row - 1][col];
            }
        }
        return dp[m - 1][n - 1];
    }
}
