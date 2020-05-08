package flyfishinggame.domain;

import javafx.geometry.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test rock object.
 *
 * @author Matias Tamsi
 */
public class RockTest {

    Rock rock;

    @Test
    public void constructorSetsTheSizeRight() {
        rock = new Rock(1, 1, 123);
        assertEquals(123, rock.getSize(), 0);
    }

    @Test
    public void constructorSetsThePointRight() {
        rock = new Rock(134, 421, 6.0);
        //Rock won't move.
        assertEquals(new Point2D(0, 0), rock.getMovement());
    }

    @Test
    public void contructorSetsTheCoordinatesRight() {
        rock = new Rock(198, 252, 67);
        assertEquals(198, rock.getX(), 0);
        assertEquals(252, rock.getY(), 0);
    }
}
