package hf;

/**
 * https://leetcode-cn.com/problems/sum-of-two-integers/
 */
public class _371_两整数之和 {
    /**
     * a + b 的问题拆分为 (a 和 b 的无进位结果) + (a 和 b 的进位结果)
     * 无进位加法使用异或运算计算得出
     * 进位结果使用与运算和移位运算计算得出
     * 循环此过程，直到进位为 0
     * c++需要换成无符号数再进行与操作再左移（计算进位值），
     * 否则在leetcode平台会报runtime error: left shift of negative value
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }
}
