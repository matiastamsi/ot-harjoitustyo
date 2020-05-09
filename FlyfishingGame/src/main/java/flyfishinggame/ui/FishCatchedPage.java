package flyfishinggame.ui;

import flyfishinggame.domain.Fish;
import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.stage;
import static flyfishinggame.ui.FlyfishingUi.continueButton;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * When player catches a fish this class creates a page where it shows info
 * about the catch. This class is one of the UI classes.
 *
 * @author Matias Tamsi
 */
public class FishCatchedPage {

    /**
     * If player catches a fish, show the length of the fish and points of that
     * catch.
     */
    public void createPage(Fish fish) {
        BorderPane fishCatched = new BorderPane();
        fishCatched.setPrefSize(WIDTH, HEIGHT);
        Label textForFisher = new Label("\n"
                + "             Awesome! You got a "
                + ((int) fish.getSize()) * 5 + " cm trout!"
                + "\n                   You earned "
                + ((int) fish.getSize()) * 5 + " points!"
                + " \n"
                + " \n"
                + " \n"
                + " \n"
                + " \n");
        textForFisher.setFont(new Font("Arial", 40));
        fishCatched.setTop(textForFisher);
        fishCatched.setCenter(continueButton);
        Scene catchScene = new Scene(fishCatched);
        stage.setScene(catchScene);
        stage.setTitle("Fish catched!");
        stage.show();
    }
}
