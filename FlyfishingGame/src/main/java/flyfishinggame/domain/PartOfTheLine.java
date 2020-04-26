package flyfishinggame.domain;

import javafx.scene.shape.Polygon;

/**
 * The class to represent one point of the line. This is how the smooth
 * "real-looking" line's movement is made.
 *
 * @author Matias Tamsi
 */
public class PartOfTheLine extends ObjectInTheRiver {

    /**
     * Like the other objects in the river, also part of the line has
     * coordinates and size.
     *
     * @param x coordinate
     * @param y coordinate
     * @param size The size of the part of the line.
     */
    public PartOfTheLine(double x, double y, double size) {
        super(new Polygon(0, 0, 0, size, size, size, size, 0), x, y, size);

    }
}
