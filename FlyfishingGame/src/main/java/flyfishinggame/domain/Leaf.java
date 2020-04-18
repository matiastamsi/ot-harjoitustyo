
package flyfishinggame.domain;

import java.util.Random;
import javafx.scene.shape.Polygon;

/**
 *The class that represents a leaf.
 * 
 * @author Matias Tamsi
 */
public class Leaf extends ObjectInTheRiver {
    private double rotatingmovements;
    
    /**
     * A leaf gets coordinates and size
     * like the other objects in the river.
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param size Size of a leaf.
     */
    public Leaf(int x, int y, double size) {
        super(new Polygon(-5*size, -5*size, 10*size, 0, -5*size, 5*size, -13*size, 0, -18*size, 0), x, y, size);
        Random rnd = new Random();
        super.getShapePolygon().setRotate(rnd.nextInt(360));
        this.rotatingmovements = (0.6 - rnd.nextDouble()) / 3;
    }

    @Override
    public void movePolygon(double speed) {
        super.movePolygon(speed);
        super.getShapePolygon().setRotate(super.getShapePolygon().getRotate() + rotatingmovements);
    }
    
  
    
}
