package sort_array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 */
public class _350_两个数组的交集_II {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        /**
         * 遍历较短的数组将数字和出现的次数保存在map中
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] result = new int[nums1.length];
        int index = 0;
        /**
         * 遍历较长的数组
         */
        for (int num : nums2) {
            Integer orDefault = map.getOrDefault(num, 0);
            orDefault--;
            if (orDefault >= 0) {
                result[index++] = num;
                map.put(num, orDefault);
            } else {
                map.remove(num);
            }
        }

        return Arrays.copyOfRange(result, 0, index);
    }


    /**
     * 排序后使用双指针
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] result = new int[length1 > length2 ? length2 : length1];
        int i = 0, j = 0, index = 0;
        while (i < length1 && j < length2) {
            if (nums1[i] == nums2[j]) {
                result[index] = nums1[i];
                index++;
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }

    public static void main(String[] args) {
        _350_两个数组的交集_II obj = new _350_两个数组的交集_II();
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 6};
        int[] intersect = obj.intersect(nums1, nums2);
        for (int num : intersect) {
            System.out.println(num);
        }
    }
}
