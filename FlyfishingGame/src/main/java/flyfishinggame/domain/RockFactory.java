package flyfishinggame.domain;

import java.util.Random;
import javafx.scene.shape.Polygon;

/**
 * Class makes the rocks to look like a rock and gives an unique shape.
 *
 * @author Matias Tamsi
 */
public class RockFactory {

    /**
     * Method creates a shape that looks like a rock.
     *
     * @param size the size of the rock
     * @return shape
     */
    public Polygon createRock(double size) {

        Random rnd = new Random();
        Polygon shape = new Polygon();
        double c1 = Math.cos(Math.PI * 2 / 5);
        double c2 = Math.cos(Math.PI / 5);
        double s1 = Math.sin(Math.PI * 2 / 5);
        double s2 = Math.sin(Math.PI * 4 / 5);
        shape.getPoints().addAll(
                size, 0.0,
                size * c1, -1 * size * s1,
                -1 * size * c2, -1 * size * s2,
                -1 * size * c2, size * s2,
                size * c1, size * s1);
        for (int i = 0; i < shape.getPoints().size(); i++) {
            int change = rnd.nextInt(40); //Suitable value to randomize.
            shape.getPoints().set(i, shape.getPoints().get(i) + change);
        }
        return shape;
    }
}
