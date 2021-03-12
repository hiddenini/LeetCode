package 排序_数组;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class _88_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int cur = nums1.length - 1;
        //如果nums2数组先用完,那么说明nums2中的所有元素已经合并到nums1 整个操作已经完成
        while (i2 >= 0) {
            if (i1 >= 0 && nums2[i2] < nums1[i1]) {
                nums1[cur--] = nums1[i1--];

/*
                nums1[cur] = nums1[i1];
                i1--;
                cur--;
*/

            } else {
                //i1 <0 || nums2[i2] >=nums1[i1]
                /**
                 * i1 <0 时说明nums1数组的元素全部往后排好序了,直接将nums2元素放到nums1即可
                 *
                 *  nums2[i2] >=nums1[i1] 时同样也是将nums2元素放到nums1位置即可
                 */
                nums1[cur--] = nums2[i2--];

            }
        }
    }
}
