package flyfishinggame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *The class to represent objects that moves in the river 
 *and are white and circle shaped.
 *
 * @author tamsi
 */
public class WhiteCircle extends ObjectInTheRiver {

    /**
     * The object gets coordinates and size.
     *Also, the colour is defined as white.
     * @param x coordinate
     * @param y coordinate
     * @param size double value
     */
    public WhiteCircle(int x, int y, double size) {
        super(new Circle(size, size, size, Color.WHITE), x, y, size);
    }

}
