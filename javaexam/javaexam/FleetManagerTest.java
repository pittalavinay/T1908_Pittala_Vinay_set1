import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FleetManagerTest {
    @Test
    void testAverageSpeedCalculation() {
        FleetManager manager = new FleetManager();
        manager.addVehicle(new Vehicle("V1", 80, 100, 50));
        manager.addVehicle(new Vehicle("V2", 90, 100, 50));
        manager.addVehicle(new Vehicle("V3", 100, 100, 50));
        assertEquals(90.0, manager.getAverageSpeed(), 0.0001);
    }

    @Test
    void testEmptyDatasetThrowsException() {
        FleetManager manager = new FleetManager();
        assertThrows(IllegalStateException.class, manager::getAverageSpeed);
    }
}
