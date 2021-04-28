package sort_array;

/**
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 */
public class _剑指Offer21_调整数组顺序使奇数位于偶数前面 {
    /**
     * 首尾双指针 有点快排的感觉
     */
    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (i <= j && (nums[i] & 1) == 1) {
                i++;
            }

            while (i <= j && (nums[j] & 1) == 0) {
                j--;
            }
            if (i > j) break;
/*            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;*/
            swap(nums, i, j);

        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    public void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);

    }

    /**
     * 快慢双指针
     */
    public int[] exchange1(int[] nums) {
        int fast = 0, slow = 0;
        int length = nums.length;
        while (fast < length) {
            if ((nums[fast] & 1) == 1) {
                swap(nums, fast, slow);
                slow++;
            }
            fast++;
        }
        return nums;
    }


    public static void main(String[] args) {
        _剑指Offer21_调整数组顺序使奇数位于偶数前面 obj = new _剑指Offer21_调整数组顺序使奇数位于偶数前面();
        for (int i : obj.exchange(new int[]{1, 2, 3, 4})) {
            System.out.println(i);
        }

        int a = 1;
        int b = 7;
        obj.swap(a, b);

    }
}
