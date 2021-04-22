package hf;

/**
 * https://leetcode-cn.com/problems/reverse-bits/
 */
public class _190_颠倒二进制位 {

    /**
     * 每次拿到n的最低位 n & 1
     * 使用完将 n 进行右移一位，将答案左移一位
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res += n & 1;
            n >>= 1;

        }
        return res;
    }


}
