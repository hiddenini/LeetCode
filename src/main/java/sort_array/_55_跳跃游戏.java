package sort_array;

/**
 * https://leetcode-cn.com/problems/jump-game/
 */
public class _55_跳跃游戏 {
    public boolean canJump(int[] nums) {
        int longest = 0;
        /**
         * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
         * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
         * 如果可以一直跳到最后，就成功了。
         *
         * 前n-1个元素能够跳到的最远距离
         */
        for (int i = 0; i <= longest; i++) {
            //第i个元素能够跳到的最远距离
            int cur = i + nums[i];
            //更新最远距离
            longest = Math.max(longest, cur);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (longest >= nums.length - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        _55_跳跃游戏 obj = new _55_跳跃游戏();
        obj.canJump(new int[]{2, 3, 1, 1, 4});
    }
}
