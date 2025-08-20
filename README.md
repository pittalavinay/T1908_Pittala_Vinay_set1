# Fleet Manager Java Project

## Introduction
This project is a simple fleet management system written in Java. It reads vehicle data from a CSV file, manages a fleet of vehicles, computes average statistics, and triggers alerts for critical conditions such as overheating and low fuel. The project demonstrates object-oriented principles, input validation, exception handling, and includes JUnit tests for core functionality.

## Classes and Functions

### Vehicle
- Represents a single vehicle with the following fields:
  - `id` (String): Unique identifier for the vehicle
  - `speed` (double): Current speed of the vehicle
  - `temperature` (double): Engine temperature in Celsius
  - `fuel` (double): Remaining fuel percentage (0-100)
- Constructor validates input values and throws `IllegalArgumentException` for invalid data.
- Getters for all fields.

### FleetManager
- Manages a list of `Vehicle` objects.
- Methods:
  - `addVehicle(Vehicle v)`: Adds a vehicle and checks for alerts
  - `getAverageSpeed()`: Returns average speed (throws exception if empty)
  - `getAverageTemperature()`: Returns average temperature (throws exception if empty)
  - `getAverageFuel()`: Returns average fuel (throws exception if empty)
  - `getAlerts()`: Returns a list of alert messages
  - `getVehicles()`: Returns the list of vehicles
- Alerts:
  - "Critical Overheating" if temperature > 110°C
  - "Low Fuel Warning" if fuel < 15%

### Main
- Reads vehicle data from `vehicles.csv` (format: id,speed,temperature,fuel)
- Uses `FleetManager` to process data
- Prints average statistics and any alerts
- Skips invalid rows and prints warnings

## Sample Input
**vehicles.csv**
```
id,speed,temperature,fuel
V1,80,105,40
V2,90,112,10
V3,70,108,20
V4,100,115,8
```

## Sample Output
```
Average Speed: 85.0
Average Temperature: 110.0
Average Fuel: 19.5

Alerts:
Critical Overheating for Vehicle V2
Low Fuel Warning for Vehicle V2
Critical Overheating for Vehicle V4
Low Fuel Warning for Vehicle V4
```

## Running Tests
JUnit tests are provided for both `Vehicle` and `FleetManager` classes. Use your IDE or build tool to run the tests in `VehicleTest.java` and `FleetManagerTest.java`.
