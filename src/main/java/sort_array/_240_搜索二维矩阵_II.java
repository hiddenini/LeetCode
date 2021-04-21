package sort_array;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class _240_搜索二维矩阵_II {

    public boolean searchMatrix(int[][] matrix, int target) {
        int col = 0;
        int row = matrix.length - 1;

        while (row >= 0 && col <= matrix[0].length - 1) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }
}
