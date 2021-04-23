package dp;

/**
 * https://leetcode-cn.com/problems/decode-ways/
 */
public class _91_解码方法 {

    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                //f[i] 初始化是0 即累加
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                //f[i] 初始化是0  即累加
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    /**
     * 往字符串头部追加空格作为哨兵，追加空格既可以避免讨论前导零
     * 也能使下标从 1 开始，简化 f[i-1] 等负数下标的判断。
     */
    public int numDecodings1(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9) f[i] = f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26) f[i] += f[i - 2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        _91_解码方法 obj = new _91_解码方法();
        String s = "12";
        obj.numDecodings(s);

        int a = 1;
        int b = 0;
        b += a;
        System.out.println(b);
    }
}
