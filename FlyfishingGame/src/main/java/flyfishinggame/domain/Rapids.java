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

    static boolean onTopOfAnother = false;
    private Random r = new Random();
    public List<Rock> rocks;
    public List<WhiteCircle> buffers;
    public List<WhiteCircle> takenAreas;
    public Bubble[] bubbles;
    public Leaf[] leaves;
    public Pole pole;

    /**
     * New rapids has list of rocks. Bubbles and leaves are stored in the
     * arrays. This way operations are fast. Because the amount is solid,
     * variation is created with randomness later on.
     */
    public Rapids() {
        this.rocks = new ArrayList<>();
        this.buffers = new ArrayList<>();
        this.takenAreas = new ArrayList<>();
        this.bubbles = new Bubble[200];
        this.leaves = new Leaf[3];
        this.pole = new Pole(150, HEIGHT, 200);
    }

    /**
     * This method keeps the water and other particles moving and "alive".
     */
    public void flow() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < bubbles.length; i++) {
                    if (i < leaves.length) { //Handle leaves.
                        //If a leaf has gone out of sight make a new to replace it.
                        if (leaves[i].getX() > WIDTH) {
                            pane.getChildren().remove(leaves[i].getShapePolygon());
                            if (Math.random() < 0.5) {
                                continue; //Not always three leaves in the sight.
                            } else {
                                leaves[i] = new Leaf(0, 25 + r.nextInt(HEIGHT - 25), r.nextDouble());
                                pane.getChildren().add(leaves[i].getShapePolygon());
                            }
                        }
                        //Move the leaves forward (to right).
                        leaves[i].movePolygon(0.5);
                        //Check if hits to the buffers and move it back if so.
                        for (int j = 0; j < buffers.size(); j++) {
                            if (leaves[i].crashPolygonToCircle(buffers.get(j))) {
                                leaves[i].movePolygonAside(buffers.get(j));
                            }
                        }
                    }
                    //Handle bubbles.
                    /*Check if a bubble has gone out of sight and replace it
                    if necessary*/
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
                    //Move the bubbles forward (to right).
                    bubbles[i].black.moveCircle(0.5);
                    bubbles[i].white.moveCircle(0.5);
                    //Check if hits to the buffers and move back if so.
                    for (int j = 0; j < buffers.size(); j++) {
                        if (bubbles[i].black.crashCircleToCircle(buffers.get(j))) {
                            bubbles[i].black.moveCircleAside(buffers.get(j));
                            bubbles[i].white.moveCircleAside(buffers.get(j));
                        }
                    }
                }
                /*Add the pole constantly on the sight
                to make it seem to be on top of other objects*/
                pane.getChildren().remove(pole.getShape());
                pane.getChildren().add(pole.getShape());
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
            clear();
        }
        createBubbles();
        //Create rocks, buffers for rocks and leaves.
        for (int i = 0; i < 3; i++) {
            int positionX = 150 + r.nextInt(WIDTH);
            int positionY = 50 + r.nextInt(HEIGHT - 50);
            double size = 40 + r.nextInt(20);
            WhiteCircle area = new WhiteCircle((int) (positionX - size * 7 / 4),
                    (int) (positionY - size - 25),
                    (int) (size + 50));
            if (!takenAreas.isEmpty()) {

                takenAreas.forEach(other -> {
                    if (other.crashCircleToCircle(area)) {
                        onTopOfAnother = true;
                    }
                });
            }
            if (onTopOfAnother) {
                i--;
                onTopOfAnother = false;
                continue;
            }
            takenAreas.add(area);
            WhiteCircle buffer1 = new WhiteCircle((int) (positionX - size * 7 / 3),
                    (int) (positionY - size + 25), size - 5);
            buffers.add(buffer1);
            pane.getChildren().add(buffer1.getShapeCircle());
            WhiteCircle buffer2 = new WhiteCircle((int) (positionX - size * 2),
                    (int) (positionY - size + 10), size + 10);
            buffers.add(buffer2);
            pane.getChildren().add(buffer2.getShapeCircle());
            WhiteCircle buffer3 = new WhiteCircle((int) (positionX - size * 7 / 4),
                    (int) (positionY - size), size + 20);
            buffers.add(buffer3);
            pane.getChildren().add(buffer3.getShapeCircle());

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
    /**
     * Clear the pane and lists/arrays.
     */
    public void clear() {
        rocks.forEach(rock -> pane.getChildren().remove(rock.getShapePolygon()));
        rocks.clear();
        buffers.forEach(buffer -> pane.getChildren().remove(buffer.getShapeCircle()));
        buffers.clear();
        takenAreas.clear();
        for (int i = 0; i < 200; i++) {
            if (i < 3) {
                pane.getChildren().remove(leaves[i].getShapePolygon());
            }
            pane.getChildren().remove(bubbles[i].black.getShapeCircle());
            pane.getChildren().remove(bubbles[i].white.getShapeCircle());
        }
    }
    /**
     * Create bubbles in the sight (randomly).
     */
    public void createBubbles() {
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
    }
}
