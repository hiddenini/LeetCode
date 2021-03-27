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
        /**
         * 负奇数n>>1 等价于(n-1)>>1
         * -21>>1=-11 所以正负数统一了
         */
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

    public int powMod(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        int res = 1 % z;
        x %= z;
        while (y > 0) {
            if ((y & 1) == 1) {
                //最后一个二进制是1,就进行累乘
                res = (res * x) % z;
            }
            x = (x * x) % z;
            //舍弃掉最后一个二进制位
            y >>= 1;
        }
        return res;
    }

    /**
     * 递归
     */
    public int powMod1(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        //递归基
        if (y == 0) return 1 % z;
        int half = powMod1(x, y >> 1, z);
        half *= half;
        if ((y & 1) == 0) {//偶数
            return half % z;
        } else {
            return (half * (x % z)) % z;
        }
    }
}
