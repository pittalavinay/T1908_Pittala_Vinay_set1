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
Average Speed: 48.8056858014997
Average Temperature: 106.250547966467
Average Fuel: 39.7188541564077
Time (ms): 10

--- Multi-threaded Results ---
Average Speed: 64.82529763087844
Average Temperature: 116.85656040085277
Average Fuel: 70.83597423072062
Time (ms): 10

Alerts:
Critical Overheating for Vehicle V2
Low Fuel Warning for Vehicle V2
Critical Overheating for Vehicle V4
Low Fuel Warning for Vehicle V4
```

## Running Tests
JUnit tests are provided for both `Vehicle` and `FleetManager` classes. Use your IDE or build tool to run the tests in `VehicleTest.java` and `FleetManagerTest.java`.

### Test Cases
- Vehicle with temperature=120 triggers 'Critical Overheating'.
- Vehicle with fuel=10 triggers 'Low Fuel Warning'.
- Fleet with speeds [80, 90, 100] computes average speed = 90.
- Boundary cases: temperature=110 does NOT trigger alert, fuel=15 does NOT trigger warning.
- Empty dataset throws an exception.

### Test Output
```
All tests passed.
- VehicleTest: testCriticalOverheatingAlert PASSED
- VehicleTest: testLowFuelWarningAlert PASSED
- VehicleTest: testTemperatureBoundaryNoAlert PASSED
- VehicleTest: testFuelBoundaryNoWarning PASSED
- FleetManagerTest: testAverageSpeedCalculation PASSED
- FleetManagerTest: testEmptyDatasetThrowsException PASSED
```
