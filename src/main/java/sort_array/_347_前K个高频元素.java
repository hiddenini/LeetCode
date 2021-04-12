package sort_array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class _347_前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) return null;
        int length = nums.length;
        if (length == 0) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        //遍历一次数组,获取每一个元素出现的次数,存入map中
        for (int i = 0; i < length; i++) {
            Integer occurrences = map.get(nums[i]);
            if (occurrences == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], occurrences + 1);
            }
        }
        //使用小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((int[] n1, int[] n2) -> {
            return n1[1] - n2[1];
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer num = entry.getKey();
            Integer occurrences = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < occurrences) {
                    queue.poll();
                    queue.offer(new int[]{num, occurrences});
                }
            } else {
                queue.offer(new int[]{num, occurrences});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }
}
