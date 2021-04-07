package hf;

/**
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 */
public class _172_阶乘后的零 {
    public int trailingZeroes(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount += 1;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }
}
