package optimizations.module02;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Unclosed resources
 * Growing collections
 * Circular references
 * Classloader leaks
 */
public class MemoryLeakDetection {
    public static class MemoryMonitor {
        private static final Runtime runtime = Runtime.getRuntime();

        public static void printMemoryStats() {
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;

            System.out.printf("""
                Memory Statistics:
                Used Memory: %d MB
                Free Memory: %d MB
                Total Memory: %d MB
                Max Memory: %d MB%n""",
                usedMemory / 1024 / 1024,
                freeMemory / 1024 / 1024,
                totalMemory / 1024 / 1024,
                runtime.maxMemory() / 1024 / 1024);
        }
    }

    // Example of a memory leak
    public static class LeakyCache {
        private static final Map<String, byte[]> cache = new HashMap<>();

        public void addToCache(String key) {
            // Creates a memory leak by continuously adding items
            cache.put(key, new byte[1024 * 1024]); // 1MB per entry
        }
    }
}

