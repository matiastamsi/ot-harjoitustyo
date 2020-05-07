package flyfishinggame.domain;

import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.poleLength;
import static flyfishinggame.ui.FlyfishingUi.thinLineSize;
import static flyfishinggame.ui.FlyfishingUi.thickLineSize;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.Pane;

/**
 * The class to represent the line. It is created from points of line to make it
 * seem real in the rapid.
 *
 * @author Matias Tamsi
 */
public class Line {

    public List<PartOfTheLine> line = new ArrayList<>();
    private Wave wave;
    private double j;
    private PartOfTheLine point;
    private double thinLine;
    private double thickLine;

    /**
     * Method to tell if line touches a rock. Because there is so many parts of
     * line to check, let us check only every hundred.
     *
     * @param rocks list of rapid's rocks
     * @return true: touches, false: not
     */
    public boolean hitsRock(List<Rock> rocks) {
        for (int i = 0; i < line.size() - 100; i = i + 100) {
            for (Rock rock : rocks) {
                if (line.get(i).crashPolygonToPolygon(rock)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to clear line out of the sight.
     */
    public void clear(Pane pane) {
        if (!line.isEmpty()) {
            line.forEach(point -> pane.getChildren().remove(point.getShapePolygon()));
            line.clear();
            pane.getChildren().remove(wave.getShapePolygon());
        }
    }

    /**
     * This method creates and "draws" the line to the sight when user casts. It
     * calls other methods depending on where the fly lands.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLine(double x, double y, Pane pane) {
        this.j = x;
        this.point = null;
        this.thinLine = thinLineSize;
        this.thickLine = thickLineSize;

        if (y > HEIGHT - poleLength) {
            throwLineUnderTheTip(x, y, pane);
        } else {
            throwLineOverTheTip(x, y, pane);
        }
        this.wave = new Wave(x, y, HEIGHT / 50); //Add a wave.
        pane.getChildren().add(wave.getShapePolygon());
    }

    /**
     * Part two of the throwLine method. This method is called if the line is
     * going to land over the tip. Then chose the direction.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineOverTheTip(double x, double y, Pane pane) {
        if (x < WIDTH / 5) {
            throwLineOverTheTipAndLeft(x, y, pane);
        } else if (x == WIDTH / 5) {
            throwLineOverTheTipAndForward(x, y, pane);
        } else {
            throwLineOverTheTipAndRight(x, y, pane);
        }
    }
    
    /**
     * Part three of the throwLine method. This method is called
     * if the line is going to land over and left from the tip.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineOverTheTipAndLeft(double x, double y, Pane pane) {
        for (double i = y; i < HEIGHT - poleLength; i += 0.2) {
            if (line.size() < poleLength) {
                point = new PartOfTheLine(j, i, thinLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j + 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            } else {
                point = new PartOfTheLine(j, i, thickLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j + 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            }
        }
    }
    /**
     * Part three of the throwLine method. This method is called
     * if the line is going to land over and forward from the tip.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineOverTheTipAndForward(double x, double y, Pane pane) {
        for (double i = y; i < HEIGHT - poleLength; i += 0.2) {
            if (line.size() < poleLength * 2) {
                point = new PartOfTheLine(x, i, thinLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
            } else {
                point = new PartOfTheLine(x, i, thickLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
            }
        }
    }
    /**
     * Part three of the throwLine method. This method is called
     * if the line is going to land over and right from the tip.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineOverTheTipAndRight(double x, double y, Pane pane) {
        for (double i = y; i < HEIGHT - poleLength; i += 0.2) {
            if (line.size() < poleLength) {

                point = new PartOfTheLine(j, i, thinLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j + 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);

            } else {
                point = new PartOfTheLine(j, i, thickLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j + 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            }

        }
    }

    /**
     * Part two of the throwLine method. This method is called if the line is
     * going to land under the tip. Then chose the direction.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineUnderTheTip(double x, double y, Pane pane) {
        if (x < WIDTH / 5) {
            throwLineUnderTheTipAndLeft(x, y, pane);
        } else if (x == WIDTH / 5) {
            throwLineUnderTheTipAndForward(x, y, pane);
        } else {
            throwLineUnderTheTipAndRight(x, y, pane);
        }
    }
    /**
     * Part three of the throwLine method. This method is called
     * if the line is going to land under and left from the tip.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineUnderTheTipAndLeft(double x, double y, Pane pane) {
        for (double i = y; i > HEIGHT - poleLength; i = i - 0.2) {
            if (line.size() < poleLength * 2) {
                point = new PartOfTheLine(j, i, thinLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j - 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            } else {
                point = new PartOfTheLine(j, i, thickLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j - 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            }
        }
    }
    /**
     * Part three of the throwLine method. This method is called
     * if the line is going to land under and forward from the tip.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineUnderTheTipAndForward(double x, double y, Pane pane) {
        for (double i = y; i > HEIGHT - poleLength; i = i - 0.2) {
            if (line.size() < poleLength * 2) {
                point = new PartOfTheLine(x, i, thinLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
            } else {
                point = new PartOfTheLine(x, i, thickLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
            }
        }
    }
    /**
     * Part three of the throwLine method. This method is called
     * if the line is going to land under and right from the tip.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void throwLineUnderTheTipAndRight(double x, double y, Pane pane) {
        for (double i = y; i > HEIGHT - poleLength; i = i - 0.2) {
            if (line.size() < poleLength) {
                point = new PartOfTheLine(j, i, thinLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j - 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            } else {
                point = new PartOfTheLine(j, i, thickLine);
                line.add(point);
                pane.getChildren().add(point.getShapePolygon());
                j = j - 0.2 * (WIDTH / 5 - x) / (HEIGHT - poleLength - y);
            }
        }
    }

    /**
     * Method to move the line and draw a new wave randomly there where the
     * "line goes under the water". It is on purpose that the wave on the sight
     * is "shaking" to make it more real looking.
     *
     * @param speed The speed of the flow.
     */
    public void moveTheLine(double speed, Pane pane) {
        if (Math.random() < 0.4) {
            pane.getChildren().remove(wave.getShapePolygon());
            this.wave = new Wave(line.get(0).getX() + wave.getSize() / 2, line.get(0).getY() + wave.getSize() / 2, 5 + new Random().nextInt(5));
            wave.rotateWave(5 * Math.sqrt(line.get(0).getY()));
            pane.getChildren().add(wave.getShapePolygon());
        }
        for (PartOfTheLine part : line) {
            if (part.getY() < HEIGHT - poleLength) {
                part.movePolygonDown(0.02 * speed * Math.sqrt(HEIGHT - poleLength - part.getY()));
            }
            if (part.getY() > HEIGHT - poleLength) {
                part.movePolygonUp(0.02 * speed * Math.sqrt(part.getY() - (HEIGHT - poleLength)));
            }
        }
    }
}
