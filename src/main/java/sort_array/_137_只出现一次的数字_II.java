package sort_array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/single-number-ii/
 */
public class _137_只出现一次的数字_II {
    /**
     * 朴素的做法使用hashMap 遍历2次
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1) return key;
        }
        return 0;
    }

    /**
     * 位数统计
     */
    public int singleNumber1(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            //遍历num的每一位
            for (int j = 0; j < 32; j++) {
                //获取num的最低位
                counts[j] += num & 1;
                //num右移一位
                num >>>= 1;
            }
        }

/*        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            //左移 1 位
            res <<= 1;
            // 恢复第 i 位的值到 res
            res |= counts[31 - i] % m;
        }*/


        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((counts[i] % 3 & 1) == 1) {
                res += (1 << i);
            }
        }


        return res;
    }
}
