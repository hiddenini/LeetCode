package hf;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/brick-wall/
 */
public class _554_砖墙 {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * 遍历整个二维集合 算出每块砖距离嘴边的距离(最后一排除外)
         *
         * 使用map存储 这样就可以得出这些砖块中距离左边距离相同最多
         *
         * 直线从这个旁边传穿过,那么经过的砖块就是最少的
         */
        for (List<Integer> list : wall) {
            int size = list.size();
            int sum = 0;
            for (int i = 0; i < size - 1; i++) {
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return wall.size() - max;
    }


}
