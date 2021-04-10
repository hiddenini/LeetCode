package sort_array;

/**
 * https://leetcode-cn.com/problems/plus-one/
 */
public class _66_加一 {
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        if (digits == null || digits.length == 0) return new int[0];
        /**
         * 从后往前遍历 取出末位数字加1 模上10,不为0则结束 否则继续往前取
         */
        for (int i = length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }

        /**
         * 如果循环结束了还未返回,说明一致都有进位,那么直接新建数组,长度length+1 首位置为1返回
         * 9,9,9  ->1,0,0,0
         */
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{9, 9};
        int[] ints = plusOne(digits);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        System.out.println(10 % 10);
    }
}
