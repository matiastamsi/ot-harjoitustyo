package flyfishinggame.domain;

import javafx.scene.shape.Polygon;

/**
 * This class represents a wave that is formed when the line creates a wave to
 * water when it goes under it.
 *
 * @author Matias Tamsi
 */
public class Wave extends ObjectInTheRiver {

    /**
     * Wave has coordinates and and size like objects it the river.
     *
     * @param x coordinate
     * @param y coordinate
     * @param size The size of the wave.
     */
    public Wave(double x, double y, int size) {
        super(new Polygon(0, 0, size, -size, 0, -1, -size, -size), x, y, size);
    }

    /**
     * A wave has method to rotate itself.
     *
     * @param angle The value that defines how much to rotate.
     */
    public void rotateWave(double angle) {
        super.getShapePolygon().setRotate(
                super.getShapePolygon().getRotate() + angle);
    }
}
