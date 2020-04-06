
package flyfishinggame.domain;

/**
 *
 * @author tamsi
 */
public class Rock extends ObjectInTheRiver {

    private double size;
    private int x;
    private int y;

    public Rock(int x, int y, double size) {
        super(new RockFactory().createRock(size), x, y, size);
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

}
