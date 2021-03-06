package flyfishinggame.domain;

import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.poleLength;
import static flyfishinggame.ui.FlyfishingUi.bubbleSize;
import static flyfishinggame.ui.FlyfishingUi.leafSize;
import static flyfishinggame.ui.FlyfishingUi.rockSize;
import static flyfishinggame.ui.FlyfishingUi.fishSize;
import static flyfishinggame.ui.FlyfishingUi.speedRange;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 * The class to represent the rapids.
 *
 * @author Matias Tamsi
 */
public class Rapids {

    static boolean onTopOfAnother = false;
    public Random r = new Random();
    public List<Rock> rocks;
    public List<WhiteCircle> buffers;
    public List<WhiteCircle> takenAreas;
    public Bubble[] bubbles;
    public List<Bubble> splashRings;
    public Leaf[] leaves;
    public List<Fish> fishes;
    public Pole pole;
    public double speed;

    /**
     * New rapids has list of rocks. Bubbles and leaves are stored in the
     * arrays. This way operations are fast. Because the amount is solid,
     * variation is created with randomness later on.
     */
    public Rapids() {
        this.rocks = new ArrayList<>();
        this.buffers = new ArrayList<>();
        this.takenAreas = new ArrayList<>();
        this.fishes = new ArrayList<>();
        this.bubbles = new Bubble[200];
        this.splashRings = new ArrayList<>();
        this.leaves = new Leaf[3];
        this.pole = new Pole(poleLength + poleLength / 50, HEIGHT, poleLength);
        this.speed = speedRange + r.nextDouble();
    }

    /**
     * This method keeps the water and other particles moving and "alive".
     *
     * @param line the line of the UI.
     * @param pane the pane of the UI.
     */
    public void flow(Line line, Pane pane) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (line.hitsRock(rocks)) {
                    line.clear(pane);
                }
                if (line.line.size() > 0) {
                    line.moveTheLine(speed, pane);
                    checkIfFishBites(line, pane);
                    if (line.line.get(0).getX() >= WIDTH
                            || (line.line.get(0).getY() < HEIGHT - poleLength + 1
                            && line.line.get(0).getY() > HEIGHT - poleLength - 1)) {
                        line.clear(pane);
                    }
                }
                if (!splashRings.isEmpty()) {
                    splashRings.forEach(sr -> {
                        sr.black.moveCircle(speed);
                        sr.white.moveCircle(speed);
                    });
                    for (Rock rock : rocks) {
                        for (Bubble ring : splashRings) {
                            if (rock.crashPolygonToCircle(ring.black)) {
                                pane.getChildren().remove(rock.getShapePolygon());
                                pane.getChildren().add(rock.getShapePolygon());
                            }
                        }
                    }
                }
                if (Math.random() < 0.05) {
                    fadeAwayRings(pane);
                }
                //Randomly set some of the fishes to jump (gives a hint to user).
                if (Math.random() < 0.0005) {
                    fishBite(fishes.get(r.nextInt(fishes.size() - 1)), pane);
                }

