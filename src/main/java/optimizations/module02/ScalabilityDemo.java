package optimizations.module02;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScalabilityDemo {
    public static class ScalableProcessor {
        private final int parallelism;
        private final ExecutorService executor;

        public ScalableProcessor(int parallelism) {
            this.parallelism = parallelism;
            this.executor = Executors.newFixedThreadPool(parallelism);
        }

        public <T> List<T> processItems(List<T> items,
            Function<T, T> processor) {
            return items.parallelStream()
                .map(processor)
                .collect(Collectors.toList());
        }

        public void shutdown() {
            executor.shutdown();
        }
    }

    // Example of measuring scalability
    public static void measureScalability() {
        List<Integer> items = IntStream.range(0, 10000)
            .boxed()
            .collect(Collectors.toList());

        Function<Integer, Integer> processor = i -> {
            // Simulate complex processing
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return i * 2;
        };

        // Test with different numbers of threads
        for (int threads = 1; threads <= Runtime.getRuntime()
            .availableProcessors(); threads *= 2) {
            ScalableProcessor sp = new ScalableProcessor(threads);

            long start = System.currentTimeMillis();
            sp.processItems(items, processor);
            long duration = System.currentTimeMillis() - start;

            System.out.printf("Threads: %d, Duration: %d ms%n",
                threads, duration);
            sp.shutdown();
        }
    }
}