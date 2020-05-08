package flyfishinggame.domain;

import javafx.scene.shape.Polygon;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test pole.
 * 
 * @author Matias Tamsi
 */
public class PoleTest {

    Pole pole;

    @Test
    public void contructorCreatesPolygonShapedObject() {
        pole = new Pole(1, 1, 1);
        int length = 1;
        /**
         * To compare if shape is correct, compare the list of points. Following
         * points are the same as used in class.
         */
        assertEquals(new Polygon(0, 0,
                -length / 100, -length / 7,
                -length / 100, -1 * length,
                -2 * length / 100, -1 * length,
                -2 * length / 100, -length / 7,
                -3 * length / 100, 0).getPoints(),
                pole.getShape().getPoints());
    }
}
