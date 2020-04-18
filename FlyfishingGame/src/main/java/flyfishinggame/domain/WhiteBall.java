package flyfishinggame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author tamsi
 */
public class WhiteBall extends ObjectInTheRiver {

    public WhiteBall(int x, int y, double size) {
        super(new Circle(size, size, size, Color.WHITE), x, y, size);
    }

}
