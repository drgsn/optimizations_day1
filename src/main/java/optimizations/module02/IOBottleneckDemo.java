package optimizations.module02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unbuffered I/O
 * Synchronous I/O operations
 * Network latency
 * Resource contention
 */
public class IOBottleneckDemo {
    public static class FileProcessor {
        // Inefficient I/O
        public void readFileUnbuffered(String path) throws IOException {
            try (FileReader reader = new FileReader(path)) {
                int character;
                while ((character = reader.read()) != -1) {
                    // Process one character at a time
                }
            }
        }

        // Efficient I/O
        public void readFileBuffered(String path) throws IOException {
            try (BufferedReader reader = new BufferedReader(
                new FileReader(path), 8192)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Process entire lines
                }
            }
        }
    }
}

