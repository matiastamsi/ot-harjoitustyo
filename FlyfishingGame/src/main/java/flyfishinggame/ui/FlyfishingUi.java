package flyfishinggame.ui;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author tamsi
 */
public class FlyfishingUi extends Application {

    public static int WIDTH = 1920 / 2;
    public static int HEIGHT = 1050 / 2;
    public static Pane pane;
    public static Stage stage;
    public static Button startButton;
    public static TextField nicknameField;
    private AtomicReference<String> nickname;
    private AtomicInteger points;

    public FlyfishingUi() {
        this.pane = new Pane();
        this.nicknameField = new TextField();
        this.startButton = new Button("Start");
        this.nickname = new AtomicReference<String>();
        this.points = new AtomicInteger();
    }

    @Override
    public void start(Stage s) {

        /*First show the startpage, which tells a about the game
        and asks player's nickname.
            When the player presses the startbutton, the startpage
        changes to the actual gamingsight.*/
        
        //Creation of the startpage.
        stage = s;
        StartPage startpage = new StartPage();
        startpage.createStartingSight();

        //Creation of the actual gamingsight.
        
        //Borderpane is the base.
        BorderPane borderpane = new BorderPane();
        borderpane.setPrefSize(WIDTH, HEIGHT);
        //Pane is the area for the action.
        pane.setPrefSize(WIDTH, HEIGHT);
        borderpane.setBottom(pane);
        //Player gets points if catches fish.
        Text text = new Text(WIDTH - 110, 15, "Points: " + points);
        borderpane.getChildren().add(text);
        //On the top is relevant instructions for the player.
        Text instructionText = new Text(WIDTH / 2 - 200, 15,
                "Look for a good spot, then tap to cast."
                + " Watch out the rocks!");
        borderpane.getChildren().add(instructionText);
        Text instructionText2 = new Text(WIDTH / 2 - 150, 30,
                "When the fish bites, tap to catch it!");
        borderpane.getChildren().add(instructionText2);
        //Player has atleast an option to change spot.
        Button changeSpotButton = new Button("Change spot");
        HBox buttons = new HBox();
        buttons.getChildren().add(changeSpotButton);
        borderpane.setTop(buttons);

        /*Method is used to make new riversight when called
            in the start or when player decides to 
            change the spot.*/
        createNewSight();

        Scene scene = new Scene(borderpane);

        /*In the startpage is startbutton. When user
        is ready (and presses the startbutton),
        the startpage changes to the actual gaming sight.*/
        
        startButton.setOnAction((event) -> {
            /*Save the nickname from the textfield.
            and turn it to atomic so that it is saved.*/
            String nm = nicknameField.getText();
            nickname = new AtomicReference<String>(nm);
            
            stage.setTitle("Flyfish!");
            stage.setScene(scene);
            stage.show();
        });

        changeSpotButton.setOnAction((event) -> {
            createNewSight();
        });

    }

    public void createNewSight() {
        //Needs objects to the river.
    }

    public static void main(String[] args) {
        launch(FlyfishingUi.class);
    }
}