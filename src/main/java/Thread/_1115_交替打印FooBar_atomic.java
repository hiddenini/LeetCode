package Thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 * <p>
 * 这个方法在leetcode上面提交后n=5 时会超时
 */
public class _1115_交替打印FooBar_atomic {
    private int n;

    public _1115_交替打印FooBar_atomic(int n) {
        this.n = n;
    }

    AtomicInteger num = new AtomicInteger(1);

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (num.get() != 1) {
                //加上这句就不会超时了
                Thread.yield();
            }
            printFoo.run();
            num.set(2);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (num.get() != 2) {
                //加上这句就不会超时了
                Thread.yield();
            }
            printBar.run();
            num.set(1);
        }
    }
}
