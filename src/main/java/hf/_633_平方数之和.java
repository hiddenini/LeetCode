package hf;

/**
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 */
public class _633_平方数之和 {

    public boolean judgeSquareSum(int c) {
        int max = (int) Math.sqrt(c);
        for (int a = 0; a <= max; a++) {
            int b = (int) Math.sqrt(c - a * a);
            if ((a * a + b * b) == c) return true;
        }
        return false;
    }

    public boolean judgeSquareSum1(int c) {
        int a = 0;
        int b = (int) Math.sqrt(c);
        while (a <= b) {
            int num = a * a + b * b;
            if (num == c) {
                return true;
            } else if (num < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}
