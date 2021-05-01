package dfs;

/**
 * https://leetcode-cn.com/problems/word-search/
 */
public class _79_单词搜索 {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean flag = dfs(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        //递归结束条件
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }

        visited[i][j] = true;
        boolean result = false;
        //上下左右四个方向
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (inArea(newI, newJ, board)) {
                if (!visited[newI][newJ]) {
                    boolean flag = dfs(board, visited, newI, newJ, word, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        //还原现场
        visited[i][j] = false;
        return result;
    }

    /**
     * 是否在矩阵内
     */
    boolean inArea(int i, int j, char[][] board) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }
}
