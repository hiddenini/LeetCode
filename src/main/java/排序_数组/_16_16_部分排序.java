package 排序_数组;

/**
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 */
public class _16_16_部分排序 {
    public int[] subSort(int[] array) {
        /**
         * 从左到右找到最右边的那个逆序对的位置
         */
        if (array.length == 0) return new int[]{-1, -1};
        int max = array[0];
        int r = -1;
        for (int i = 1; i < array.length; i++) {
            int v = array[i];
            if (v >= max) {
                max = v;
            } else {
                r = i;
            }
        }

        /**
         * 从右到左找到最左边的那个逆序对的位置
         */
        int min = array[array.length - 1];
        int l = -1;
        for (int i = array.length - 2; i >= 0; i--) {
            int v = array[i];
            if (v <= min) {
                min = v;
            } else {
                l = i;
            }
        }

        return new int[]{l, r};
    }
}
