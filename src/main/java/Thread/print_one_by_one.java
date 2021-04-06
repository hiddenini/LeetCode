package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用2个线程输出1 A 2 B 3 C...
 * <p>
 * 最优的方法有 lockSupport
 * <p>
 * lock condition
 */
public class print_one_by_one {
    static Thread t1 = null;
    static Thread t2 = null;

    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    static AtomicInteger num = new AtomicInteger(1);

    public static void main(String[] args) {
        //lockSupport();
        //enumExample();
        //atomic();
        //blocking();
        //wait_notify();
        //wait_notify_1();
        //countDl();
        condition();
    }

    static void lockSupport() {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        t1 = new Thread(() -> {
            for (char c : a1) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : a2) {
                /**
                 * 就算t2先运行,上来就被阻塞,直到被t1唤醒
                 */
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        }, "t1");

        t1.start();
        t2.start();
    }

    static void enumExample() {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(() -> {
            for (char c : a1) {
                while (r != ReadyToRun.T1) {
                }
                System.out.println(c);
                r = ReadyToRun.T2;
            }
        }).start();

        new Thread(() -> {
            for (char c : a2) {
                while (r != ReadyToRun.T2) {
                }
                System.out.println(c);
                r = ReadyToRun.T1;
            }
        }).start();
    }

    static void atomic() {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(() -> {
            for (char c : a1) {
                while (num.get() != 1) {
                }
                System.out.println(c);
                num.set(2);
            }
        }).start();

        new Thread(() -> {
            for (char c : a2) {
                while (num.get() != 2) {
                }
                System.out.println(c);
                num.set(1);
            }
        }).start();
    }

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    static void blocking() {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(() -> {
            for (char c : a1) {
                try {
                    System.out.println(c);
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(() -> {
            for (char c : a2) {
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(c);
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 这种不能保证哪个线程先执行
     */
    static void wait_notify() {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    System.out.println(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //必须要加,因为执行到最后总有一个线程在wait,必须唤醒然后才能结束掉,不然程序不会结束
                o.notify();
            }

        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a2) {
                    System.out.println(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //必须要加,因为执行到最后总有一个线程在wait,必须唤醒然后才能结束掉
                o.notify();
            }
        }).start();
    }

    /**
     * 保证t1先运行
     */
    static volatile boolean t2Started = false;

    static void wait_notify_1() {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    System.out.println(c);
                    t2Started = true;
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }).start();

        new Thread(() -> {
            synchronized (o) {
                while (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : a2) {
                    System.out.println(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
    }

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    static void countDl() {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        t1 = new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    System.out.println(c);
                    countDownLatch.countDown();
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t1");

        t2 = new Thread(() -> {
            try {
                //t2先等待 t1先执行之后t2再执行
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c : a2) {
                    System.out.println(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t1");

        t1.start();
        t2.start();
    }

    static void condition() {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : a1) {
                    System.out.println(c);
                    condition2.signal();
                    condition1.await();
                }
                //必须要加,因为执行到最后总有一个线程在await(要么t1 要么t2),必须唤醒然后才能结束掉
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char c : a2) {
                    System.out.println(c);
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
        }).start();
    }


}
