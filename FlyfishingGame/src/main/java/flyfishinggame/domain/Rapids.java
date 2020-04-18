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

    private Random r = new Random();
    public List<Rock> rocks;
    public Bubble[] bubbles;
    public Leaf[] leaves;

    /**
     * New rapids has list of rocks. Bubbles and leaves are stored in the
     * arrays. This way operations are fast. Because the amount is solid,
     * variation is created with randomness later on.
     */
    public Rapids() {
        this.rocks = new ArrayList<>();
        this.bubbles = new Bubble[200];
        this.leaves = new Leaf[3];
    }

    /**
     * This method keeps the water and other particles moving and "alive".
     */
    public void flow() {
        new AnimationTimer() {

            @Override
            public void handle(long now) {

                for (int i = 0; i < 200; i++) {
                    if (i < 3) { //Handle leaves.
                        if (leaves[i].getX() > WIDTH) {
                            pane.getChildren().remove(leaves[i].getShapePolygon());
                            if (Math.random() < 0.5) {
                                continue; //Not always three leaves in the sight.
                            } else {
                                leaves[i] = new Leaf(0, 25 + r.nextInt(HEIGHT - 25), r.nextDouble());
                                pane.getChildren().add(leaves[i].getShapePolygon());
                            }
                        }
                        leaves[i].movePolygon(0.5);
                    }
                    //Handle bubbles.
                    if (bubbles[i].black.getX() > WIDTH) {
                        pane.getChildren().remove(bubbles[i].black.getShapeCircle());
                        pane.getChildren().remove(bubbles[i].white.getShapeCircle());
                        if (Math.random() < 0.25) {
                            continue; //Not always 200 bubbles in the sight.
                        } else {
                            bubbles[i] = new Bubble(0, 25 + r.nextInt(HEIGHT - 25), r.nextInt(5));
                            pane.getChildren().add(bubbles[i].black.getShapeCircle());
                            pane.getChildren().add(bubbles[i].white.getShapeCircle());
                        }
                    }

                    bubbles[i].black.moveCircle(0.5);
                    bubbles[i].white.moveCircle(0.5);
                }

            }
        }
                .start();
    }

    /**
     * Method to create a new river sight in the beginning or when changing the
     * spot.
     */
    public void createNewSight() {
        if (!rocks.isEmpty()) {
            rocks.forEach(rock -> pane.getChildren().remove(rock.getShapePolygon()));
            rocks.clear();
            for (int i = 0; i < 200; i++) {
                if (i < 3) {
                    pane.getChildren().remove(leaves[i].getShapePolygon());
                }
                pane.getChildren().remove(bubbles[i].black.getShapeCircle());
                pane.getChildren().remove(bubbles[i].white.getShapeCircle());
            }
        }

        //Create bubbles.
        for (int i = 0; i < 200; i++) {
            int positionX = r.nextInt(WIDTH);
            int positionY = 25 + r.nextInt(HEIGHT - 25);
            double size = r.nextInt(5);
            Bubble bubble = new Bubble(positionX, positionY, size);
            bubbles[i] = bubble;
            pane.getChildren().add(bubble.black.getShapeCircle());
            pane.getChildren().add(bubble.white.getShapeCircle());
        }
        //Create rocks and leaves.
        for (int i = 0; i < 3; i++) {
            int positionX = 150 + r.nextInt(WIDTH);
            int positionY = 50 + r.nextInt(HEIGHT - 50);
            double size = 40 + r.nextInt(20);

            Rock rock = new Rock(positionX, positionY, size);
            rocks.add(rock);
            pane.getChildren().add(rock.getShapePolygon());

            positionX = r.nextInt(WIDTH);
            positionY = 25 + r.nextInt(HEIGHT - 25);
            size = r.nextDouble();

            Leaf leaf = new Leaf(positionX, positionY, size);
            leaves[i] = leaf;
            pane.getChildren().add(leaf.getShapePolygon());

        }

    }
}
