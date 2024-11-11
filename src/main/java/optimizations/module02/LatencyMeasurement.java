package optimizations.module02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatencyMeasurement {
    public static void measureLatency(Runnable task) {
        // Measure end-to-end response time
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();

        // Convert to milliseconds for readability
        double latencyMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Operation latency: %.2f ms%n", latencyMs);
    }

    // Example usage with statistics
    public static class LatencyStatistics {
        private final List<Double> measurements = new ArrayList<>();

        public void recordLatency(double latencyMs) {
            measurements.add(latencyMs);
        }

        public double getAverageLatency() {
            return measurements.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        }

        public double getPercentile(double percentile) {
            List<Double> sorted = new ArrayList<>(measurements);
            Collections.sort(sorted);
            int index = (int) Math.ceil(percentile / 100.0 * sorted.size()) - 1;
            return sorted.get(index);
        }
    }
}