package flyfishinggame.domain;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 * The class to represent the fly fishing pole.
 *
 * @author Matias Tamsi
 */
public class Pole {

    private Polygon pole;
    private Point2D spot;
    private int length;

    /**
     * A pole gets coordinates and length.
     *
     * @param x coordinate
     * @param y coordinate
     * @param length pole's length
     */
    public Pole(int x, int y, int length) {
        this.length = length;
        this.pole = new Polygon(0, 0,
                -length / 100, -length / 7,
                -length / 100, -1 * length,
                -3*length / 100, -1 * length,
                -3*length / 100, -1 * length + length / 100,
                 length / 110, -1 * length + length / 100,
                 length / 110, -1 * length,
                -2 * length / 100, -1 * length,
                -2 * length / 100, -length / 7,
                -3 * length / 100, 0);
        this.pole.setTranslateX(x);
        this.pole.setTranslateY(y);
        this.spot = new Point2D(0, 0);
    }

    /**
     * Method to return the shape of the pole.
     *
     * @return shape of the pole
     */
    public Polygon getShape() {
        return this.pole;
    }
}
