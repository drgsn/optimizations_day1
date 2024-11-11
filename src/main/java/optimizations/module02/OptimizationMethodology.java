package optimizations.module02;

/**
 * Observation
 * Hypothesis
 * Experiment
 * Analysis
 * Conclusion
 */
public class OptimizationMethodology {
    public static class PerformanceExperiment {
        private final String experimentName;
        private final Runnable baseline;
        private final Runnable optimized;

        public PerformanceExperiment(String experimentName,
            Runnable baseline, Runnable optimized) {
            this.experimentName = experimentName;
            this.baseline = baseline;
            this.optimized = optimized;
        }

        public void runExperiment(int iterations) {
            // Warm-up phase
            System.out.println("Warming up...");
            for (int i = 0; i < iterations / 10; i++) {
                baseline.run();
                optimized.run();
            }

            // Measurement phase
            System.out.println("Measuring baseline...");
            long baselineTime = measurePerformance(baseline, iterations);

            System.out.println("Measuring optimized version...");
            long optimizedTime = measurePerformance(optimized, iterations);

            // Analysis
            double improvement = (baselineTime - optimizedTime) /
                                 (double) baselineTime * 100;

            System.out.printf("""
                Experiment: %s
                Baseline Time: %d ms
                Optimized Time: %d ms
                Improvement: %.2f%%%n""",
                experimentName,
                baselineTime,
                optimizedTime,
                improvement);
        }

        private long measurePerformance(Runnable task, int iterations) {
            long startTime = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                task.run();
            }
            return (System.nanoTime() - startTime) / 1_000_000;
        }
    }
}