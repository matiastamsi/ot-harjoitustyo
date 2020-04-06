package flyfishinggame.domain;

import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.pane;
import java.util.*;
import javafx.animation.AnimationTimer;

/**
 * The class to represent rapids.
 *
 * @author Matias Tamsi
 */
public class Rapids {

    public List<Rock> rocks;
    /**
     * New rapids has list of objects 
     * (usually objects that extends ObjectInTheRiver).
     */
    public Rapids() {
        this.rocks = new ArrayList<>();
    }
    /**
     * This method keeps the water and 
     * other particles moving and "alive".
     */
    public void flow() {
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                //Actions in here.
            }
        }
                .start();
    }
    /**
     * Method to create a new river sight
     * in the beginning or when changing the spot.
     */
    public void createNewSight() {
        if (!rocks.isEmpty()) {
            rocks.forEach(rock -> pane.getChildren().remove(rock.getShapePolygon()));
            rocks.clear();
        }

        for (int i = 0; i < 3; i++) {
            Random rnd = new Random();

            int positionX = 150 + rnd.nextInt(WIDTH - 150);
            int positionY = 50 + rnd.nextInt(HEIGHT - 50);

            int size = 40 + rnd.nextInt(20);
            Rock rock = new Rock(positionX, positionY, size);
            this.rocks.add(rock);
        }

        rocks.forEach(rock -> pane.getChildren().add(rock.getShapePolygon()));

    }
}
