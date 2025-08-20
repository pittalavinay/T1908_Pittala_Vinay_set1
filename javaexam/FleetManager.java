/**
 * Manages a fleet of Vehicle objects, computes statistics, and triggers alerts.
 * Provides thread-safe operations using a ReentrantLock for concurrent access.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FleetManager {
    /** Temperature threshold for critical overheating alert */
    private static final double TEMPERATURE_THRESHOLD = 110.0;
    /** Fuel threshold for low fuel warning */
    private static final double FUEL_THRESHOLD = 15.0;

    /** List of vehicles in the fleet */
    private final List<Vehicle> vehicles;
    /** List of alert messages */
    private final List<String> alerts;
    /** Lock for thread-safe operations */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Default constructor. Initializes empty vehicle and alert lists.
     */
    public FleetManager() {
        vehicles = new ArrayList<>();
        alerts = new ArrayList<>();
    }

    /**
     * Constructor with initial vehicles.
     * @param initialVehicles List of vehicles to add to the fleet
     */
    public FleetManager(List<Vehicle> initialVehicles) {
        vehicles = new ArrayList<>();
        alerts = new ArrayList<>();
        if (initialVehicles != null) {
            for (Vehicle v : initialVehicles) {
                addVehicle(v);
            }
        }
    }

    /**
     * Adds a vehicle to the fleet and checks for alerts.
     * @param v Vehicle to add
     */
    public void addVehicle(Vehicle v) {
        lock.lock();
        try {
            vehicles.add(v);
            if (v.getTemperature() > TEMPERATURE_THRESHOLD) {
                alerts.add("Critical Overheating for Vehicle " + v.getId());
            }
            if (v.getFuel() < FUEL_THRESHOLD) {
                alerts.add("Low Fuel Warning for Vehicle " + v.getId());
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Computes the average speed of all vehicles.
     * @return Average speed
     * @throws IllegalStateException if the fleet is empty
     */
    public double getAverageSpeed() {
        lock.lock();
        try {
            if (vehicles.isEmpty()) {
                throw new IllegalStateException("No vehicles in fleet");
            }
            return vehicles.stream().mapToDouble(Vehicle::getSpeed).average().orElse(0);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Computes the average temperature of all vehicles.
     * @return Average temperature
     * @throws IllegalStateException if the fleet is empty
     */
    public double getAverageTemperature() {
        lock.lock();
        try {
            if (vehicles.isEmpty()) {
                throw new IllegalStateException("No vehicles in fleet");
            }
            return vehicles.stream().mapToDouble(Vehicle::getTemperature).average().orElse(0);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Computes the average fuel of all vehicles.
     * @return Average fuel
     * @throws IllegalStateException if the fleet is empty
     */
    public double getAverageFuel() {
        lock.lock();
        try {
            if (vehicles.isEmpty()) {
                throw new IllegalStateException("No vehicles in fleet");
            }
            return vehicles.stream().mapToDouble(Vehicle::getFuel).average().orElse(0);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the list of alert messages.
     * @return List of alerts
     */
    public List<String> getAlerts() {
        lock.lock();
        try {
            return new ArrayList<>(alerts);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the list of vehicles in the fleet.
     * @return List of vehicles
     */
    public List<Vehicle> getVehicles() {
        lock.lock();
        try {
            return new ArrayList<>(vehicles);
        } finally {
            lock.unlock();
        }
    }
}
