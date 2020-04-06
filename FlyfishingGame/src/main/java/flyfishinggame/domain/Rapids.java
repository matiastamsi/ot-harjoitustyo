package flyfishinggame.domain;

import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.pane;
import java.util.*;
import javafx.animation.AnimationTimer;

/**
 *
 * @author tamsi
 */
public class Rapids {

    public List<Rock> rocks;

    public Rapids() {
        this.rocks = new ArrayList<>();
    }

    public void flow() {
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                //Actions in here.
            }
        }
                .start();
    }

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
