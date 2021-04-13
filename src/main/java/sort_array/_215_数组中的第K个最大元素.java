package sort_array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class _215_数组中的第K个最大元素 {
    /**
     * 暴力法
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len - k];
    }

    /**
     * 快排
     */
    public int findKthLargest1(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        if (l >= r) return a[l];
        int q = randomPartition(a, l, r, index);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }


    int randomPartition(int R[], int lo, int hi, int index) {
        int i = lo, j = hi;
        int temp;
        if (i < j) {
            Random random = new Random();
            int randi = random.nextInt(j - i + 1) + i;
            swap(R, i, randi);
            temp = R[i];
            while (i < j) {
                /**
                 *  注意这里需要=号,否则死循环,为啥,因为这里如果R[j]==temp时,j也应该--
                 *  如果是            while (begin < end) {
                 *                 if (pivot < nums[end]) {
                 *                     end--;
                 *                 } else {
                 *                     nums[begin] = nums[end];
                 *                     begin++;
                 *                     break;
                 *                 }
                 *             }
                 *这种写入，为啥不用加= 因为=在else分支中, begin++;或者end--;不会一直不变 所以不会死循环
                 */
                while (i < j && R[j] >=temp) --j;
                R[i] = R[j];
                while (i < j && R[i] <=temp) ++i;
                R[j] = R[i];
            }
            R[i] = temp;
        }
        return i;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1,5,6,4};
        _215_数组中的第K个最大元素 obj = new _215_数组中的第K个最大元素();
        System.out.println(obj.findKthLargest1(nums, 2));
    }

    /**
     * 使用java自带的堆
     */
    public int findKthLargest2(int[] nums, int k) {
        //构建一个最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> {
            return n1 - n2;
        });
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }

    int findKthLargest4(int[] nums, int k) {
        int pivot, len = nums.length;
        int head = 0, tail = len - 1;
        while (true) {
            Random random = new Random();
            int i  = random.nextInt(tail - head + 1) + head;
            swap(nums, i, head);
            pivot = nums[head];
            while (tail > head) {
                while (nums[tail] >= pivot && tail > head) tail--;
                if (tail > head) nums[head] = nums[tail];
                while (nums[head] <= pivot && tail > head) head++;
                if (tail > head) nums[tail] = nums[head];
            }
            nums[head] = pivot;
            if (len - head == k) return nums[head];
            else if (len - head > k) {
                head++;
                tail = len - 1;
            } else {
                k -= len - head;
                len = head;
                tail = head - 1;
                head = 0;
            }
        }
    }

}
