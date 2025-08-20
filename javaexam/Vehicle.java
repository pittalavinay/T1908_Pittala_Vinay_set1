/**
 * Represents a vehicle with id, speed, temperature, and fuel fields.
 * Provides thread-safe setters for concurrent updates.
 */
public class Vehicle {
    /** Vehicle identifier */
    private String id;
    /** Current speed of the vehicle */
    private double speed;
    /** Engine temperature in Celsius */
    private double temperature;
    /** Remaining fuel percentage (0-100) */
    private double fuel;

    /**
     * Constructs a Vehicle with the given parameters.
     *
     * @param id Vehicle identifier
     * @param speed Initial speed (must be >= 0)
     * @param temperature Initial temperature (must be between -50 and 200)
     * @param fuel Initial fuel percentage (must be between 0 and 100)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Vehicle(String id, double speed, double temperature, double fuel) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Vehicle id cannot be null or empty");
        }
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative");
        }
        if (temperature < -50 || temperature > 200) {
            throw new IllegalArgumentException("Temperature out of range (-50 to 200)");
        }
        if (fuel < 0 || fuel > 100) {
            throw new IllegalArgumentException("Fuel must be between 0 and 100");
        }
        this.id = id;
        this.speed = speed;
        this.temperature = temperature;
        this.fuel = fuel;
    }

    /**
     * Gets the vehicle identifier.
     * @return Vehicle id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the current speed.
     * @return Speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Gets the current engine temperature.
     * @return Temperature in Celsius
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Gets the remaining fuel percentage.
     * @return Fuel percentage (0-100)
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Thread-safe setter for speed.
     * @param speed New speed (must be >= 0)
     * @throws IllegalArgumentException if speed is negative
     */
    public synchronized void setSpeed(double speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative");
        }
        this.speed = speed;
    }

    /**
     * Thread-safe setter for temperature.
     * @param temperature New temperature (must be between -50 and 200)
     * @throws IllegalArgumentException if temperature is out of range
     */
    public synchronized void setTemperature(double temperature) {
        if (temperature < -50 || temperature > 200) {
            throw new IllegalArgumentException("Temperature out of range (-50 to 200)");
        }
        this.temperature = temperature;
    }

    /**
     * Thread-safe setter for fuel.
     * @param fuel New fuel percentage (must be between 0 and 100)
     * @throws IllegalArgumentException if fuel is out of range
     */
    public synchronized void setFuel(double fuel) {
        if (fuel < 0 || fuel > 100) {
            throw new IllegalArgumentException("Fuel must be between 0 and 100");
        }
        this.fuel = fuel;
    }

    /**
     * Returns a string representation of the vehicle.
     * @return String describing the vehicle
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", speed=" + speed +
                ", temperature=" + temperature +
                ", fuel=" + fuel +
                '}';
    }
}
