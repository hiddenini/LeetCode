package sort_array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/single-number/
 */
public class _136_只出现一次的数字 {

    /**
     * 使用异或运算
     * 1--任何数和 00 做异或运算，结果仍然是原来的数，即 a \oplus 0=aa⊕0=a。
     * 2--任何数和其自身做异或运算，结果是 00，即 a \oplus a=0a⊕a=0。
     * 3--异或运算满足交换律和结合律，即 a \oplus b \oplus a=b \oplus a \oplus a=b \oplus (a \oplus a)=b \oplus0=ba⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
     *
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


    public int singleNumber1(int[] nums) {
        int result = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = map.get(nums[i]);
            if (num == null) {
                map.put(nums[i], 1);
            } else {
                map.remove(nums[i]);
            }
        }
        for (Integer value : map.keySet()) {
            result = value;
        }
        return result;
    }


}
