package flyfishinggame.domain;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * This abstract class describes objects that can be seen moving or staying
 * still in the river.
 *
 * @author Matias Tamsi
 */
public abstract class ObjectInTheRiver {

    private double x;
    private double y;
    private double size;
    private Polygon shape;
    private Circle circle;
    private Point2D movement;
    private int direction;

    /**
     * Creates an object that can be presented as a polygon shaped.
     *
     * @param shape Polygon shape
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param size Size of an object
     */
    public ObjectInTheRiver(Polygon shape, double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.shape = shape;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.movement = new Point2D(0, 0);
        this.direction = 0;
    }

    /**
     * Creates an object that can be presented as a circle.
     *
     * @param circle Circle shape
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param size Size of an object
     */
    public ObjectInTheRiver(Circle circle, int x, int y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.circle = circle;
        this.circle.setTranslateX(x);
        this.circle.setTranslateY(y);
        this.movement = new Point2D(0, 0);
        this.direction = 0;
    }

    /**
     * Method returns object's x-coordinate.
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Method returns object's y-coordinate.
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Method returns object's movement as a point.
     *
     * @return movement
     */
    public Point2D getMovement() {
        return movement;
    }

    /**
     * Method sets a new movement (point).
     *
     * @param movement current point
     */
    public void setMovement(Point2D movement) {
        this.movement = movement;
    }

    /**
     * Method returns shape of Polygon shaped object.
     *
     * @return shape
     */
    public Polygon getShapePolygon() {
        return shape;
    }

    /**
     * Method returns shape of Circle shaped object.
     *
     * @return circle
     */
    public Circle getShapeCircle() {
        return circle;
    }

    /**
     * Method returns the size of an object.
     *
     * @return size
     */
    public double getSize() {
        return size;
    }

    /**
     * Move polygon shaped object to right in the river (flow).
     *
     * @param speed Speed of the flow.
     */
    public void movePolygon(double speed) {

        this.x = this.shape.getTranslateX()
                + speed + this.movement.getX();
        this.shape.setTranslateX(this.shape.getTranslateX()
                + speed + this.movement.getX());
        this.y = this.shape.getTranslateY()
                + this.movement.getY();
        this.shape.setTranslateY(this.shape.getTranslateY()
                + this.movement.getY());

    }

    /**
     * Move circle shaped object to right in the river (flow).
     *
     * @param speed Speed of the flow.
     */
    public void moveCircle(double speed) {
        this.x = this.circle.getTranslateX()
                + speed + this.movement.getX();
        this.circle.setTranslateX(this.circle.getTranslateX()
                + speed + this.movement.getX());
        this.y = this.circle.getTranslateY()
                + this.movement.getY();
        this.circle.setTranslateY(this.circle.getTranslateY()
                + this.movement.getY());
    }

    /**
     * Inform if circle shaped object hits to another circle shaped object.
     *
     * @param other Another circle object.
     * @return Boolean value to tell if it hits.
     */
    public boolean crashCircleToCircle(ObjectInTheRiver other) {
        Shape crasharea = Shape.intersect(this.circle,
                other.getShapeCircle());
        return crasharea.getBoundsInLocal().getWidth() != -1;
    }

    /**
     * Inform if polygon shaped object hits to circle shaped object.
     *
     * @param other Another circle shaped object.
     * @return Boolean value to tell if it hits.
     */
    public boolean crashPolygonToCircle(ObjectInTheRiver other) {
        Shape crasharea = Shape.intersect(this.shape, other.getShapeCircle());
        return crasharea.getBoundsInLocal().getWidth() != -1;
    }

    /**
     * Inform if polygon shaped object hits to another polygon shaped object.
     *
     * @param other Another polygon shaped object.
     * @return Boolean value to tell if it hits.
     */
    public boolean crashPolygonToPolygon(ObjectInTheRiver other) {
        Shape crasharea = Shape.intersect(this.shape, other.getShapePolygon());
        return crasharea.getBoundsInLocal().getWidth() != -1;
    }

    /**
     * Move circle to left and to the direction (up or down) if the object is
     * already rotating something.
     *
     * @param other Other object ahead.
     */
    public void moveCircleAside(ObjectInTheRiver other) {
        if (this.circle.getTranslateY()
                < other.getShapeCircle().getTranslateY()
                + other.getShapeCircle().getCenterY()) {
            this.direction = -1;
        } else {
            this.direction = 1;
        }
        this.x = this.circle.getTranslateX()
                - 0.5 + this.movement.getX();
        this.circle.setTranslateX(this.circle.getTranslateX()
                - 0.5 + this.movement.getX());
        this.y = this.circle.getTranslateY()
                + this.direction
                + this.movement.getY();
        this.circle.setTranslateY(this.circle.getTranslateY()
                + this.direction
                + this.movement.getY());

    }

    /**
     * Move polygon to left and to the direction (up or down) if the object is
     * already rotating something.
     *
     * @param other Other object ahead.
     */
    public void movePolygonAside(ObjectInTheRiver other) {

        if (this.shape.getTranslateY()
                < other.getShapeCircle().getTranslateY()
                + other.getShapeCircle().getCenterY()) {
            this.direction = -1;
        } else {
            this.direction = 1;
        }
        this.x = this.shape.getTranslateX()
                - 0.5 + this.movement.getX();
        this.shape.setTranslateX(this.shape.getTranslateX()
                - 0.5 + this.movement.getX());
        this.y = this.shape.getTranslateY()
                + this.direction
                + this.movement.getY();
        this.shape.setTranslateY(this.shape.getTranslateY()
                + this.direction
                + this.movement.getY());
    }

    /**
     * Method to move polygon shaped object down.
     *
     * @param speed Speed of the flow.
     */
    public void movePolygonDown(double speed) {
        this.x = this.shape.getTranslateX() + speed + this.movement.getX();
        this.shape.setTranslateX(this.shape.getTranslateX() + speed + this.movement.getX());
        this.y = this.shape.getTranslateY() + speed + this.movement.getY();
        this.shape.setTranslateY(this.shape.getTranslateY() + speed + this.movement.getY());
    }

    /**
     * Method to move polygon shaped object up.
     *
     * @param speed Speed of the flow.
     */
    public void movePolygonUp(double speed) {
        this.x = this.shape.getTranslateX() + speed + this.movement.getX();
        this.shape.setTranslateX(this.shape.getTranslateX() + speed + this.movement.getX());
        this.y = this.shape.getTranslateY() - speed - this.movement.getY();
        this.shape.setTranslateY(this.shape.getTranslateY() - speed - this.movement.getY());
    }
}
