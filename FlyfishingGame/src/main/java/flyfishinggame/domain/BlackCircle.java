package flyfishinggame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The class to represent objects that moves in the river and are black and
 * circle shaped.
 *
 * @author tamsi
 */
public class BlackCircle extends ObjectInTheRiver {

    /**
     * The object gets coordinates and size. Also, the colour is defined as
     * black.
     *
     * @param x coordinate
     * @param y coordinate
     * @param size double value
     */
    public BlackCircle(int x, int y, double size) {
        super(new Circle(size, size, size, Color.BLACK), x, y, size);
    }
}
