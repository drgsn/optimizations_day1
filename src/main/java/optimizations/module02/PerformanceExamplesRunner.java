package optimizations.module02;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceExamplesRunner {

    public static void main(String[] args) throws Exception {
        System.out.println("Running Java Performance Examples\n");

        // 1. Latency Example
        System.out.println("=== Latency Measurement Example ===");
        runLatencyExample();

        // 2. Throughput Example
        System.out.println("\n=== Throughput Measurement Example ===");
        runThroughputExample();

        // 3. Memory Leak Example
        System.out.println("\n=== Memory Leak Detection Example ===");
        runMemoryLeakExample();

        // 4. CPU Bottleneck Example
        System.out.println("\n=== CPU Bottleneck Example ===");
        runCPUBottleneckExample();

        // 5. Thread Contention Example
        System.out.println("\n=== Thread Contention Example ===");
        runThreadContentionExample();
    }

    private static void runLatencyExample() {
        LatencyMeasurement.measureLatency(() -> {
            // Simulate some work
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Test with statistics
        LatencyMeasurement.LatencyStatistics stats = new LatencyMeasurement.LatencyStatistics();
        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            // Simulate varying workload
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            long end = System.nanoTime();
            stats.recordLatency((end - start) / 1_000_000.0);
        }

        System.out.printf("Average Latency: %.2f ms%n", stats.getAverageLatency());
        System.out.printf("95th Percentile: %.2f ms%n", stats.getPercentile(95));
    }

    private static void runThroughputExample() throws InterruptedException {
        ThroughputMeasurement.ThroughputMonitor monitor = new ThroughputMeasurement.ThroughputMonitor();
        monitor.start();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                // Simulate work
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                monitor.recordOperation();
            });
        }

        // Monitor throughput for 30 seconds
        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000);
            System.out.printf("Current throughput: %.2f ops/sec%n", monitor.getCurrentThroughput());
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void runMemoryLeakExample() {
        MemoryLeakDetection.MemoryMonitor.printMemoryStats();

        // Create a controlled memory leak
        MemoryLeakDetection.LeakyCache leakyCache = new MemoryLeakDetection.LeakyCache();
        System.out.println("\nInducing memory leak...");

        for (int i = 0; i < 1000; i++) {
            leakyCache.addToCache("key-" + i);
            if (i % 10 == 0) {
                MemoryLeakDetection.MemoryMonitor.printMemoryStats();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static void runCPUBottleneckExample() {
        CPUProfilingDemo.CPUIntensiveTask task = new CPUProfilingDemo.CPUIntensiveTask();

        System.out.println("Running inefficient operation...");
        long start = System.nanoTime();
        task.inefficientOperation();
        long inefficientTime = System.nanoTime() - start;

        System.out.println("Running optimized operation...");
        start = System.nanoTime();
        task.optimizedOperation();
        long optimizedTime = System.nanoTime() - start;

        System.out.printf("""
                Results:
                Inefficient: %.2f ms
                Optimized: %.2f ms
                Improvement: %.2f%%%n""",
            inefficientTime / 1_000_000.0,
            optimizedTime / 1_000_000.0,
            (inefficientTime - optimizedTime) * 100.0 / inefficientTime);
    }

    private static void runThreadContentionExample() throws InterruptedException {
        ThreadContentionDemo.ContentionExample contention = new ThreadContentionDemo.ContentionExample();
        int numThreads = 10;
        int iterationsPerThread = 1000000;

        // Test with high contention
        long start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < iterationsPerThread; j++) {
                    contention.incrementWithLock();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        long highContentionTime = System.nanoTime() - start;

        // Test with low contention
        start = System.nanoTime();
        executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < iterationsPerThread; j++) {
                    contention.incrementAtomic();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        long lowContentionTime = System.nanoTime() - start;

        System.out.printf("""
                Thread Contention Results:
                High Contention Time: %.2f ms
                Low Contention Time: %.2f ms
                Improvement: %.2f%%%n""",
            highContentionTime / 1_000_000.0,
            lowContentionTime / 1_000_000.0,
            (highContentionTime - lowContentionTime) * 100.0 / highContentionTime);
    }
}