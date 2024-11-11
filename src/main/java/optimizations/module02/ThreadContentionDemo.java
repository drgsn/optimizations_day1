package optimizations.module02;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lock contention
 * Thread pool saturation
 * Deadlocks
 * Resource starvation
 */
public class ThreadContentionDemo {
    public static class ContentionExample {
        private final Object lock = new Object();
        private int counter = 0;

        // High contention version
        public void incrementWithLock() {
            synchronized(lock) {
                counter++;
            }
        }

        // Low contention version
        private final AtomicInteger atomicCounter = new AtomicInteger(0);

        public void incrementAtomic() {
            atomicCounter.incrementAndGet();
        }

        // Thread pool monitoring
        public static class ThreadPoolMonitor {
            private final ThreadPoolExecutor executor;

            public ThreadPoolMonitor(ThreadPoolExecutor executor) {
                this.executor = executor;
            }

            public void printStats() {
                System.out.printf("""
                    Thread Pool Statistics:
                    Active Threads: %d
                    Pool Size: %d
                    Core Pool Size: %d
                    Queue Size: %d
                    Completed Tasks: %d%n""",
                    executor.getActiveCount(),
                    executor.getPoolSize(),
                    executor.getCorePoolSize(),
                    executor.getQueue().size(),
                    executor.getCompletedTaskCount());
            }
        }
    }
}
