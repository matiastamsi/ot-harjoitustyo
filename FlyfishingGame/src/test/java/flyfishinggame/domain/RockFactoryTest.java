package flyfishinggame.domain;

import javafx.scene.shape.Polygon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test rock factory's functionality.
 * 
 * @author Matias Tamsi
 */
public class RockFactoryTest {

    RockFactory rf;

    @Test
    public void factoryMakesUniqueRock() {
        rf = new RockFactory();
        Polygon r1 = rf.createRock(9.9);
        assertEquals(false, rf.createRock(9.9).equals(r1));
    }
}
