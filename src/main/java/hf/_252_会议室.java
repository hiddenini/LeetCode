package hf;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/meeting-rooms/
 * <p>
 * 类似做储值时区间是否有重复
 */
public class _252_会议室 {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);
        //遍历每一个会议
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][0]) return false;
        }
        return false;
    }
}
