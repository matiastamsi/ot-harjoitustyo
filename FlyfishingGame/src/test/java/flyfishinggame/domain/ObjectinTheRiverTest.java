package flyfishinggame.domain;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tamsi
 */
public class ObjectinTheRiverTest {

    ObjectInTheRiver oIR;

    @Test
    public void constructorSetsTheShapeRight() {
        Polygon p = new Polygon(1, 1, 1, 1);
        oIR = new ObjectInTheRiver(p, 1, 1, 1);
        assertEquals(p, oIR.getShapePolygon());
    }

    @Test
    public void constructorSetsTheSizeRight() {
        Polygon p = new Polygon(1, 1, 1, 1);
        oIR = new ObjectInTheRiver(p, 1, 1, 5.0);
        double size = 5.0;
        assertEquals(size, oIR.getSize(), 0);
    }

    @Test
    public void constructorSetsThePointRight() {
        Polygon p = new Polygon(1, 1, 1, 1);
        oIR = new ObjectInTheRiver(p, 1, 1, 1);
        assertEquals(1, oIR.getX(), 0);
        assertEquals(1, oIR.getY(), 0);
        assertEquals(new Point2D(0, 0), oIR.getMovement());
    }

    @Test
    public void setMovementWorks() {
        Polygon p = new Polygon(1, 1, 1, 1);
        oIR = new ObjectInTheRiver(p, 1, 1, 1);
        oIR.setMovement(new Point2D(3, 3));
        assertEquals(new Point2D(3, 3), oIR.getMovement());
    }

}
