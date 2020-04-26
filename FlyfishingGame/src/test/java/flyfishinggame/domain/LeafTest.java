package flyfishinggame.domain;

import javafx.scene.shape.Polygon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tamsi
 */
public class LeafTest {

    Leaf leaf;

    @Test
    public void constructorCreatesLeafShapedPolygon() {
        leaf = new Leaf(1, 1, 1);
        int size = 1;
        //Check the shape based on list of points.
        assertEquals(new Polygon(-5 * size, -5 * size,
                10 * size, 0,
                -5 * size, 5 * size,
                -13 * size, 0,
                -18 * size, 0).getPoints(),
                leaf.getShapePolygon().getPoints());

    }

    @Test
    public void movingTheLeafToRightWorks() {
        leaf = new Leaf(1, 2, 3);
        leaf.movePolygon(1);
        assertEquals(true, leaf.getX() > 1);
    }

    @Test
    public void movingTheLeafRotatesIt() {
        leaf = new Leaf(1, 1, 1);
        leaf.movePolygon(1);
        assertEquals(true, leaf.rotatingmovements != 0);
    }

    @Test
    public void crashingToOtherPolygonInformsCorrectlyWhenNotTouching() {
        leaf = new Leaf(1, 1, 1);
        Leaf o = new Leaf(99, 99, 1);
        assertEquals(false, leaf.crashPolygonToPolygon(o));
    }

    @Test
    public void crashingToOtherPolygonInformsCorrectlyWhenTouching() {
        leaf = new Leaf(1, 1, 1);
        Leaf o = new Leaf(1, 1, 3);
        assertEquals(true, leaf.crashPolygonToPolygon(o));
    }

    @Test
    public void movingPolygonAsideWorks() {
        leaf = new Leaf(1, 1, 1);
        leaf.movePolygonAside(new BlackCircle(2, 2, 2));
        assertEquals(true, leaf.getY() < 1);
        leaf = new Leaf(2, 2, 2);
        leaf.movePolygonAside(new BlackCircle(1, 1, 1));
        assertEquals(true, leaf.getY() > 1);
    }
}
