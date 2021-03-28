package hf;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class _7_整数反转 {
    public int reverse(long x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            if (res > Integer.MAX_VALUE) return 0;
            if (res < Integer.MIN_VALUE) return 0;
            x /= 10;
        }
        return (int) res;
    }

    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            int prevRes = res;
            res = prevRes * 10 + x % 10;
            /**
             * 如果溢出是反推不回去的
             */
            if ((res - x % 10) / 10 != prevRes) return 0;
            x /= 10;
        }
        return res;
    }
}
