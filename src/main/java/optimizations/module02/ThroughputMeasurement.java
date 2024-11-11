package optimizations.module02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ThroughputMeasurement {
    public static class ThroughputMonitor {
        private final AtomicLong operationCount = new AtomicLong(0);
        private final AtomicLong startTime = new AtomicLong(0);

        public void start() {
            startTime.set(System.currentTimeMillis());
            operationCount.set(0);
        }

        public void recordOperation() {
            operationCount.incrementAndGet();
        }

        public double getCurrentThroughput() {
            long currentTime = System.currentTimeMillis();
            long duration = currentTime - startTime.get();
            if (duration == 0) return 0.0;

            return operationCount.get() / (duration / 1000.0);
        }
    }

    // Example usage
    public static void main(String[] args) throws InterruptedException {
        ThroughputMonitor monitor = new ThroughputMonitor();
        monitor.start();

        // Simulate operations
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                performOperation();
                monitor.recordOperation();
            });
        }

        // Print throughput every second
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.printf("Current throughput: %.2f ops/sec%n",
                monitor.getCurrentThroughput());
        }

        executor.shutdown();
    }

    private static void performOperation() {
        // Simulate work
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}