/**
 * Entry point for the fleet management simulation.
 * Loads vehicle data from CSV, runs single-threaded and multi-threaded simulations,
 * and prints statistics and alerts.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * Main entry point. Loads vehicles, runs simulations, and prints results.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        FleetManager manager = new FleetManager();
        String csvFile = "vehicles.csv";
        String line;
        String cvsSplitBy = ",";
        int lineNumber = 0;
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header
            br.readLine();
            lineNumber++;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(cvsSplitBy);
                if (data.length != 4) {
                    System.out.println("Warning: Invalid column count at line " + lineNumber + ". Skipping.");
                    continue;
                }
                try {
                    String id = data[0];
                    double speed = Double.parseDouble(data[1]);
                    double temperature = Double.parseDouble(data[2]);
                    double fuel = Double.parseDouble(data[3]);
                    Vehicle v = new Vehicle(id, speed, temperature, fuel);
                    vehicles.add(v);
                    manager.addVehicle(v);
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: Invalid data at line " + lineNumber + ": " + e.getMessage() + ". Skipping.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        // Single-threaded simulation
        long singleStart = System.nanoTime();
        simulateVehicleUpdates(vehicles, false);
        double avgSpeedSingle = manager.getAverageSpeed();
        double avgTempSingle = manager.getAverageTemperature();
        double avgFuelSingle = manager.getAverageFuel();
        long singleEnd = System.nanoTime();
        long singleDuration = singleEnd - singleStart;

        // Multi-threaded simulation
        long multiStart = System.nanoTime();
        simulateVehicleUpdates(vehicles, true);
        double avgSpeedMulti = manager.getAverageSpeed();
        double avgTempMulti = manager.getAverageTemperature();
        double avgFuelMulti = manager.getAverageFuel();
        long multiEnd = System.nanoTime();
        long multiDuration = multiEnd - multiStart;

        System.out.println("\n--- Single-threaded Results ---");
        System.out.println("Average Speed: " + avgSpeedSingle);
        System.out.println("Average Temperature: " + avgTempSingle);
        System.out.println("Average Fuel: " + avgFuelSingle);
        System.out.println("Time (ms): " + singleDuration / 1_000_000);

        System.out.println("\n--- Multi-threaded Results ---");
        System.out.println("Average Speed: " + avgSpeedMulti);
        System.out.println("Average Temperature: " + avgTempMulti);
        System.out.println("Average Fuel: " + avgFuelMulti);
        System.out.println("Time (ms): " + multiDuration / 1_000_000);

        System.out.println("\nAlerts:");
        for (String alert : manager.getAlerts()) {
            System.out.println(alert);
        }
    }

    /**
     * Simulates vehicle updates, either single-threaded or multi-threaded.
     * @param vehicles List of vehicles to update
     * @param concurrent If true, use multiple threads; otherwise, single-threaded
     */
    private static void simulateVehicleUpdates(List<Vehicle> vehicles, boolean concurrent) {
        Random rand = new Random();
        int updates = 1000;
        if (concurrent) {
            ExecutorService executor = Executors.newFixedThreadPool(vehicles.size());
            for (Vehicle v : vehicles) {
                executor.submit(() -> {
                    for (int i = 0; i < updates; i++) {
                        v.setSpeed(rand.nextDouble() * 120);
                        v.setTemperature(80 + rand.nextDouble() * 50);
                        v.setFuel(rand.nextDouble() * 100);
                    }
                });
            }
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            for (Vehicle v : vehicles) {
                for (int i = 0; i < updates; i++) {
                    v.setSpeed(rand.nextDouble() * 120);
                    v.setTemperature(80 + rand.nextDouble() * 50);
                    v.setFuel(rand.nextDouble() * 100);
                }
            }
        }
    }
}
