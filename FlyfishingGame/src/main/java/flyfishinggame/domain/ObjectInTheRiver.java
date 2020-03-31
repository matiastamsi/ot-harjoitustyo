package flyfishinggame.domain;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 *
 * @author tamsi
 */

/*Class describes objects that can be seen
moving or staying still in the river.
*/


public class ObjectInTheRiver {

    private double x;
    private double y;
    private double size;
    private Polygon shape;
    private Point2D movement;

    public ObjectInTheRiver(Polygon shape, int x, int y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.shape = shape;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.movement = new Point2D(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
    }

    public Polygon getShapePolygon() {
        return shape;
    }

    public double getSize() {
        return size;
    }
}
