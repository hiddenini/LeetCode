package hf;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class _70_爬楼梯 {
    /**
     * 第n阶台阶是从n-1阶爬一阶
     * <p>
     * 或者从n-2阶台阶爬2阶
     * so f(n)=f(n-1)+f(n-2)
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
