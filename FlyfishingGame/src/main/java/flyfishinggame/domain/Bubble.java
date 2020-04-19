package flyfishinggame.domain;

import java.util.Random;

/**
 * The class to represent bubble in the river.
 *
 * @author tamsi
 */
public class Bubble {

    private int x;
    private int y;
    public BlackCircle black;
    public WhiteCircle white;

    /**
     * A bubble is created with a white 
     * circle on top of a black circle. The
     * size difference makes it look like a bubble.
     *
     * @param x coordinate
     * @param y coordinate
     * @param size Size of the circles.
     */
    public Bubble(int x, int y, double size) {
        this.x = x;
        this.y = y;
        this.black = new BlackCircle(x, y, size);
        this.white = new WhiteCircle(x, y, size - 1);
    }
}
