package dfs;

import commom.TreeNode;

public class _0000_dfs {

    /**
     * 树 的dfs模板
     */
    void traverse(TreeNode root) {
        // 判断 base case
        if (root == null) {
            return;
        }
        // 访问两个相邻结点：左子结点、右子结点
        traverse(root.left);
        traverse(root.right);
    }


    /**
     * 网格 DFS模板 但是网格和树不一样 有可能重复
     */
    void dfs(int[][] grid, int r, int c) {
        // 判断 base case
        // 如果坐标 (r, c) 超出了网格范围，直接返回
        if (!inArea(grid, r, c)) {
            return;
        }

        // 如果这个格子不是岛屿，直接返回
        if (grid[r][c] != 1) {
            return;
        }
        grid[r][c] = 2; // 将格子标记为「已遍历过」

        // 访问上、下、左、右四个相邻结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }


}
