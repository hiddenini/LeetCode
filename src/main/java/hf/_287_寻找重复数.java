package hf;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class _287_寻找重复数 {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return num;
        }
        return 0;
    }

    /**
     * 链表中：慢指针 slow = slow.nextslow=slow.next；快指针 fast = fast.next.nextfast=fast.next.next
     * <p>
     * 数组中：慢指针 slow = nums[slow]slow=nums[slow]；快指针 fast = nums[nums[fast]]fast=nums[nums[fast]]，因为 numsnums 中的元素值都在 11 到 nn 之间（包括 11 和 nn），所以这样不会造成数组越界问题。
     */
    public int findDuplicate1(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {

            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {

                fast = 0;
                while (nums[slow] != nums[fast]) {

                    slow = nums[slow];
                    fast = nums[fast];
                }
                return nums[slow];
            }
        }
    }

    /**
     * 这里简单解释为什么后面将 slow 放置起点后移动相遇的点就一定是答案了
     * 假设环长为 LL，从起点到环的入口的步数是 a，从环的入口继续走 b 步到达相遇位置，从相遇位置继续走 cc
     * 步回到环的入口，则有 b+c=L其中 L、a、b、c 都是正整数。根据上述定义，慢指针走了 a+b 步，
     * 快指针走了 2(a+b) 步。从另一个角度考虑，在相遇位置，快指针比慢指针多走了若干圈，
     * 因此快指针走的步数还可以表示成 a+b+kL，其中 k 表示快指针在环上走的圈数。联立等式，可以得到
     * <p>
     * 2(a+b)=a+b+kL
     * <p>
     * 解得 a=kL-ba，整理可得
     * <p>
     * a=(k-1)L+(L-b)=(k-1)L+c
     * <p>
     * 从上述等式可知，如果慢指针从起点出发，快指针从相遇位置出发，每次两个指针都移动一步，
     * 则慢指针走了 a 步之后到达环的入口，快指针在环里走了 k-1 圈之后又走了 c 步，
     * 由于从相遇位置继续走 c步即可回到环的入口，因此快指针也到达环的入口。两个指针在环的入口相遇，
     * 相遇点就是答案。
     */
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int pre = 0;
        while (pre != fast) {
            pre = nums[pre];
            fast = nums[fast];
        }
        return nums[pre];
    }

}