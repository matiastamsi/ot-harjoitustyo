
package flyfishinggame.domain;

import java.util.Random;

/**
 *
 * @author tamsi
 */
public class Bubble {
    private int x;
    private int y;
    public BlackBall black;
    public WhiteBall white;
    
    public Bubble(int x, int y, double size) {
        this.x = x;
        this.y = y;
        this.black = new BlackBall(x, y, size);
        this.white = new WhiteBall(x, y, size - 1);
    }
}
