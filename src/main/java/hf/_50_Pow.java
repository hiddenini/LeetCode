package hf;

/**
 * https://leetcode-cn.com/problems/powx-n/
 */
public class _50_Pow {
    /**
     * 快速幂 递归
     */
    public double myPow(double x, int n) {
        /**
         * -1 >>1 还是-1
         */
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        //是否为奇数
        boolean odd = (n & 1) == 1;
        double half = myPow(x, n >> 1);
        half *= half;
        //x = (n < 0) ? (1 / x) : x;
        return odd ? (half * x) : half;
    }

    /**
     * 快速幂 非递归
     */
    public double myPow1(double x, int n) {
        boolean neg = n < 0;
        /**
         * -2^31 <= n <= 2^31-1
         */
        long y = neg ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            //System.out.println(n & 1);
            if ((y & 1) == 1) {
                //最后一个二进制是1,就进行累乘
                res *= x;
            }
            x *= x;
            //舍弃掉最后一个二进制位
            y >>= 1;
        }
        return neg ? (1 / res) : res;
    }
}
