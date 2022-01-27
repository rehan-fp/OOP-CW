import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Formula1DriverTest {
    private Formula1Driver testDriver = new Formula1Driver("Rehan", "Negombo", "Redbull", 5);

    @Test
    void testDriverDetails() {
        assertEquals(testDriver.getName(), "Rehan");
        assertEquals(testDriver.getLocation(), "Negombo");
        assertEquals(testDriver.getTeamName(), "Redbull");
        assertEquals(testDriver.getNumberOfRaces(), 5);
    }

    @Test
    void testDriverStatics() {

        ArrayList<Integer> stats = new ArrayList<Integer>();

        stats.add(0,1);
        testDriver.setTotalPoints(stats);
        assertEquals(25,testDriver.getTotalPoints());
    }
}