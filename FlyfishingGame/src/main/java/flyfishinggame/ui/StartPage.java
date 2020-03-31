
package flyfishinggame.ui;

import static flyfishinggame.ui.FlyfishingUi.HEIGHT;
import static flyfishinggame.ui.FlyfishingUi.WIDTH;
import static flyfishinggame.ui.FlyfishingUi.stage;
import static flyfishinggame.ui.FlyfishingUi.startButton;
import static flyfishinggame.ui.FlyfishingUi.nicknameField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author tamsi
 */
public class StartPage {

    public void createStartingSight() {
        //Use borderpane as a base for features.
        BorderPane startingSight = new BorderPane();
        startingSight.setPrefSize(WIDTH, HEIGHT);
        Label startingText = new Label("\n"
                + "     Hi flyfisher! Good to see you here wanting to flyfish.\n\n"
                + "     You might already know this, but here is a tip for you:\n"
                + "     Fish, such as trout, waits for food in flows caused by rocks.\n\n"
                + "     When you feel ready, give your nickname and press the start.\n"
                + "             Good luck for you! Remember to be patient.\n");
        startingText.setFont(new Font("Arial", 25));
        nicknameField.setMaxSize(150, 30);
        VBox inputs = new VBox();
        inputs.setSpacing(30);
        inputs.setAlignment(Pos.CENTER);
        inputs.getChildren().addAll(startingText, nicknameField, startButton);
        //Add (mayde) picture of a fish also.
        startingSight.setCenter(inputs);
        
        Scene startScene = new Scene(startingSight);
        stage.setScene(startScene);
        stage.setTitle("FlyfishingGame");
        stage.show();
    }
}
