package optimizations.module02;


public class MemorySettingsChecker {
    public static void main(String[] args) {
        // Get Runtime instance
        Runtime runtime = Runtime.getRuntime();

        // Calculate memory info in MB
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;

        System.out.println("\nJVM Memory Settings:");
        System.out.println("===================");
        System.out.printf("Maximum Memory (-Xmx): %d MB%n", maxMemory);
        System.out.printf("Initial Memory (-Xms): %d MB%n", totalMemory);
        System.out.printf("Used Memory: %d MB%n", usedMemory);
        System.out.printf("Free Memory: %d MB%n", freeMemory);
        System.out.printf("Total Memory: %d MB%n", totalMemory);

        // Print other useful system properties
        System.out.println("\nJVM Properties:");
        System.out.println("==============");
        System.out.printf("Java Version: %s%n",
            System.getProperty("java.version"));
        System.out.printf("Java VM Name: %s%n",
            System.getProperty("java.vm.name"));
        System.out.printf("Java VM Version: %s%n",
            System.getProperty("java.vm.version"));
        System.out.printf("Java VM Vendor: %s%n",
            System.getProperty("java.vm.vendor"));

        // Print available processors
        System.out.printf("Available Processors: %d%n",
            runtime.availableProcessors());
    }
}