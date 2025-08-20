import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    @Test
    void testCriticalOverheatingAlert() {
        Vehicle v = new Vehicle("V1", 80, 120, 50);
        FleetManager manager = new FleetManager();
        manager.addVehicle(v);
        assertTrue(manager.getAlerts().stream().anyMatch(a -> a.contains("Critical Overheating")));
    }

    @Test
    void testLowFuelWarningAlert() {
        Vehicle v = new Vehicle("V2", 80, 100, 10);
        FleetManager manager = new FleetManager();
        manager.addVehicle(v);
        assertTrue(manager.getAlerts().stream().anyMatch(a -> a.contains("Low Fuel Warning")));
    }

    @Test
    void testTemperatureBoundaryNoAlert() {
        Vehicle v = new Vehicle("V3", 80, 110, 50);
        FleetManager manager = new FleetManager();
        manager.addVehicle(v);
        assertFalse(manager.getAlerts().stream().anyMatch(a -> a.contains("Critical Overheating")));
    }

    @Test
    void testFuelBoundaryNoWarning() {
        Vehicle v = new Vehicle("V4", 80, 100, 15);
        FleetManager manager = new FleetManager();
        manager.addVehicle(v);
        assertFalse(manager.getAlerts().stream().anyMatch(a -> a.contains("Low Fuel Warning")));
    }
}
