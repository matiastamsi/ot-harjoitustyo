package flyfishinggame.domain;

import javafx.scene.shape.Circle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tamsi
 */
public class BubbleTest {

    Bubble bubble;

    @Test
    public void constructorCreatesBubble() {
        bubble = new Bubble(1, 1, 1);
        assertEquals(new BlackCircle(1, 1, 1).getClass(), bubble.black.getClass());
        assertEquals(new WhiteCircle(1, 1, 1).getClass(), bubble.white.getClass());
    }

    @Test
    public void gettingShapeFromBothBlackAndWhiteCircle() {
        bubble = new Bubble(1, 1, 1);
        assertEquals(new Circle(1, 1, 1).getClass(),
                bubble.black.getShapeCircle().getClass());
        assertEquals(new Circle(1, 1, 1).getClass(),
                bubble.white.getShapeCircle().getClass());
    }
}
