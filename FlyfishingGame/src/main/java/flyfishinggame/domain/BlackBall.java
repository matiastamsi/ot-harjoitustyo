
package flyfishinggame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author tamsi
 */
public class BlackBall extends ObjectInTheRiver {
    
    public BlackBall(int x, int y, double size) {
        super(new Circle(size, size, size, Color.BLACK), x, y, size);
    }
}
