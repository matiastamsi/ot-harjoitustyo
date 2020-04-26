
package flyfishinggame.domain;


import javafx.scene.shape.Polygon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Check that PartOfTheLine class works.
 *
 * @author Matias Tamsi
 */
public class PartOfTheLineTest {
    
    PartOfTheLine p;
    
    @Test
    public void creatingPartOfTheLineWorks() {
        p = new PartOfTheLine(1,1,1);
        assertEquals(new Polygon().getClass(), p.getShapePolygon().getClass());
        assertEquals(1, p.getX(), 0);
        assertEquals(1, p.getY(), 0);
    }
    
    @Test
    public void movingPolygonUpWorks() {
        p = new PartOfTheLine(1,1,1);
        p.movePolygonUp(1);
        assertEquals(true, p.getY() < 1);
    }
    @Test
    public void movingPolygonDownWorks() {
        p = new PartOfTheLine(1,1,1);
        p.movePolygonDown(1);
        assertEquals(true, p.getY() > 1);
    }
}
