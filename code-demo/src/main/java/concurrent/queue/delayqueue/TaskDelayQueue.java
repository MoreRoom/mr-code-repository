package concurrent.queue.delayqueue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName TaskDelayQueue
 * @Description <p>
 * <h1>本示例主要实现了一个延迟的任务队列</h1>
 * <p>内部类{@link TimeDelay} 用于定义延迟任务的主体</p>
 * <p>内部类{@link ProducerThread} 任务生产者线程</p>
 * <p>内部类{@link CustomerThread} 任务消费者线程</p>
 * <p>
 * 总共产生10000个任务 每个任务会携带一个随机数,用于标记需要等待的时间.
 * 单独设置一个list用来存储结果,目的是防止print输出的时候 多线程导致的输出顺序混乱.
 * </p>
 * </p>
 * @Author MoreRoom
 * @Since 2018/8/30
 */
public class TaskDelayQueue {

    public static void main(String[] args) throws InterruptedException {
        // 消费者线程池
        ExecutorService poolExecutor = new ThreadPoolExecutor(0, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
        DelayQueue<TimeDelay> taskQueue = new DelayQueue<TimeDelay>();
        List<Integer> resultList = new ArrayList<Integer>();
        // 1. 生产线程启动
        new Thread(new ProducerThread(taskQueue)).start();
        // 2. 消费线程启动
        while (true) {
            if (taskQueue != null) {
                TimeDelay result = taskQueue.take();
                if (result != null) {
                    poolExecutor.execute(new CustomerThread(result, resultList));
                }
            }
            if (taskQueue.size() == 0 && ((ThreadPoolExecutor) poolExecutor).getCompletedTaskCount() == 10000) {
                poolExecutor.shutdown();
                break;
            }
        }
        for (Integer integer : resultList) {
//            System.out.println(integer == 1 ? integer + "---------" : integer + "");
            System.out.println(integer + "");
        }
    }

    /**
     * 想要实现延迟队列的效果,需要实现Delayed接口
     */
    private static class TimeDelay implements Delayed {
        /**
         * 执行时间戳
         */
        private long executeTimestamp;

        /**
         * 等待秒数
         */
        private int waitSeconds;

        public TimeDelay(int executeTime) {
            waitSeconds = executeTime / 1000;
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(new Date());
            currentCalendar.add(Calendar.MILLISECOND, executeTime);
            executeTimestamp = currentCalendar.getTime().getTime();
        }

        public int getWaitSeconds() {
            return waitSeconds;
        }

        /**
         * 返回剩余的延迟时间
         * 毫秒为单位
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(executeTimestamp - System.currentTimeMillis(), unit);
        }

        /**
         * 用于比较,实现内部的排序规则
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            if (o instanceof TimeDelay) {
                int result = (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
                if (result == 0) {
                    return 0;
                } else if (result > 0) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                throw new IllegalArgumentException("argument is not instance of TimeDelay.");
            }
        }
    }

    /**
     * 任务生产线程  要求生产10000个线程任务
     */
    private static class ProducerThread implements Runnable {

        /**
         * 任务数
         */
        private static final int TASK_NUMBER = 10000;
        /**
         * 任务队列
         */
        private DelayQueue<TimeDelay> taskQueue;

        public ProducerThread(DelayQueue delayQueue) {
            this.taskQueue = delayQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < TASK_NUMBER; i++) {
                int waitTime = (int) (Math.random() * 100 / 10);
//                if (i == 9998) {
//                    try {
//                        Thread.sleep(3500l);
//                    } catch (Exception e) {
//
//                    }
//                    waitTime = 1;
//                }
                taskQueue.add(new TimeDelay(waitTime * 1000));
            }
        }
    }


    /**
     * 消费线程
     */
    private static class CustomerThread implements Runnable {

        /**
         * 延迟对象
         */
        private TimeDelay timeDelay;
        /**
         * 结果队列
         */
        private List<Integer> resultList;

        public CustomerThread(TimeDelay timeDelay, List<Integer> resultList) {
            this.timeDelay = timeDelay;
            this.resultList = resultList;
        }

        @Override
        public void run() {
            if (null == timeDelay) {
                return;
            }
//            System.out.println("此任务已经等待" + timeDelay.getWaitSeconds() + "s,现在执行.");
            resultList.add(timeDelay.getWaitSeconds());
        }

    }


}
