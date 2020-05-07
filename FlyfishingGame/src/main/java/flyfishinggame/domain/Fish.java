package flyfishinggame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The class to represent a fish. Tip: if you want to see where the fish lands,
 * set the colour of the circle (in constructor) to e.g. 'BLUE'.
 *
 * @author Matias Tamsi
 */
public class Fish extends ObjectInTheRiver {

    private boolean hookInMouth;

    /**
     * Like other objects in the river, a fish has coordinates and size.
     *
     * @param x coordinate
     * @param y coordinate
     * @param size the size of the fish.
     */
    public Fish(int x, int y, int size) {
        super(new Circle(size, size, size, Color.BLUE), x, y, size);
        this.hookInMouth = false;
    }

    /**
     * Sets fish hooked or not.
     *
     * @param value true: fish is hooked, false: fish is no longer hooked.
     */
    public void hooked(boolean value) {
        this.hookInMouth = value;
    }

    /**
     * Tells whether the fish is possible to catch.
     *
     * @return hookInMouth which tells whether the fish is splashing etc.
     */
    public boolean isHooked() {
        return this.hookInMouth;
    }
}
