package flyfishinggame.ui;

import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.stage;
import static flyfishinggame.ui.FlyfishingUi.startButton;
import static flyfishinggame.ui.FlyfishingUi.nicknameField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Class creates the start page. Player reads the instructions and gives a
 * nickname as a player.
 *
 * @author Matias Tamsi
 */
public class StartPage {
    /**
     * Start page is formed by calling this method.
     */
    public void createStartingSight() {
        //Use borderpane as a base for features.
        BorderPane startingSight = new BorderPane();
        startingSight.setPrefSize(WIDTH, HEIGHT);
        Label startingText = new Label("\n"
                + "     Hi flyfisher! Good to see you!\n\n"
                + "     You might already know this, but here is a tip for you:\n"
                + "     Fish, such as trout, waits for food in flows caused by rocks.\n\n"
                + "     When you feel ready, give your nickname and press the start.\n"
                + "     Remember to be patient. Good luck!\n");
        startingText.setFont(new Font("Arial", 25));
        nicknameField.setMaxSize(150, 30);
        VBox inputs = new VBox();
        inputs.setSpacing(30);
        inputs.setAlignment(Pos.CENTER);
        inputs.getChildren().addAll(startingText, nicknameField, startButton);
        startingSight.setCenter(inputs);
        Scene startScene = new Scene(startingSight);
        stage.setScene(startScene);
        stage.setTitle("FlyfishingGame");
        stage.show();
    }
}
