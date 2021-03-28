package hf;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/meeting-rooms-ii/
 */
public class _252_会议室_2 {
    public int canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);
        /**
         * 创建一个最小堆(存放每一个会议的结束时间)
         */
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //存放0号会议的结束时间
        heap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            /**
             * 堆顶的含义:目前占用的会议室中最早结束时间
             */
            if (intervals[i][0] >= heap.peek()) {
                heap.remove();
            }
            //将i号会议的结束时间放入堆中
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }

    /**
     * 另外一种解法,本质和使用堆一样
     *
     * @param intervals
     * @return
     */
    public int canAttendMeetings1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int[] begins = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            begins[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(begins);
        Arrays.sort(ends);
        int room = 0, endIdx = 0;
        for (int begin : begins) {
            if (begin >= ends[endIdx]) {
                //能重复利用会议室
                endIdx++;
            } else {
                //需要新开一个会议室
                room++;
            }
        }
        return room;
    }

}
