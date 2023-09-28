package com.yancy.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 * @author yancy0109
 * @date: 2023/9/27
 */
public class ThreadPool implements Executor {

    private final AtomicInteger ctl = new AtomicInteger(0);

    /**
     * 核心线程数
     */
    private volatile int corePoolSize;

    /**
     * 最大线程数
     */
    private volatile int maximumPoolSize;

    /**
     * 任务阻塞队列
     */
    private final BlockingQueue<Runnable> workQueue;

    public ThreadPool(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable command) {
        int c = ctl.get();
        // 当前线程数 < corePoolSize
        if (c < corePoolSize) {
            // 尝试newThread执行当前线程池中的任务
           if (!addWorker(command)) {
               reject();
           }
           return;
        }
        // 当前线程数 > corePoolSize, 将任务放入线程任务队列
        if (!workQueue.offer(command)) {
            // 如果失败 开启线程执行任务
            if (!addWorker(command)) {
                reject();
            }
        }

    }

    private boolean addWorker(Runnable firstTask) {
        // 大于最大线程数 拒绝
        if (ctl.get() >= maximumPoolSize) {
            return false;
        }
        // 如果不超过最大线程数, 开启线程循环执行 BlockingQueue 中任务
        Worker worker = new Worker(firstTask);
        worker.thread.start();
        // 线程数++;
        ctl.incrementAndGet();
        return true;
    }

    /**
     * Worker 对象
     * 绑定一个Thread，调用 Thread 不停获取 BlockingQueue 队列中的任务，进行执行
     */
    private final class Worker implements Runnable {

        final Thread thread;

        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }

        /**
         * 不断执行流程
         * 从 BlockingQueue 中获取 Task，进行执行
         * 当任务列表为空，ctl--；
         */
        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                while (task != null || (task = getTask()) != null) {
                    task.run();
                    if (ctl.get() > maximumPoolSize) {
                        break;
                    }
                    task = null;
                }
            } finally {
                ctl.decrementAndGet();
            }
        }

        /**
         * 通过 BlockingQueue 获取 Task
         * @return      Runnable接口 Task
         */
        private Runnable getTask() {
            for (;;) {
                try {
                    System.out.println("workQueue.size: " + workQueue.size());
                    return workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 任务拒绝方法
     */
    private void reject() {
        throw new RuntimeException("Error! ctl.count: " + ctl.get() + " workQueue.size: " + workQueue.size());
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 2, new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 10; i++) {
            int finalIdx = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("任务编号: " + finalIdx);
            });
        }


    }
}
