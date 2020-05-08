package flyfishinggame.domain;

import javafx.scene.shape.Polygon;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Test wave object.
 *
 * @author Matias Tamsi
 */
public class WaveTest {

    Wave w;

    @Test
    public void creatingWaveWorks() {
        w = new Wave(1, 1, 1);
        assertEquals(new Polygon().getClass(), w.getShapePolygon().getClass());
        assertEquals(1, w.getX(), 0);
        assertEquals(1, w.getY(), 0);
    }

    @Test
    public void rotatingWaveWorks() {
        w = new Wave(1, 1, 1);
        w.rotateWave(1);
        assertEquals(1, w.getShapePolygon().getRotate(), 0);
    }

}
