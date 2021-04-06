package Thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode-cn.com/problems/print-in-order/
 */
public class _1114_按序打印 {

    AtomicInteger num = new AtomicInteger(1);


    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        num.getAndIncrement();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (num.get()!=2){}
        printSecond.run();
        num.getAndIncrement();

    }

    public void third(Runnable printThird) throws InterruptedException {
        while (num.get()!=3){}
        printThird.run();
        num.getAndIncrement();
    }
}
