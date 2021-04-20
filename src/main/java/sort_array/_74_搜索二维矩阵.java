package sort_array;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class _74_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        //行数
        int row = matrix.length;
        //列数
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            //将一维坐标变为二维坐标
            //在第几行
            int rowNum = mid / col;
            //在第几列
            int colNum = mid % col;
            if (matrix[rowNum][colNum] == target) {
                return true;
            } else if (matrix[rowNum][colNum] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return false;
    }

}
