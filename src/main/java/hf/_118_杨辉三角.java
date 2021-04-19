package hf;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle/
 */
public class _118_杨辉三角 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <=i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    //动态规划思想 这一行j位置的值等于上一行j-1位置和j位置的和
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }

            }
            result.add(row);
        }
        return result;
    }
}
