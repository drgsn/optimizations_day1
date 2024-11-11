package optimizations.module02;


import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualVMDemo {

    private static final List<byte[]> memoryLeakList = new ArrayList<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Print process ID for easy identification
        System.out.println("Process ID: " + ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        System.out.println("VisualVM Demo Running...");

        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Start CPU-intensive task
        executor.submit(VisualVMDemo::cpuIntensiveTask);

        // Start memory-intensive task
        executor.submit(VisualVMDemo::memoryIntensiveTask);

        // Start I/O task
        executor.submit(VisualVMDemo::ioIntensiveTask);

        // Keep application running
        try {
            System.out.println("Manually interrupt to exit");
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static void cpuIntensiveTask() {
        while (!Thread.currentThread().isInterrupted()) {
            // CPU-intensive computation
            for (int i = 0; i < 1000000; i++) {
                final double v = Math.sin(random.nextDouble()) * Math.cos(random.nextDouble());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void memoryIntensiveTask() {
        while (!Thread.currentThread().isInterrupted()) {
            // Allocate memory
            memoryLeakList.add(new byte[1024 * 1024]); // 1MB
            if (memoryLeakList.size() > 100) {
                memoryLeakList.subList(0, 50).clear();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void ioIntensiveTask() {
        while (!Thread.currentThread().isInterrupted()) {
            // Simulate I/O operations
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}