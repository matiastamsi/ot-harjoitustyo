package flyfishinggame.ui;

import flyfishinggame.domain.*;
import flyfishinggame.dao.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * The user interface for the app. First show the start page, which tells a
 * about the game and asks player's nickname. When the player presses the start
 * button, the start page changes to the actual gaming sight. Spot can be
 * changed by pressing the changeButton. If player catches a fish, show a page
 * where is points of the catch etc.
 *
 * @author Matias Tamsi
 */
public class FlyfishingUi extends Application {

    public static int WIDTH;
    public static int HEIGHT;
    public static int poleLength;
    public static int bubbleSize;
    public static int leafSize;
    public static int rockSize;
    public static int partOfTheLineSize;
    public static int speedRange;
    public static int fishSize;
    public static double thinLineSize;
    public static double thickLineSize;
    public static Pane pane;
    public static Stage stage;
    public static Scene scene;
    public static Button startButton;
    public static Button continueButton;
    public static TextField nicknameField;
    public static Line line;
    public static Rapids rapids;
    public static String nickname;
    public static int points;
    public static FlyfishingDao db;
    public static Text topScores;

    public FlyfishingUi() throws SQLException {

        init(); //Read configurations.
        poleLength = WIDTH / 5;
        db = new FlyfishingDao();
        pane = new Pane();
        nicknameField = new TextField();
        startButton = new Button("Start");
        continueButton = new Button("Continue");
        nickname = "";
        points = 0;
        rapids = new Rapids();
        line = new Line();
        topScores = null;
    }

    @Override
    public void init() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            WIDTH = Integer.valueOf(properties.getProperty("WIDTH"));
            HEIGHT = Integer.valueOf(properties.getProperty("HEIGHT"));
            bubbleSize = Integer.valueOf(properties.getProperty("bubbleSize"));
            leafSize = Integer.valueOf(properties.getProperty("leafSize"));
            rockSize = Integer.valueOf(properties.getProperty("rockSize"));
            speedRange = Integer.valueOf(properties.getProperty("speedRange"));
            fishSize = Integer.valueOf(properties.getProperty("fishSize"));
            thinLineSize = Double.valueOf(properties.getProperty("thinLineSize"));
            thickLineSize = Double.valueOf(properties.getProperty("thickLineSize"));
        } catch (Exception e) {
            //Insert default values.
            WIDTH = 960;
            HEIGHT = 525;
            bubbleSize = 7;
            leafSize = 0; // + random double value.
            rockSize = 20;
            speedRange = 1; // + random double value.
            fishSize = 15;
            thinLineSize = 0.5;
            thickLineSize = 1.5;
            System.out.println("Default values are taken into use.");
        }
    }

    @Override
    public void start(Stage s) throws SQLException {
        //Creation of the startpage.
        stage = s;
        StartPage startpage = new StartPage();
        startpage.createStartingSight();

        //Creation of the actual gamingsight.
        //Borderpane is the base.
        BorderPane borderpane = new BorderPane();
        borderpane.setPrefSize(WIDTH, HEIGHT);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
        borderpane.setStyle(style);
        //Pane is the area for the action.
        pane.setPrefSize(WIDTH, HEIGHT);
        borderpane.setBottom(pane);
        //Player gets points if catches fish.
        Text text = new Text(WIDTH - 150, 30, "Score: " + points);
        borderpane.getChildren().add(text);
        //On the top is relevant instructions for the player.
        Text instructionText = new Text(WIDTH / 2 - 200, 15,
                "Look for a good spot, then tap to cast."
                + " Watch out the rocks!");
        borderpane.getChildren().add(instructionText);
        Text instructionText2 = new Text(WIDTH / 2 - 200, 30,
                "When the fish bites and splashes, tap the C-key to catch it!");
        borderpane.getChildren().add(instructionText2);
        //Player has atleast an option to change spot.
        Button changeSpotButton = new Button("Change spot");
        HBox buttons = new HBox();
        buttons.getChildren().add(changeSpotButton);
        borderpane.setTop(buttons);

        db.createDatabase();
        Text headerForScores = new Text(WIDTH - 180, 60, "TOP SCORES:");
        headerForScores.setFont(new Font("Arial", 20));
        borderpane.getChildren().add(headerForScores);
        topScores = new Text(WIDTH - 150, 80, db.getTopFive());
        borderpane.getChildren().add(topScores);
        scene = new Scene(borderpane);

        /*In the startpage is startbutton. When user
        is ready (and presses the startbutton),
        the startpage changes to the actual gaming sight.*/
        startButton.setOnAction((event) -> {
            nickname = nicknameField.getText();
            borderpane.getChildren().add(new Text(WIDTH - 150, 15,
                    "Playing as: " + nickname));
            stage.setTitle("Flyfish!");
            stage.setScene(scene);
            stage.show();
            rapids.flow(line, pane);
        });

        changeSpotButton.setOnAction((event) -> {
            rapids.createNewSight(line, pane);
        });

        /*Method is used to make new riversight when called
            in the start or when player decides to 
            change the spot.*/
        rapids.createNewSight(line, pane);

        pane.setOnMouseClicked((event) -> {
            line.clear(pane);
            double x = event.getX();
            double y = event.getY();
            line.throwLine(x, y, pane);
            if (line.hitsRock(rapids.getRocks())) {
                line.clear(pane);
            }
        }
        );

        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.C)) {
                for (Fish fish : rapids.getFishes()) {
                    if (fish.isHooked()) {
                        points = points + ((int) fish.getSize()) * 5;
                        try {
                            db.addScore(nickname, points);
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        borderpane.getChildren().remove(topScores);
                        try {
                            topScores = new Text(WIDTH - 150, 80, db.getTopFive());
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        borderpane.getChildren().add(topScores);
                        text.setText("Points: " + points);
                        FishCatchedPage fCP = new FishCatchedPage();
                        fCP.createPage(fish);
                        return;
                    }
                }
            }
        });

        continueButton.setOnAction((event) -> {
            stage.setTitle("Flyfish!");
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void main(String[] args) {
        launch(FlyfishingUi.class);
    }
}
