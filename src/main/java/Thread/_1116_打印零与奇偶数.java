package Thread;

/**
 * https://leetcode-cn.com/problems/print-zero-even-odd/
 */
public class _1116_打印零与奇偶数 {
    private int n;

    public _1116_打印零与奇偶数(int n) {
        this.n = n;
    }

    volatile int state;

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            //i为偶数 i是从0开始 并且需要输出0 奇数 0 偶数...
            /**
             * 那么就是 i=0 输出0 然后唤醒odd 输出1 0->1 然后i=1 此时state!=0 则等待
             *
             * odd输出1之后将state设置为0 之后 zero继续运行 再次输出0 0->1->0 此时唤醒even
             *
             * i=2 even输出2 state设置为0 0->1->0->2 依次循环
             */
            if ((i & 1) == 0) {
                //唤醒输奇数
                state = 1;
            } else {
                //i为奇数, 唤醒输出偶数
                state = 2;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                Thread.yield();
            }
            printNumber.accept(i);
            //唤醒输出0
            state = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            //唤醒输出0
            state = 0;
        }
    }

    private class IntConsumer {
        void accept(Integer x) {
            System.out.println(x);
        }
    }
}
