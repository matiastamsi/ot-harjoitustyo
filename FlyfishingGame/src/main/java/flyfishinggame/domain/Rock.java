package flyfishinggame.domain;

/**
 * Class to represent a rock in the rapids.
 *
 * @author Matias Tamsi
 */
public class Rock extends ObjectInTheRiver {

    private int x;
    private int y;

    /**
     * Rock needs coordinates and size. Every rock is unique due to the
     * RockFactory.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param size the size of the rock
     */
    public Rock(int x, int y, double size) {
        super(new RockFactory().createRock(size), x, y, size);
        this.x = x;
        this.y = y;
    }
}