                for (int i = 0; i < bubbles.length; i++) {
                    if (i < leaves.length) { //Handle leaves.
                        //If a leaf has gone out of sight make a new to replace it.
                        if (leaves[i].getX() > WIDTH) {
                            pane.getChildren().remove(leaves[i].getShapePolygon());
                            if (Math.random() < 0.5) {
                                continue; //Not always three leaves in the sight.
                            } else {
                                leaves[i] = new Leaf(0, 25 + r.nextInt(HEIGHT - 25),
                                        leafSize + r.nextDouble());
                                pane.getChildren().add(leaves[i].getShapePolygon());
                            }
                        }
                        //Move the leaves forward (to right).
                        leaves[i].movePolygon(speed);
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
                            bubbles[i] = new Bubble(0, 25 + r.nextInt(HEIGHT - 25),
                                    r.nextInt(bubbleSize));
                            pane.getChildren().add(bubbles[i].black.getShapeCircle());
                            pane.getChildren().add(bubbles[i].white.getShapeCircle());
                        }
                    }
                    //Move the bubbles forward (to right).
                    bubbles[i].black.moveCircle(speed);
                    bubbles[i].white.moveCircle(speed);
                    //Check if hits to the buffers and move back if so.
                    for (int j = 0; j < buffers.size(); j++) {
                        if (bubbles[i].black.crashCircleToCircle(buffers.get(j))) {
                            bubbles[i].black.moveCircleAside(buffers.get(j));
                            bubbles[i].white.moveCircleAside(buffers.get(j));
                        }
                    }
                    //Bubbles pop randomly and new ones emerge.
                    if (Math.random() < 0.0001) {
                        replaceBubble(pane);
                    }
                }
                /*Add the pole constantly on the sight
                to make it seem to be on top of other objects*/
                pane.getChildren()
                        .remove(pole.getShape());
                pane.getChildren()
                        .add(pole.getShape());
            }
        }
                .start();
    }

    /**
     * Method to create a new river sight in the beginning or when changing the
     * spot.
     *
     * @param line the line of the UI.
     * @param pane the pane of the UI.
     */
    public void createNewSight(Line line, Pane pane) {
        if (!rocks.isEmpty()) {
            clear(line, pane);
            speed = speedRange + r.nextDouble(); //Set new random speed.
        }
        createBubbles(pane);
        for (int i = 0; i < 3; i++) { //Create the rest of the river objects.
            int positionX = 150 + r.nextInt(WIDTH);
            int positionY = 50 + r.nextInt(HEIGHT - 50);
            double size = 2 * rockSize + r.nextInt(rockSize);
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
            //Set one fish (per rock) on top flow of the rock.
            Fish fish = new Fish(positionX + 2 * fishSize + r.nextInt(2 * fishSize),
                    positionY + 2 * fishSize + r.nextInt(fishSize / 2),
                    r.nextInt(fishSize));
            fishes.add(fish);
            //Set one fish (per rock) on bottom flow of the rock.
            Fish fish2 = new Fish(positionX + 2 * fishSize + r.nextInt(2 * fishSize),
                    positionY - 2 * fishSize - r.nextInt(fishSize / 2),
                    r.nextInt(fishSize));
            fishes.add(fish2);
            //Set two fishes (per rock) into random places.
            Fish fish3 = new Fish(r.nextInt(WIDTH), r.nextInt(HEIGHT),
                    r.nextInt(fishSize));
            fishes.add(fish3);
            Fish fish4 = new Fish(r.nextInt(WIDTH), r.nextInt(HEIGHT),
                    r.nextInt(fishSize));
            fishes.add(fish4);

            Rock rock = new Rock(positionX, positionY, size);
            rocks.add(rock);

            positionX = r.nextInt(WIDTH);
            positionY = 25 + r.nextInt(HEIGHT - 25);
            size = leafSize + r.nextDouble();

            Leaf leaf = new Leaf(positionX, positionY, size);
            leaves[i] = leaf;
            pane.getChildren().add(leaf.getShapePolygon());
        }
        /**
         * Add first fishes so that if fish and rock hits, the rock will cover
         * the fish.
         */
        fishes.forEach(fish -> pane.getChildren().add(fish.getShapeCircle()));
        rocks.forEach(rock -> pane.getChildren().add(rock.getShapePolygon()));
    }

    /**
     * Clear the pane and lists/arrays. Bubbles and rings can stay.
     *
     * @param line the line of the UI.
     * @param pane the pane of the UI.
     */
    public void clear(Line line, Pane pane) {
        line.clear(pane);
        rocks.forEach(rock -> pane.getChildren().remove(rock.getShapePolygon()));
        rocks.clear();
        fishes.forEach(fish -> pane.getChildren().remove(fish.getShapeCircle()));
        fishes.clear();
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
     * Create bubbles to the sight (randomly).
     *
     * @param pane the pane of the UI.
     */
    public void createBubbles(Pane pane) {
        for (int i = 0; i < 200; i++) {
            int positionX = r.nextInt(WIDTH);
            int positionY = 25 + r.nextInt(HEIGHT - 25);
            double size = r.nextInt(bubbleSize);
            Bubble bubble = new Bubble(positionX, positionY, size);
            bubbles[i] = bubble;
            pane.getChildren().add(bubble.black.getShapeCircle());
            pane.getChildren().add(bubble.white.getShapeCircle());
        }
    }

    /**
     * Method removes a random bubble and makes a new one to random coordinates
     * (not on top of a rock).
     *
     * @param pane the pane of the UI.
     */
    public void replaceBubble(Pane pane) {
        int a = r.nextInt(200);
        pane.getChildren().remove(bubbles[a].black.getShapeCircle());
        pane.getChildren().remove(bubbles[a].white.getShapeCircle());
        int positionX = r.nextInt(WIDTH);
        int positionY = 25 + r.nextInt(HEIGHT - 25);
        double size = r.nextInt(bubbleSize);
        Bubble bubble = new Bubble(positionX, positionY, size);
        if (!rocks.get(0).crashPolygonToCircle(bubble.black)
                || !rocks.get(1).crashPolygonToCircle(bubble.black)
                || !rocks.get(2).crashPolygonToCircle(bubble.black)) {
            bubbles[a] = bubble;
            pane.getChildren().add(bubble.black.getShapeCircle());
            pane.getChildren().add(bubble.white.getShapeCircle());
        }
    }

    /**
     * Method to return the rocks of the rapid.
     *
     * @return list of rocks.
     */
    public List<Rock> getRocks() {
        return rocks;
    }

    /**
     * Method to return the fishes of the rapid.
     *
     * @return list of fishes.
     */
    public List<Fish> getFishes() {
        return fishes;
    }

    /**
     * Go through the fishes. If fly is on top of fish (and fish is not already
     * splashing) set fish hooked and call fishBite method.
     *
     * @param line the line of the UI.
     * @param pane the pane of the UI.
     */
    public void checkIfFishBites(Line line, Pane pane) {
        fishes.forEach(fish -> {
            if (line.line.get(0).crashPolygonToCircle(fish) && splashRings.isEmpty()) {
                fish.hooked(true);
                fishBite(fish, pane);
            }
        });
    }

    /**
     * Method creates rings to represent splashing that eating fish creates.
     *
     * @param fish the fish that bites.
     * @param pane the pane of the UI.
     */
    public void fishBite(Fish fish, Pane pane) {
        for (int i = 6; i > 1; i--) {
            double s = fish.getSize();
            int rX = r.nextInt((int) s);
            int rY = r.nextInt((int) s);
            Bubble bubble = new Bubble(
                    ((int) (fish.getX() + rX + s / i)),
                    ((int) (fish.getY() + rY + s / i)),
                    (int) s * i / 2);
            pane.getChildren().add(bubble.black.getShapeCircle());
            pane.getChildren().add(bubble.white.getShapeCircle());
            splashRings.add(bubble);
        }
    }

    /**
     * Method takes rings away from the splash rings. To make it seem more real,
     * it takes by turns big ring and small ring.
     *
     * @param pane the pane of the UI.
     */
    public void fadeAwayRings(Pane pane) {
        if (!splashRings.isEmpty()) {
            if (splashRings.size() == 1) {
                for (Fish fish : fishes) {
                    fish.hooked(false);
                }
            }
            if (splashRings.size() % 2 == 0) {
                pane.getChildren().remove(splashRings.get(0).black.getShapeCircle());
                pane.getChildren().remove(splashRings.get(0).white.getShapeCircle());
                splashRings.remove(0);
            } else {
                pane.getChildren().remove(
                        splashRings.get(splashRings.size() - 1).black.getShapeCircle());
                pane.getChildren().remove(
                        splashRings.get(splashRings.size() - 1).white.getShapeCircle());
                splashRings.remove(splashRings.size() - 1);
            }
        }
    }
}
