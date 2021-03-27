package hf;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class _54_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) return null;
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) return list;
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                //left top ->right top
                list.add(matrix[top][i]);
            }
            top++;
            //top right ->bottom right
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--;

            /**
             * 奇数行 偶数列的时候需要判断,因为已经遍历完了，但是还会执行后面的代码,导致重复输出了数字
             *
             * 出问题的地方出现在后面两个for循环,所以在这里判断即可
             */
            if (top > bottom || left > right) break;

            //right bottom ->bottom left
            for (int i = right; i >= left; i--) {
                list.add(matrix[bottom][i]);
            }
            bottom--;
            //left bottom ->left top
            for (int i = bottom; i >= top; i--) {
                list.add(matrix[i][left]);
            }
            left++;
        }
        return list;
    }
}
