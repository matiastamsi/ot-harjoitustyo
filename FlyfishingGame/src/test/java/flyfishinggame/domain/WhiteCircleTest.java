package flyfishinggame.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class.
 *
 * @author Matias Tamsi
 */
public class WhiteCircleTest {

    WhiteCircle wc;

    @Test
    public void constructorCreatesCircleWithRightCoordinates() {
        wc = new WhiteCircle(12, 34, 1);
        assertEquals(12, wc.getX(), 0);
        assertEquals(34, wc.getY(), 0);
    }

    @Test
    public void constructorCreatesCircleWithRightSize() {
        wc = new WhiteCircle(1, 1, 345);
        assertEquals(345, wc.getSize(), 0);
    }
}
