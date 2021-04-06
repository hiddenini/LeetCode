package Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 */
public class _1115_交替打印FooBar {
    private int n;

    public _1115_交替打印FooBar(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    public void foo(Runnable printFoo) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < n; i++) {
                printFoo.run();
                condition1.signal();
                condition2.await();
            }
            //必须要加,因为执行到最后总有一个线程在await,必须唤醒然后才能结束掉
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < n; i++) {
                printBar.run();
                condition2.signal();
                condition1.await();
            }
            //必须要加,因为执行到最后总有一个线程在await,必须唤醒然后才能结束掉
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
