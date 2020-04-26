package flyfishinggame.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matias Tamsi
 */
public class BlackCircleTest {

    BlackCircle bc;

    @Test
    public void constructorCreatesCircleWithRightCoordinates() {
        bc = new BlackCircle(11, 22, 1);
        assertEquals(11, bc.getX(), 0);
        assertEquals(22, bc.getY(), 0);
    }

    @Test
    public void constructorCreatesCircleWithRightSize() {
        bc = new BlackCircle(1, 1, 345);
        assertEquals(345, bc.getSize(), 0);
    }

    @Test
    public void movingTheCircleRightWorks() {
        bc = new BlackCircle(1, 1, 1);
        bc.moveCircle(1);
        assertEquals(true, bc.getX() > 1);
    }

    @Test
    public void movingCircleAsideWorks() {
        bc = new BlackCircle(1, 1, 1);
        bc.moveCircleAside(new BlackCircle(2, 2, 2));
        assertEquals(true, bc.getY() < 1);
        bc = new BlackCircle(2, 2, 2);
        bc.moveCircleAside(new BlackCircle(1, 1, 1));
        assertEquals(true, bc.getY() > 1);
    }

    @Test
    public void settingMovementWorks() {
        bc = new BlackCircle(1, 2, 3);
        bc.setMovement(new Point2D(123, 321));
        assertEquals(new Point2D(123, 321), bc.getMovement());
    }

    @Test
    public void crashingToOtherCircleInformsCorrectlyWhenNotTouching() {
        bc = new BlackCircle(1, 1, 1);
        BlackCircle o = new BlackCircle(99, 99, 1);
        assertEquals(false, bc.crashCircleToCircle(o));
    }

    @Test
    public void crashingToOtherCircleInformsCorrectlyWhenTouching() {
        bc = new BlackCircle(1, 1, 1);
        BlackCircle o = new BlackCircle(1, 1, 2);
        assertEquals(true, bc.crashCircleToCircle(o));
    }

    @Test
    public void crashingToPolygonInformsCorrectlyWhenNotTouching() {
        bc = new BlackCircle(1, 1, 1);
        Leaf o = new Leaf(99, 99, 1);
        assertEquals(false, o.crashPolygonToCircle(bc));
    }

    @Test
    public void crashingToPolygonInformsCorrectlyWhenTouching() {
        bc = new BlackCircle(1, 1, 1);
        Leaf o = new Leaf(1, 1, 2);
        assertEquals(true, o.crashPolygonToCircle(bc));
    }

}
