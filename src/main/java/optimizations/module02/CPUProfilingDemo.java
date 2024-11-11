package optimizations.module02;

import java.util.ArrayList;
import java.util.List;


/**
 * Inefficient algorithms
 * Excessive object creation
 * Poor loop optimization
 * Unnecessary computations
 */
public class CPUProfilingDemo {
    public static class CPUIntensiveTask {
        public void inefficientOperation() {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 10000000; i++) {
                numbers.add(i);
                // Inefficient string concatenation
                String result = "";
                for (int j = 0; j < 100; j++) {
                    result += String.valueOf(j);
                }
            }
        }

        public void optimizedOperation() {
            List<Integer> numbers = new ArrayList<>(1000000);
            for (int i = 0; i < 10000000; i++) {
                numbers.add(i);
                // Efficient string building
                StringBuilder result = new StringBuilder();
                for (int j = 0; j < 100; j++) {
                    result.append(j);
                }
            }
        }
    }
}