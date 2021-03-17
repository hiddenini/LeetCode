package string;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class _151_翻转字符串里的单词 {
    public static String reverseWords(String s) {
        if (s == null) return " ";
        char[] chars = s.toCharArray();
        //消除多余的空格
        //字符串的最终有效长度
        int len = 0;
        //当前用来存放字符的位置
        int cur = 0;
        //前一个字符是否为空格(-1位置是空格,假想的哨兵)
        boolean space = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                chars[cur++] = chars[i];
                space = false;
            } else if (space == false) {//chars[i]是空格字符,chars[i-1]是非空格字符
                chars[cur++] = ' ';
                space = true;
            }
        }
        len = space ? (cur - 1) : cur;
        if (len < 0) return "";
        System.out.println(len);

        //对整个有效字符串进行逆序
        reverse(chars, 0, len);

        //对每一个单词进行逆序

        //前一个空格字符的位置 假想的哨兵,-1位置假想有个空格 单词都是在上一个空格和当前空格之中的
        int preSpaceIdx = -1;
        /**
         * 这个循环可以翻转除了最后一个单词之前的所有单词,因为i<len
         *
         * 由于单词都是在上一个空格和当前空格之中的 所以当i=10的时候循环已经结束了,并不能拿到最后一个单词
         *
         * 为啥不能i<=len 因为前面消除多余的空格的时候是在原字符上面进行覆盖的,所以不确定现在的字符串的len位置到底是不是空格
         *
         * 如果不是空格也无法确定最后一个单词,最简单的就是直接翻转[chars,preSpaceIdx+!,len)
         */
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars, preSpaceIdx + 1, i);
            preSpaceIdx = i;
        }
        //翻转最后一个单词
        reverse(chars, preSpaceIdx + 1, len);
        return new String(chars, 0, len);
    }

    /**
     * 将[l,r)范围内的字符串进行逆序
     */
    private static void reverse(char[] chars, int li, int ri) {
        ri--;
        while (li < ri) {
            char temp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = temp;
            li++;
            ri--;
        }

    }

    public static void main(String[] args) {
        System.out.println("666_" + reverseWords("") + "_666");
        System.out.println("666_" + reverseWords("  hello world!     ") + "_666");
        System.out.println("666_" + reverseWords("a good   example") + "_666");
        System.out.println("666_" + reverseWords("are you ok") + "_666");
    }
}
