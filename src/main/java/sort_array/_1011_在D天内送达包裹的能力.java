package sort_array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 */
public class _1011_在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();

        while (left <right) {
            //防止溢出 不要写成 mid =（left + right）/ 2
            int mid = left + ((right - left) >> 1);

            // need 为需要运送的天数
            // cur 为当前这一天已经运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                //假设现在的载重是mid 那么cur + weight > mid意味着一天不够 need++ 并且 cur = 0 重新开始计算 否则一一直累加
                if (cur + weight > mid) {
                    need++;
                    cur = 0;
                }
                cur += weight;
            }

            if (need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }
        return left;
    }
}
