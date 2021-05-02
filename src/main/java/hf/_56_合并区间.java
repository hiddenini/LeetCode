package hf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class _56_合并区间 {
    public int[][] merge(int[][] intervals) {
        //将每个数组按照最小值排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //存放最后的结果
        List<int[]> merged = new ArrayList<int[]>();

        //遍历排序之后的intervals 由于intervals[i].length=2 即每一个数组只有2个元素
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0];
            int R = intervals[i][1];
            //如果merged还是空 或者 遍历到的数组的第一位大于merged中最后一个数组的第二位
            if (merged.size() == 0 || L > merged.get(merged.size() - 1)[1]) {
                merged.add(new int[]{L, R});
            } else {
                //否则则设置merged最后一个数组的第二位为当前遍历到的数组的第二位和自己的第二位的的较大值
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
