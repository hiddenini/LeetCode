package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 */
public class _1115_交替打印FooBar_blockingQueue {
    private int n;

    public _1115_交替打印FooBar_blockingQueue(int n) {
        this.n = n;
    }

    BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            printFoo.run();
            q1.put("ok");
            q2.take();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            q1.take();
            printBar.run();
            q2.put("ok");
        }
    }
}
