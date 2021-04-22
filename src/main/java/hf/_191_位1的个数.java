package hf;

/**
 * https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class _191_位1的个数 {
    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * n & (n−1)，其运算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。
     * <p>
     * 运算结果 4 即为把 6 的二进制位中的最低位的 1变为 0 之后的结果。
     * <p>
     * 这样我们可以利用这个位运算的性质加速我们的检查过程，在实际代码中
     * <p>
     * 我们不断让当前的 n 与 n - 1 做与运算，直到 n 变为 0即可。
     * <p>
     * 因为每次运算会使得 n 的最低位的 1 被翻转，因此运算次数就等于 n 的二进制位中 1的个数
     */
    public int hammingWeight1(int n) {
        int result = 0;
        while (n != 0) {
            n &= n - 1;
            result++;
        }
        return result;
    }
}
